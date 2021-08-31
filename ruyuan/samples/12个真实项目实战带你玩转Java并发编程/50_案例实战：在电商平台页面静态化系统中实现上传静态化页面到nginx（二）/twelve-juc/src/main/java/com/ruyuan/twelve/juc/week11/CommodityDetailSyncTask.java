

package com.ruyuan.twelve.juc.week11;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 案例场景：电商平台定时生成静态化页面，定时同步到指定的nginx服务器上去
 *
 * @author little
 */
public class CommodityDetailSyncTask implements Runnable {

    @Override
    public void run() {
        /*
         * 创建Pipeline实例。
         */
        SimplePipeline<CommodityInfoTask, String> pipeline = buildPipeline();

        // 初始化
        pipeline.init(pipeline.newDefaultPipelineContext());

        // 接口CommodityDbData
        try {
            // 创建商品查询数据源
            CommodityDbData commodityDbDataList = new CommodityDbData();

            // 使用Pipeline来处理商品信息
            processCommodityInfos(commodityDbDataList, pipeline);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pipeline.shutdown(360, TimeUnit.SECONDS);
    }


    /**
     * 创建pipeline
     *
     * @return 结果
     */
    private SimplePipeline<CommodityInfoTask, String> buildPipeline() {
        /*
         * 线程池的本质是重复利用一定数量的线程，而不是针对每个任务都有一个专门的工作者线程。
         * 这里，各个Pipe的初始化话完全可以在上游Pipe初始化完毕后再初始化其后继Pipe，而不必多个Pipe同时初始化。
         * 因此，这个初始化的动作可以由一个线程来处理。该线程处理完各个Pipe的初始化后，可以继续处理之后可能产生的任务， 如错误处理。
         * 所以，上述这些先后产生的任务可以由线程池中的一个工作者线程从头到尾负责执行。
         *
         * 由于这里的几个Pipe都是处理I/O的，为了避免使用锁（以减少不必要的上下文切换） 但又能保证线程安全，故每个Pipe都采用单线程处理。
         * 若各个Pipe要改用线程池来处理，需要注意：1）线程安全 2）死锁
         */
        final ExecutorService helperExecutor =
                Executors.newSingleThreadExecutor();
        final SimplePipeline<CommodityInfoTask, String> pipeline =
                new SimplePipeline<>(helperExecutor);

        /*
         * 根据数据库记录生成相应的静态化页面写入到文件中 AbstractPipe
         */
        Pipe<CommodityInfoTask, File> commodityInfoStage = generateCommodityInfoStage();
        pipeline.addAsWorkerThreadBasedPipe(commodityInfoStage, 1);

        // 将生成的静态化传输到nginx上 并行的pipe：AbstractParallelPipe
        Pipe<File, File> stageTransferFile = createFileTransferStage();
        pipeline.addAsWorkerThreadBasedPipe(stageTransferFile, 1);

        return pipeline;
    }

    /**
     * 将数据库中商品的数据结合静态化的页面模板生成一个商品id.html的文件
     *
     * @return 构建生成商品详情的pipe
     */
    private Pipe<CommodityInfoTask, File> generateCommodityInfoStage() {
        Pipe<CommodityInfoTask, File> ret;
        // AbstractPipe类的
        ret = new AbstractPipe<CommodityInfoTask, File>() {

            // 具体执行的业务逻辑
            @Override
            protected File doProcess(CommodityInfoTask task)
                    throws PipeException {
                /*
                 * 将记录写入文件
                 */
                File file;
                // 获取商品详情的模板
                final CommodityDetailTemplate commodityDetailTemplate = CommodityDetailTemplate.getInstance();
                final CommodityInfo commodityInfo = task.commodityInfo;
                try {
                    // 根据模板和数据生成静态化文件
                    file = commodityDetailTemplate.generate(commodityInfo,
                                                            task.targetFileName);
                } catch (IOException e) {
                    throw new PipeException(this, task,
                                            "Failed to save records.", e);
                }
                return file;
            }
        };
        return ret;
    }

    protected Pipe<File, File> createFileTransferStage() {
        Pipe<File, File> ret;
        final String[][] ftpServerConfigs = {{"127.0.0.1"}};

        final ThreadPoolExecutor ftpExecutorService = new ThreadPoolExecutor(1,
                                                                             ftpServerConfigs.length,
                                                                             60,
                                                                             TimeUnit.SECONDS,
                                                                             new ArrayBlockingQueue<>(100));

        // AbstractParallelPipe类
        ret = new AbstractParallelPipe<File, File, File>(
                new SynchronousQueue<>(), ftpExecutorService) {
            @SuppressWarnings("unchecked")
            final Future<FtpUploader>[] ftpClientUtilHolders =
                    new Future[ftpServerConfigs.length];

            @Override
            public void init(PipeContext pipeCtx) {
                super.init(pipeCtx);
                // 构建一组子任务，建立 ftp client和server端的连接，通过承若模式实现
                String[] ftpServerConfig;
                for (int i = 0; i < ftpServerConfigs.length; i++) {
                    ftpServerConfig = ftpServerConfigs[i];
                    // FTPUploaderPromisor 采用承诺模式
                    ftpClientUtilHolders[i] =
                            FtpUploaderPromisor.newFtpUploaderPromise();
                }
            }

            @Override
            protected List<Callable<File>> buildTasks(final File file) {
                // 创建一组并发任务，将指定的文件上传到多个FTP服务器上
                List<Callable<File>> tasks = new LinkedList<>();
                for (Future<FtpUploader> ftpClientUtilHolder
                        : ftpClientUtilHolders) {
                    tasks.add(new FileTransferTask(ftpClientUtilHolder, file));
                }
                return tasks;
            }

            @Override
            protected File combineResults(List<Future<File>> subTaskResults)
                    throws Exception {
                if (0 == subTaskResults.size()) {
                    return null;
                }
                // 组合执行的结果，这里因为ftp的client只有一个同步阻塞等待其中一个执行完即可，然后返回对应的文件数据
                return subTaskResults.get(0).get();
            }

            @Override
            public void shutdown(long timeout, TimeUnit unit) {
                super.shutdown(timeout, unit);
                ftpExecutorService.shutdown();
                try {
                    ftpExecutorService.awaitTermination(timeout, unit);
                } catch (InterruptedException e1) {
                    ;
                }
                for (Future<FtpUploader> ftpClientUtilHolder
                        : ftpClientUtilHolders) {
                    try {
                        ftpClientUtilHolder.get().disconnect();
                    } catch (Exception e) {
                        ;
                    }
                } // end of for
            }// end of shutdown
        };
        return ret;
    }

    private void processCommodityInfos(CommodityDbData commodityDbData,
                                       Pipeline<CommodityInfoTask, String> pipeline) throws Exception {
        CommodityInfo commodityInfo;
        while (commodityDbData.hasNext()) {
            commodityInfo = commodityDbData.next();
            // 通过pipeline来处理每一个商品任务
            pipeline.process(new CommodityInfoTask(commodityInfo, commodityInfo.getId() + ".html"));
        }
    }
}
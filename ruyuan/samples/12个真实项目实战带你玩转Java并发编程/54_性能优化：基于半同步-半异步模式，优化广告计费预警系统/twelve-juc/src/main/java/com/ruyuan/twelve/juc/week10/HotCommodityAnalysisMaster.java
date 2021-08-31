package com.ruyuan.twelve.juc.week10;

import java.io.*;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:电商平台热点商品分析系统-》热点商品分析系统master角色--》分析电商系统的日志文件，去统计热点商品
 **/
public class HotCommodityAnalysisMaster {


    /**
     * 日志文件的根目录
     */
    private final String logFileRootPath;

    /**
     * 每次派发给某个Slave线程的文件个数
     */
    private static final int NUMBER_OF_FILES_FOR_EACH_DISPATCH = 1;

    /**
     * 工作线程的个数
     */
    private static final int WORKER_COUNT = 2;

    /**
     * 构造方法
     *
     * @param logFileRootPath 日志文件目录
     */
    public HotCommodityAnalysisMaster(String logFileRootPath) {
        this.logFileRootPath = logFileRootPath;
    }

    /**
     * 分析热点商品
     *
     * @param fileNamesReader 日志文件字符流
     * @return 热点数据统计结果
     * @throws IOException
     */
    public ConcurrentMap<Long, AtomicInteger> analysisHotCommodity(
            BufferedReader fileNamesReader) throws IOException {
        // 存储各个slave角色的数据
        ConcurrentMap<Long, AtomicInteger> hotCommodityMap =
                new ConcurrentSkipListMap<>();
        // 创建工作者线程
        Worker[] workers = createAndStartWorkers(hotCommodityMap);
        // 指派任务给工作者线程
        dispatchTask(fileNamesReader, workers);
        // 等待工作者线程处理结束
        for (int i = 0; i < WORKER_COUNT; i++) {
            workers[i].terminate();
        }
        // 返回处理结果
        return hotCommodityMap;
    }

    /**
     * 创建slave线程
     *
     * @param hotCommodityMap 热点商品的map
     * @return 结果
     */
    private Worker[] createAndStartWorkers(
            ConcurrentMap<Long, AtomicInteger> hotCommodityMap) {
        Worker[] workers = new Worker[WORKER_COUNT];
        Worker worker;

        for (int i = 0; i < WORKER_COUNT; i++) {
            worker = new Worker(hotCommodityMap);
            workers[i] = worker;
            worker.start();
        }
        return workers;
    }

    /**
     * 将请求分发给指定线程执行
     *
     * @param fileNamesReader
     * @param workers
     * @throws IOException
     */
    private void dispatchTask(BufferedReader fileNamesReader,
                              Worker[] workers) throws IOException {
        String line;
        Set<String> fileNames = new HashSet<>();
        int fileCount = 0;
        int workerIndex = -1;
        BufferedReader logFileReader;
        while ((line = fileNamesReader.readLine()) != null) {
            fileNames.add(line);
            fileCount++;
            if (0 == (fileCount % NUMBER_OF_FILES_FOR_EACH_DISPATCH)) {
                // 满足指定文件 工作者线程间的负载均衡：采用简单的轮询选择worker
                workerIndex = (workerIndex + 1) % WORKER_COUNT;
                logFileReader = makeReaderFrom(fileNames);
                System.out.println("Dispatch " + NUMBER_OF_FILES_FOR_EACH_DISPATCH
                                           + " files to worker:" + workerIndex);
                workers[workerIndex].submitWorkload(logFileReader);
                fileNames = new HashSet<>();
                fileCount = 0;
            }
        }

        // 剩下的文件不足指定个数
        if (fileCount > 0) {
            logFileReader = makeReaderFrom(fileNames);
            workerIndex = (workerIndex + 1) % WORKER_COUNT;
            workers[workerIndex].submitWorkload(logFileReader);
        }
    }

    /**
     * 读取多个文件目录下的数据
     *
     * @param logFileNames 多个文件名
     * @return 文件内容对应的字符流
     */
    private BufferedReader makeReaderFrom(final Set<String> logFileNames) {
        BufferedReader logFileReader;

        InputStream in = new SequenceInputStream(
                new Enumeration<InputStream>() {
                    private Iterator<String> iterator = logFileNames
                            .iterator();

                    @Override
                    public boolean hasMoreElements() {
                        return iterator.hasNext();
                    }

                    @Override
                    public InputStream nextElement() {
                        String fileName = iterator.next();
                        InputStream in = null;
                        try {
                            in = new FileInputStream(logFileRootPath + fileName);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        return in;
                    }

                });
        logFileReader = new BufferedReader(new InputStreamReader(in));
        return logFileReader;
    }

    public String getLogFileRootPath() {
        return logFileRootPath;
    }
}
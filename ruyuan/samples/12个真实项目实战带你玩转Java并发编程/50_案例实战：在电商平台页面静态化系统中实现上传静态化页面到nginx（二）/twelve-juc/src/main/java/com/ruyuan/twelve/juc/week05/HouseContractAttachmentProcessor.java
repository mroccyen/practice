package com.ruyuan.twelve.juc.week05;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:互联网房产交易平台，上传购房合同之类的东西作为附件，当上传到系统之后需要进行索引文件的构建归类方便公司人员来进行查询
 **/
public class HouseContractAttachmentProcessor {

    /**
     * 通道角色 所有购房合同，生产者发送后put进入到通道中 消费者从通道中take后重新构建index
     */
    private final Channel<HouseContractFile> fileChannel = new BlockingQueueChannel<>(new ArrayBlockingQueue<>(200));

    /**
     * 消费者 消费通道中的购房合同后构建索引
     */
    private final AbstractTerminationThread indexingThread = new AbstractTerminationThread() {

        @Override
        protected void doRun() throws InterruptedException {
            HouseContractFile houseContractFile = null;
            houseContractFile = fileChannel.take();
            System.out.println("消费者获取购房合同：" + houseContractFile);
            try {
                builderIndexFile(houseContractFile);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 任务数量-1
                terminationToken.reservations.decrementAndGet();
            }
        }
    };

    /**
     * 上传购房合同的附件
     */
    public void uploadHouseContractAttachment(HouseContractFile houseContractFile) {
        try {
            // 放入到通道的队列中
            System.out.println("生产者上传购房合同：" + houseContractFile);
            fileChannel.put(houseContractFile);
            // 任务数量+1
            indexingThread.terminationToken.reservations.incrementAndGet();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建indexFile文件
     *
     * @param houseContractFile
     */
    private void builderIndexFile(HouseContractFile houseContractFile) throws InterruptedException {
        Thread.sleep(100);

        System.out.println("构建合同：" + houseContractFile + "索引文件完成");
    }

    /**
     * 初始化
     */
    public void init() {
        indexingThread.start();
    }

    /**
     * 关闭
     */
    public void shutdown() {
        indexingThread.terminate();
    }
}
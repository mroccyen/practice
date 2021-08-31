package com.ruyuan.twelve.juc.week09;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:导出Excel的工作线程
 **/
public class ExportWorkThread extends AbstractTerminationThread {

    /**
     * 任务队列
     */
    private final BlockingQueue<String> workQueue;

    /**
     * 下载excel文件的client
     */
    private final DownloadExcelClient downloadExcelClient;

    public ExportWorkThread() {
        this.workQueue = new ArrayBlockingQueue<>(1000);
        downloadExcelClient = new DownloadExcelClient();
        downloadExcelClient.init();
    }

    @Override
    protected void doRun() throws InterruptedException {
        String fileName = workQueue.take();
        try {
            downloadExcelClient.download(fileName);
        } finally {
            terminationToken.reservations.decrementAndGet();
        }
    }

    /**
     * 导出excel文件
     *
     * @param excelFileName 导出excel文件名称
     */
    public void exportFile(String excelFileName) {
        try {
            workQueue.put(excelFileName);
            terminationToken.reservations.incrementAndGet();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
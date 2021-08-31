package com.ruyuan.twelve.juc.week09;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:商业智能BI数据分析系统，从系统导出大量的数据分析excel报表文件
 * 串行线程封闭模式：多个下载的任务放入到一个队列中，后台单个工作线程从队列中获取请求后进行导出
 * <p>
 * SerialThreadConfinement.Serializer
 **/
public class DataAnalysisSystem {

    /**
     * 工作线程
     * <p>
     * SerialThreadConfinement.WorkerThread
     */
    private final ExportWorkThread exportWorkThread;

    public DataAnalysisSystem() {
        this.exportWorkThread = new ExportWorkThread();
    }

    /**
     * 导出Excel文件
     *
     * @param excelFileName excel文件的名称
     */
    public void exportFile(String excelFileName) {
        exportWorkThread.exportFile(excelFileName);
    }

    /**
     * 初始化
     */
    public void init() {
        exportWorkThread.start();
    }

    /**
     * 关闭
     */
    public void shutdown() {
        exportWorkThread.terminate();
    }
}
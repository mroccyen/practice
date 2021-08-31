package com.ruyuan.twelve.juc.week09;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:下载excel文件的client
 **/
public class DownloadExcelClient {

    /**
     * 初始化一个client
     */
    public void init() {
        // 模拟客户端的初始化
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("下载excel文件的客户端初始化完成");
    }

    /**
     * 下载一个excel文件
     *
     * @param fileName
     */
    public void download(String fileName) {
        // 模拟下载excel文件
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("下载：" + fileName + "excel文件成功");
    }
}
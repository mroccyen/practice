package com.ruyuan.twelve.juc.week04;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:默认个人网盘上传的实现类
 **/
public class DefaultCloudUploader implements CloudUploader {

    @Override
    public void init(String cloudServer, String userName, String password, String serverDir) throws Exception {
        // 假设睡眠1s
        Thread.sleep(1000);
        System.out.println("与远程个人网盘的连接建立完成");
    }

    @Override
    public void upload(FileInfo fileInfo) throws Exception {
        // 上次文件 模拟睡眠100ms
        Thread.sleep(100);
        System.out.println("上传文件：" + fileInfo.getFileName() + "完成");

    }

    @Override
    public void disconnect() {
        System.out.println("关闭与远程网盘的连接");
    }
}
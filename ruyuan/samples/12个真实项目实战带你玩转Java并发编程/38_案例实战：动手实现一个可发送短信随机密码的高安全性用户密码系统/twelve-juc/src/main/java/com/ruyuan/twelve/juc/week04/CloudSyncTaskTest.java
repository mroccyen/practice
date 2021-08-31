package com.ruyuan.twelve.juc.week04;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:个人本地文件和云盘的同步测试
 **/
public class CloudSyncTaskTest {

    public static void main(String[] args) throws InterruptedException {
        CloudSyncConfig cloudSyncConfig = new CloudSyncConfig();
        cloudSyncConfig.setCloudAddress("www.baidu.com");
        cloudSyncConfig.setUsername("ruyuan");
        cloudSyncConfig.setPassword("ruyuan");
        cloudSyncConfig.setServerDir("/ruyuan");

        CloudSyncTask cloudSyncTask = new CloudSyncTask(cloudSyncConfig);

        // 正常应该是client端一个线程池来和云盘进行数据的同步，这里模拟测试一下
        Thread thread = new Thread(cloudSyncTask);
        thread.start();

        thread.join();
    }
}
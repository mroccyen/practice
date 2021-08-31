package com.ruyuan.twelve.juc.week04;


import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:个人云盘本地和远程同步定时任务
 **/
public class CloudSyncTask implements Runnable {

    /**
     * 个人云盘同步的配置信息
     */
    private final CloudSyncConfig cloudSyncConfig;

    public CloudSyncTask(CloudSyncConfig cloudSyncConfig) {
        this.cloudSyncConfig = cloudSyncConfig;
    }

    @Override
    public void run() {
        // 初始化创建本地client和server端的ftp连接
        // 因为与云盘建立网络连接是很慢的一个过程，这里异步化的去执行，云盘任务继续做别的事情
        Future<CloudUploader> cloudUploaderFuture = CloudUploaderPromisor.newCloudUploaderPromise(cloudSyncConfig);

        // 扫描本地的文件
        StorageManager storageManager = StorageManager.getInstance();
        List<FileInfo> fileInfos = storageManager.scanLocalFile();
        System.out.println("扫描本地需要同步的文件完成");

        // 获取初始化好的云盘client
        CloudUploader cloudUploader = null;
        try {
            System.out.println("同步阻塞等待云盘连接建立进行文件上传");
            cloudUploader = cloudUploaderFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if (cloudUploader == null) {
            return;
        }

        // 开始同步文件到云盘上去
        syncFile(cloudUploader, fileInfos);
        System.out.println("所有文件文件上传完成");

        // 都上传完成，关闭连接
        cloudUploader.disconnect();
        System.out.println("关闭云盘的连接");
    }

    private void syncFile(CloudUploader cloudUploader, List<FileInfo> fileInfos) {
        fileInfos.forEach(fileInfo -> {
            try {
                cloudUploader.upload(fileInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
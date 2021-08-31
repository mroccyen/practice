package com.ruyuan.twelve.juc.week04;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:包装异步任务的凭据对象，创建一个与个人云盘上传的client的promise
 **/
public class CloudUploaderPromisor {

    /**
     * 通过云盘上传的promisor组件构建一个promise凭据
     *
     * @param cloudSyncConfig 云盘同步时的配置信息
     * @return 云盘同步的promise凭据对象
     */
    public static Future<CloudUploader> newCloudUploaderPromise(CloudSyncConfig cloudSyncConfig) {
        FutureTask<CloudUploader> futureTask = new FutureTask<>(() -> {
            DefaultCloudUploader defaultCloudUploader = new DefaultCloudUploader();
            System.out.println("开始创建云盘的连接");
            defaultCloudUploader.init(cloudSyncConfig.getCloudAddress(),
                                      cloudSyncConfig.getUsername(),
                                      cloudSyncConfig.getPassword(),
                                      cloudSyncConfig.getServerDir());
            return defaultCloudUploader;
        });

        // 这里模拟一下后台线程去初始化client，正常应该是线程池的方式
        new Thread(futureTask).start();

        return futureTask;
    }
}
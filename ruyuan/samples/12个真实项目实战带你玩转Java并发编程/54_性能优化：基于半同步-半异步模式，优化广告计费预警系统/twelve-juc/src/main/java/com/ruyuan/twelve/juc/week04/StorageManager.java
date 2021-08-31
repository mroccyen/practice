package com.ruyuan.twelve.juc.week04;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:存储管理组件
 **/
public class StorageManager {

    private static StorageManager INSTANCE = new StorageManager();

    private StorageManager() {

    }

    public static StorageManager getInstance() {
        return INSTANCE;
    }

    /**
     * 扫描本地需要同步的文件
     *
     * @return
     */
    public List<FileInfo> scanLocalFile() {
        // 模拟需要同步到个人云盘的文件
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFile("abc".getBytes());
        fileInfo.setFileName("abc.txt");
        fileInfo.setFileSize(fileInfo.getFile().length);
        return Collections.singletonList(fileInfo);
    }
}
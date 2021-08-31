package com.ruyuan.twelve.juc.week11;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class FileTransferTask implements Callable<File> {
    public final Future<FtpUploader> ftpUtilHodler;
    public final File                file2Transfer;

    public FileTransferTask(Future<FtpUploader> ftpUtilHodler,
                            File file2Transfer) {
        this.ftpUtilHodler = ftpUtilHodler;
        this.file2Transfer = file2Transfer;
    }

    @Override
    public File call() throws Exception {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(file2Transfer.getName());
        // 上次指定文件
        ftpUtilHodler.get().upload(fileInfo);
        return file2Transfer;
    }
}

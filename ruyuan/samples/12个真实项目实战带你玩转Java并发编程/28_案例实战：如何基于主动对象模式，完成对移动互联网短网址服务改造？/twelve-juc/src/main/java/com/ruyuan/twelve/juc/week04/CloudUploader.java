package com.ruyuan.twelve.juc.week04;

/**
 * 云盘上传的uploader接口组件
 *
 * @author <a href="mailto:little@163.com">little</a>
 */
public interface CloudUploader {

    /**
     * 初始化与云盘上传的client
     *
     * @param cloudServer server端的地址
     * @param ftpUserName 账号
     * @param password    密码
     * @param serverDir   上次到个人云盘的目录
     * @throws Exception
     */
    void init(String cloudServer, String ftpUserName, String password,
              String serverDir) throws Exception;

    /**
     * 上传文件
     *
     * @param fileInfo 文件
     * @throws Exception
     */
    void upload(FileInfo fileInfo) throws Exception;

    /**
     * 关闭client的连接
     */
    void disconnect();
}
package com.ruyuan.twelve.juc.week04;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:云盘同步配置类
 **/
public class CloudSyncConfig {

    /**
     * server端的地址
     */
    private String cloudAddress;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 上次到个人网盘的目录
     */
    private String serverDir;

    public String getCloudAddress() {
        return cloudAddress;
    }

    public void setCloudAddress(String cloudAddress) {
        this.cloudAddress = cloudAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServerDir() {
        return serverDir;
    }

    public void setServerDir(String serverDir) {
        this.serverDir = serverDir;
    }
}
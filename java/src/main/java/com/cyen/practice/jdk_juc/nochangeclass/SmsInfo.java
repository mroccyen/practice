package com.cyen.practice.jdk_juc.nochangeclass;

/**
 * @author qingshanpeng
 * @date 2021/8/21 10:39
 * @since 标果工厂-托曼尼
 */
public class SmsInfo {

    private String url;

    private Long dataSize;

    public SmsInfo(String url, Long dataSize) {
        this.url = url;
        this.dataSize = dataSize;
    }

    public SmsInfo(SmsInfo smsInfo) {
        this.url = smsInfo.getUrl();
        this.dataSize = smsInfo.getDataSize();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getDataSize() {
        return dataSize;
    }

    public void setDataSize(Long dataSize) {
        this.dataSize = dataSize;
    }
}

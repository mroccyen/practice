package com.ruyuan.twelve.juc.week06;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:文章访问信息
 **/
public class ArticleAccessInfo {

    /**
     * 请求的短网址
     */
    private String shortUrl;

    /**
     * 请求的来源的ip
     */
    private String originIp;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getOriginIp() {
        return originIp;
    }

    public void setOriginIp(String originIp) {
        this.originIp = originIp;
    }

    @Override
    public String toString() {
        return "ArticleAccessInfo{" +
                "shortUrl='" + shortUrl + '\'' +
                ", originIp='" + originIp + '\'' +
                '}';
    }
}
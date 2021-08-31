package com.ruyuan.twelve.juc.week06;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:测试文章的访问
 **/
public class ArticleAccessTest {

    public static void main(String[] args) {
        ArticleAccessHandler articleAccessHandler = new ArticleAccessHandler();
        ArticleAccessInfo articleAccessInfo = new ArticleAccessInfo();
        articleAccessInfo.setOriginIp("127.0.0.1");
        articleAccessInfo.setShortUrl("baidu.com");
        articleAccessHandler.intercept(articleAccessInfo);
    }
}
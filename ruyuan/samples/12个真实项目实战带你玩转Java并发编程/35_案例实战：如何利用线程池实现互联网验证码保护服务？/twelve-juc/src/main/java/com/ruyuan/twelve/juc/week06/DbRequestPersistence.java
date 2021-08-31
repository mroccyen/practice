package com.ruyuan.twelve.juc.week06;

import java.io.IOException;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:磁盘持久化存储请求
 **/
public class DbRequestPersistence implements RequestPersistence {

    @Override
    public void store(ArticleAccessInfo articleAccessInfo) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("写请求：" + articleAccessInfo + "到数据库完成");
    }

    @Override
    public void close() throws IOException {
        // 什么都不用作
    }
}
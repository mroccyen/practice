package com.ruyuan.twelve.juc.week06;

import java.io.Closeable;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:请求持久化
 **/
public interface RequestPersistence extends Closeable {

    /**
     * 持久化存储访问文章的请求
     *
     * @param articleAccessInfo 访问的文章信息
     */
    void store(ArticleAccessInfo articleAccessInfo);
}
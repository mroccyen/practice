package com.ruyuan.twelve.juc.week09.common;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:抽象的任务执行处理接口
 **/
public interface TaskProcessor<T, V> {

    /**
     * 处理具体的业务逻辑
     *
     * @param task 任务
     * @return
     */
    V doProcessor(T task);
}
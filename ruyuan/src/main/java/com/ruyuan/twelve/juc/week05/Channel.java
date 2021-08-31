package com.ruyuan.twelve.juc.week05;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:通道参与者抽象
 **/
public interface Channel<T> {

    /**
     * 从通道中获取一个产品 这里即获取一个购房合同附件
     *
     * @return
     */
    T take() throws InterruptedException;

    /**
     * 往通道中添加一个产品  即添加上次一个购房合同的附件
     *
     * @param product
     */
    void put(T product) throws InterruptedException;
}
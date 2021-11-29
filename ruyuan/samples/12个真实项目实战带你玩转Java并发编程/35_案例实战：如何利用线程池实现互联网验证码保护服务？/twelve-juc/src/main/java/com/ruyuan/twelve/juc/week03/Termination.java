package com.ruyuan.twelve.juc.w03_two_phase_termination;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:中断顶层接口
 **/
public interface Termination {

    /**
     * 请求终止线程
     */
    void terminate();
}

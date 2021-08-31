/*
授权声明：
本源码系《Java多线程编程实战指南（设计模式篇）第2版》一书（ISBN：978-7-121-38245-1，以下称之为“原书”）的配套源码，
欲了解本代码的更多细节，请参考原书。
本代码仅为原书的配套说明之用，并不附带任何承诺（如质量保证和收益）。
以任何形式将本代码之部分或者全部用于营利性用途需经版权人书面同意。
将本代码之部分或者全部用于非营利性用途需要在代码中保留本声明。
任何对本代码的修改需在代码中以注释的形式注明修改人、修改时间以及修改内容。
本代码可以从以下网址下载：
https://github.com/Viscent/javamtp
http://www.broadview.com.cn/38245
*/

package com.ruyuan.twelve.juc.week12.template;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;


/**
 * 测试半同步/半异步的template模板
 *
 * @author little
 */
public class SampleAsyncTask {

    public static void main(String[] args) {
        // 创建
        XAsyncTask task = new XAsyncTask();
        List<Future<String>> results = new LinkedList<>();

        try {
            results.add(task.doSomething("Half-sync/Half-async", 1));

            results.add(task.doSomething("Pattern", 2));

            for (Future<String> result : results) {
                System.out.println(result.get());
            }

            Thread.sleep(200);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    /**
     * 实现通用模板的抽象类
     */
    private static class XAsyncTask extends AsyncTask<String> {

        /**
         * 实现耗时较长的任务（同步任务），通过后台的线程池来执行
         *
         * @param params 客户端代码调用dispatch方法时所传递的参数列表
         * @return
         */
        @Override
        protected String doInBackground(Object... params) {
            String message = (String) params[0];
            int sequence = (Integer) params[1];
            System.out.println("doInBackground:" + message);
            return "message " + sequence + ":" + message;
        }

        /**
         * 实现耗时较短的任务
         *
         * @param params 客户端代码调用dispatch方法时所传递的参数列表
         */
        @Override
        protected void onPreExecute(Object... params) {
            String message = (String) params[0];
            int sequence = (Integer) params[1];
            System.out.println("onPreExecute:[" + sequence + "]" + message);
        }

        /**
         * 执行的具体业务逻辑
         *
         * @param message  消息内容
         * @param sequence 唯一编码
         * @return
         */
        public Future<String> doSomething(String message, int sequence) {
            if (sequence < 0) {
                throw new IllegalArgumentException(
                        "Invalid sequence:" + sequence);
            }
            // 讲请求转发出去
            // 1.异步任务层执行，耗时较短的内容，将请求放入到队列中
            // 2.同步任务从队列中获取任务，然后在执行耗时较长的任务
            return this.dispatch(message, sequence);
        }
    }
}
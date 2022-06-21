package com.cyen.practice.jdk_juc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qingshanpeng
 * @date 2022/6/20 17:43
 * @since 标果工厂
 */
public class MyInheritableThreadLocal<T> extends InheritableThreadLocal<T> {

    static InheritableThreadLocal<Map<MyInheritableThreadLocal, Object>> holder = new InheritableThreadLocal<Map<MyInheritableThreadLocal, Object>>() {
        @Override
        protected Map<MyInheritableThreadLocal, Object> childValue(Map<MyInheritableThreadLocal, Object> parentValue) {
            return new ConcurrentHashMap<>(parentValue);
        }

        @Override
        protected Map<MyInheritableThreadLocal, Object> initialValue() {
            return new ConcurrentHashMap<>();
        }
    };

    @Override
    public T get() {
        T t = super.get();
        addHolder();
        return t;
    }

    @Override
    public void set(T value) {
        super.set(value);
        System.out.println(String.format("线程名称-%s, 设置的变量值=%s", Thread.currentThread().getName(), value));
        addHolder();
    }

    private void addHolder() {
        holder.get().put(this, 1);
    }

    static class MyTask implements Runnable {

        private Map<MyInheritableThreadLocal, Object> holder;
        private Runnable runnable;

        MyTask(Map<MyInheritableThreadLocal, Object> holder, Runnable runnable) {
            Map<MyInheritableThreadLocal, Object> holderTemp = new ConcurrentHashMap<>();
            for (Map.Entry<MyInheritableThreadLocal, Object> entry : holder.entrySet()) {
                MyInheritableThreadLocal key = entry.getKey();
                Object o = key.get();
                holderTemp.put(key, o);
            }
            this.holder = holderTemp;
            this.runnable = runnable;
        }

        @Override
        public void run() {
            for (Map.Entry<MyInheritableThreadLocal, Object> entry : holder.entrySet()) {
                MyInheritableThreadLocal key = entry.getKey();
                Object value = entry.getValue();
                key.set(value);
                System.out.println(String.format("子线程名称-%s, 被设置的变量值=%s", Thread.currentThread().getName(), value));
            }
            runnable.run();
        }
    }

    static ThreadLocal<Integer> threadLocal = new MyInheritableThreadLocal<>();
    private static ExecutorService executorService1 = Executors.newFixedThreadPool(5);
    private static ExecutorService executorService2 = Executors.newFixedThreadPool(5);
    private static AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) {
        while (true) {
            executorService1.execute(() -> {
                if (i.get() < 10) {
                    int andAdd = i.getAndAdd(1);
                    threadLocal.set(andAdd);
                    executorService2.execute(new MyTask(holder.get(), () -> {
                        //System.out.println(String.format("子线程名称-%s, 变量值=%s", Thread.currentThread().getName(), threadLocal.get()));
                        System.out.println(String.format("变量值=%s", threadLocal.get()));
                    }));
                }
            });
        }
    }
}

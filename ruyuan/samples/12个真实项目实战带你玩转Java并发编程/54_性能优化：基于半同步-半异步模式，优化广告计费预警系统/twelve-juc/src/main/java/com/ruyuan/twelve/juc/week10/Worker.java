package com.ruyuan.twelve.juc.week10;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:模式角色：Master-Slave.Slave slave角色  统计商品的访问量
 **/
public class Worker extends AbstractTerminationThread {

    /**
     * 存在slave角色执行完后的数据信息 key：商品id value：访问量个数
     */
    private final ConcurrentMap<Long, AtomicInteger> hotCommodityMap;

    /**
     * 每个slave线程需要执行的任务
     */
    private final BlockingQueue<BufferedReader> workQueue;

    public Worker(ConcurrentMap<Long, AtomicInteger> hotCommodityMap) {
        this.hotCommodityMap = hotCommodityMap;
        workQueue = new ArrayBlockingQueue<>(100);
    }

    /**
     * 提交任务给指定的工作线程
     *
     * @param task
     */
    public void submitWorkload(BufferedReader task) {
        try {
            workQueue.put(task);
            terminationToken.reservations.incrementAndGet();
        } catch (InterruptedException e) {
            // 什么也不做
        }
    }

    @Override
    protected void doRun() throws Exception {
        BufferedReader logFileReader = workQueue.take();

        String interfaceLogRecord;
        try {
            while ((interfaceLogRecord = logFileReader
                    .readLine()) != null) {
                LogInfo logInfo = JSON.parseObject(interfaceLogRecord, LogInfo.class);
                System.out.println("读取到日志文件的内容：" + logInfo);

                // 统计数量
                if (Objects.equals(logInfo.getOperationType(), OperationTypeEnum.SELECT.getOperationType())) {
                    // 查询操作
                    Long commodityId = logInfo.getCommodityId();
                    AtomicInteger hotCommodityCount = hotCommodityMap.putIfAbsent(commodityId, new AtomicInteger(1));
                    if (Objects.isNull(hotCommodityCount)) {
                        hotCommodityCount = new AtomicInteger();
                    }
                    hotCommodityCount.incrementAndGet();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            terminationToken.reservations.decrementAndGet();
            logFileReader.close();
        }
    }

}
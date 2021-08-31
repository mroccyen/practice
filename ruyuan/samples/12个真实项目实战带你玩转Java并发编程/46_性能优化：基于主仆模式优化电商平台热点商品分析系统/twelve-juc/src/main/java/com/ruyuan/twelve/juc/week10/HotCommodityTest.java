package com.ruyuan.twelve.juc.week10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:热点商品测试类
 **/
public class HotCommodityTest {

    public static void main(String[] args) throws IOException {
        HotCommodityAnalysisMaster hotCommodityAnalysisMaster = new HotCommodityAnalysisMaster("/Users/zhouzhihui/Desktop/workspace/twelve-juc/src/main/java/com/ruyuan/twelve/juc/day12/");
        BufferedReader fileNamesReader = new BufferedReader(new InputStreamReader(new FileInputStream(hotCommodityAnalysisMaster.getLogFileRootPath() + "filename.log")));
        ConcurrentMap<Long, AtomicInteger> result = hotCommodityAnalysisMaster.analysisHotCommodity(fileNamesReader);

        // 正常是每隔一段时间去查询一次统计出来的数据
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * 统计的商品数量：
         * key==》value
         * 商品id 访问次数
         */
        for (Long commodityId : result.keySet()) {
            System.out.println(commodityId + "==>" + result.get(commodityId).get());
        }
    }
}
package com.ruyuan.twelve.juc.week11;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 商品数据库查询
 *
 * @author Little
 */
public class CommodityDbData {

    /**
     * 商品数量
     */
    private AtomicInteger commodityNumber = new AtomicInteger();

    /**
     * 是否有下一条
     *
     * @return
     */
    public boolean hasNext() {
        return commodityNumber.get() <= 2;
    }

    /**
     * 获取下一条数据
     *
     * @return
     */
    public CommodityInfo next() {
        return createCommodityInfo(commodityNumber.getAndIncrement());
    }

    private CommodityInfo createCommodityInfo(int commodityId) {
        CommodityInfo commodityInfo = new CommodityInfo();
        commodityInfo.setId((long) commodityId);
        commodityInfo.setName("测试商品-" + commodityId);
        commodityInfo.setPrice(BigDecimal.TEN);
        commodityInfo.setMainUrl("wwww.baidu.com");
        commodityInfo.setAddress("上海");
        return commodityInfo;
    }
}

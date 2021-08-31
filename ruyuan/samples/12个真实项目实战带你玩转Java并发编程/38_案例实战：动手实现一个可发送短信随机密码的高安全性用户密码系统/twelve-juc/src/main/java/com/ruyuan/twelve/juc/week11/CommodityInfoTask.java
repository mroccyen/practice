package com.ruyuan.twelve.juc.week11;

/**
 * 商品详细信息静态化的任务
 *
 * @author little
 */
public class CommodityInfoTask {

    /**
     * 商品信息
     */
    public final CommodityInfo commodityInfo;

    /**
     * 生成的静态化页面的文件名称
     */
    public final String targetFileName;

    public CommodityInfoTask(CommodityInfo commodityInfo, String targetFileName) {
        this.commodityInfo = commodityInfo;
        this.targetFileName = targetFileName;
    }

}

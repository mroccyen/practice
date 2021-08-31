

package com.ruyuan.twelve.juc.week11;

import java.io.File;
import java.io.IOException;


/**
 * 商品详情的模板
 */
public class CommodityDetailTemplate {

    private static final CommodityDetailTemplate INSTANCE = new CommodityDetailTemplate();

    private static final String baseDir = "";

    // 私有构造器
    private CommodityDetailTemplate() {

    }

    public static CommodityDetailTemplate getInstance() {
        return INSTANCE;
    }

    public File generate(CommodityInfo commodityInfo, String targetFileName)
            throws IOException {
        // 这里模拟直接生成一个file文件 数据就不进行填充
        return new File(targetFileName);
    }

}

package com.ruyuan.twelve.juc.week10;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:日志信息
 **/
public class LogInfo {

    /**
     * 日志级别 比如 info
     */
    private String level;

    /**
     * 操作类型 比如 select操作
     */
    private String operationType;

    /**
     * 商品id 比如10
     */
    private Long commodityId;

    /**
     * 操作时间
     */
    private String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    @Override
    public String toString() {
        return "LogInfo{" +
                "level='" + level + '\'' +
                ", operationType='" + operationType + '\'' +
                ", commodityId=" + commodityId +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
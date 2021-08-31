package com.ruyuan.twelve.juc.week12;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:报警信息
 **/
public class AlarmInfo {


    /**
     * 告警类型的标识
     */
    private String id;

    /**
     * 广告主
     */
    private String advertiser;

    /**
     * 广告主的联系方式
     */
    private String phoneNumber;

    /**
     * 报警类型
     */
    private Integer alarmType;

    public AlarmInfo(String id, String advertiser, String phoneNumber, Integer alarmType) {
        this.id = id;
        this.advertiser = advertiser;
        this.phoneNumber = phoneNumber;
        this.alarmType = alarmType;
    }

    public String getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AlarmInfo{" +
                "id='" + id + '\'' +
                ", advertiser='" + advertiser + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", alarmType=" + alarmType +
                '}';
    }

    public String getUniqueIdByAlarmType(AlarmType alarmType) {
        return this.getId() + "-" + alarmType;
    }
}
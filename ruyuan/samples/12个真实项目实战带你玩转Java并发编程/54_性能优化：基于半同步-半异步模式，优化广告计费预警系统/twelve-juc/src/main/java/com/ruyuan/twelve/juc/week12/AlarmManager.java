package com.ruyuan.twelve.juc.week12;


/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:
 * 案例的背景：广告计费智能预警系统，广告计费的时候，如果发现广告主钱快没了，就智能预警通知他
 * 相当于半同步/半异步模式的asyncTask
 **/
public class AlarmManager {

    /**
     * 是否关闭
     */
    private volatile boolean shutdownRequested = false;

    /**
     * 单例对象
     */
    private static final AlarmManager INSTANCE = new AlarmManager();

    /**
     * 上报广告主欠费报警信息的工作线程
     */
    private final AlarmSendingThread alarmSendingThread;

    /**
     * 私有发构造方法
     */
    private AlarmManager() {
        alarmSendingThread = new AlarmSendingThread();
    }

    /**
     * 获取单例对象
     *
     * @return 报警功能管理组件
     */
    public static AlarmManager getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     */
    public void init() {
        alarmSendingThread.start();
    }

    /**
     * 发送机器告警信息
     *
     * @param alarmType   告警类型 {@link AlarmType}
     * @param id          告警唯一id
     * @param advertiser  广告系统用户
     * @param phoneNumber 广告系统用户手机号
     * @return 当前机器告警重复的数量，如果是-1表示机器告警管理组件已经关闭
     */
    public int sendAlarm(AlarmType alarmType, String id, String advertiser, String phoneNumber) {
        AlarmInfo alarmInfo = new AlarmInfo(id, advertiser, phoneNumber, alarmType.getAlarmType());
        // 重复提交的数量
        int duplicateSubmissionCount = 0;
        try {
            duplicateSubmissionCount = alarmSendingThread.sendAlarm(alarmInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return duplicateSubmissionCount;
    }

    /**
     * 关闭机器上报功能
     */
    public synchronized void shutdown() {
        if (shutdownRequested) {
            throw new IllegalStateException("shutdown already requested!");
        }

        // 关闭告警后台线程
        alarmSendingThread.terminate();
        shutdownRequested = true;
    }
}
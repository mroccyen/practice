package com.ruyuan.twelve.juc.week09.common;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:
 **/
public class Task {

    /**
     * 任务id
     */
    private String id;

    /**
     * 任务内容
     */
    private String content;

    public Task(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
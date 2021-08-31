package com.ruyuan.twelve.juc.week06;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:公共的响应体
 **/
public class CommonResponse<T> {

    private static final String SUCCESS_CODE = "200";

    private static final String SUCCESS_MESSAGE = "成功";

    private static final String ERROR_CODE = "500";

    private static final String ERROR_MESSAGE = "失败";

    private String resCode;

    private String resMessage;

    private T data;

    public static CommonResponse success(String data) {
        CommonResponse<Object> response = new CommonResponse<>();
        response.setResCode(SUCCESS_CODE);
        response.setResMessage(SUCCESS_MESSAGE);
        response.setData(data);
        return response;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
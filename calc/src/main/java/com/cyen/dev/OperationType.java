package com.cyen.dev;

/**
 * @author qingsp
 * @date: 2019-05-16
 */
public enum OperationType {

    ADDITION("+", "加"),
    SUBTRACTION("-", "减"),
    MULTIPLICATION("*", "乘"),
    DIVISION("/", "除"),
    LPARENTHESIS("(", "左括号"),
    RPARENTHESIS(")", "右括号"),
    DOT(".", "小数点");

    private String sign;
    private String desc;

    OperationType(String sign, String desc) {
        this.sign = sign;
        this.desc = desc;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

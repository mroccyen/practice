package com.cyen.dev;

/**
 * @author qingsp
 * @date: 2019-05-16
 */
public enum OperationType {

    /**
     * 加
     */
    ADDITION('+', "加"),

    /**
     * 减
     */
    SUBTRACTION('-', "减"),

    /**
     * 乘
     */
    MULTIPLICATION('*', "乘"),

    /**
     * 除
     */
    DIVISION('/', "除"),

    /**
     * 左括号
     */
    LPARENTHESIS('(', "左括号"),

    /**
     * 右括号
     */
    RPARENTHESIS(')', "右括号"),

    /**
     * 小数点
     */
    DOT('.', "小数点");

    private char sign;
    private String desc;

    OperationType(char sign, String desc) {
        this.sign = sign;
        this.desc = desc;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 加号或者减号
     *
     * @param c
     * @return
     */
    public static boolean isAddAndSub(char c) {
        if (c == ADDITION.sign
                || c == SUBTRACTION.sign) {
            return true;
        }
        return false;
    }

    /**
     * 乘号或者除号
     *
     * @param c
     * @return
     */
    public static boolean isMulAndDiv(char c) {
        if (c == MULTIPLICATION.sign
                || c == DIVISION.sign) {
            return true;
        }
        return false;
    }

    /**
     * 左括号
     *
     * @param c
     * @return
     */
    public static boolean isLeftParenthesis(char c) {
        return c == LPARENTHESIS.sign;
    }

    /**
     * 右括号
     *
     * @param c
     * @return
     */
    public static boolean isRightParenthesis(char c) {
        return c == RPARENTHESIS.sign;
    }
}

package com.cyen.dev.calcutor;

import com.cyen.dev.calcutor.cache.CacheHandler;

import java.util.Scanner;

/**
 * @author qingsp
 * @date: 2019-05-16
 */
public class OperationService {

    /**
     * 读取控制台内容
     *
     * @param tip
     * @return
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (ipt != "" || ipt != null) {
                return ipt;
            }
        }
        throw new NullPointerException();
    }

    public static void main(String[] args) {
        CacheHandler handler = new CacheHandler();
        double result = handler.calc(scanner("计算表达式"));
        System.out.println("计算结果为：" + result);
    }
}

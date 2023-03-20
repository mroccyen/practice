package com.cyen.practice.algorithm;

import java.util.ArrayList;

/**
 * @author qingshanpeng
 * @date 2021/8/28 16:03
 * @since 标果工厂-托曼尼pro
 */
public class Test1 {
    public static void main(String[] args) {
        //ArrayList<Integer> r = extraNum("kur1su alan0233");
        //System.out.println(r);
        String cmbchina = maxDictionaryOrder("cmbchinx");
        System.out.println(cmbchina);
    }

    public static ArrayList<Integer> extraNum(String s) {
        ArrayList<Integer> result = new ArrayList<>();
        char[] chars = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char a = chars[i];
            if (a == '0'
                    || a == '1'
                    || a == '2'
                    || a == '3'
                    || a == '4'
                    || a == '5'
                    || a == '6'
                    || a == '7'
                    || a == '8'
                    || a == '9') {
                builder.append(a);
            } else {
                if (builder.length() > 0) {
                    int ii = Integer.parseInt(builder.toString());
                    result.add(ii);
                    builder = new StringBuilder();
                }
            }
            if (i == chars.length - 1) {
                if (builder.length() > 0) {
                    int ii = Integer.parseInt(builder.toString());
                    result.add(ii);
                    builder = new StringBuilder();
                }
            }
        }
        return result;
    }

    public static String maxDictionaryOrder(String s) {
        if (s.length() == 0) {
            return "";
        }
        char[] chars = s.toCharArray();
        String result = "";
        return maxDictionaryOrder(chars, 0, result);
    }

    private static String maxDictionaryOrder(char[] chars, int index, String result) {
        if (index == chars.length - 1) {
            return result + chars[chars.length - 1];
        }
        char max = chars[index];
        int maxIndex = index;
        for (int i = index; i < chars.length; i++) {
            char a = chars[i];
            if (a > max) {
                max = a;
                maxIndex = i;
            }
        }
        result += max;
        if (maxIndex == chars.length - 1) {
            return result;
        }
        return maxDictionaryOrder(chars, maxIndex + 1, result);
    }
}

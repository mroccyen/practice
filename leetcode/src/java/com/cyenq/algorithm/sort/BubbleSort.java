package com.cyenq.algorithm.sort;

/**
 * 冒泡排序
 *
 * @author qingsp
 * @date 2019-07-13 21:45
 */
public class BubbleSort {
    private static void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int t = arr[j];
                    arr[j] = arr[i];
                    arr[i] = t;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 12, 30, 8, 4, 13, 7};
        bubbleSort(arr);
        for (int a : arr) {
            System.out.println(a);
        }
    }
}

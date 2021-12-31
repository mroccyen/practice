package com.cyen.practice.algorithm.sort;

/**
 * 希尔排序
 *
 * @author qingsp
 * @date 2019-07-15
 */
public class ShellSort {
    private static void shellSort(int[] arr, int d) {
        if (d < 1) {
            return;
        }
        for (int i = d; i < arr.length; i++) {
            int j = i - d;
            //待插入的值
            int val = arr[i];
            while (j >= 0 && arr[j] > val) {
                arr[j + d] = arr[j];
                j -= d;
            }
            if (j != i - d) {
                arr[j + d] = val;
            }
        }
        shellSort(arr, d / 2);
    }

    public static void main(String[] args) {
        int[] arr = {3, 8, 4, 20, 7, 2, 1, 15, 12, 11, 18, 0, 50, 45, 23};
        shellSort(arr, arr.length / 2);
        for (int a : arr) {
            System.out.println(a);
        }
    }
}

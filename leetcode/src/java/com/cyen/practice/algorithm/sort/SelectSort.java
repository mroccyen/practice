package com.cyen.practice.algorithm.sort;

/**
 * 选择排序
 *
 * @author qingsp
 * @date 2019-07-13 21:54
 */
public class SelectSort {
    private static void selectSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int minIndex;
        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                //依次和最小值比较，满足条件重新设置最小值的位置
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int t = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = t;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 8, 3, 2, 10, 9, 20, 15};
        selectSort(arr);
        for (int a : arr) {
            System.out.println(a);
        }
    }
}

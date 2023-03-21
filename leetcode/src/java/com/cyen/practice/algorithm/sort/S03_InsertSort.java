package com.cyen.practice.algorithm.sort;

/**
 * 插入排序
 *
 * @author qingsp
 * @date 2019-07-13 22:05
 */
public class S03_InsertSort {
    private static void insertSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            int insertVal = arr[i];
            //查找需要插入的位置
            while (j > 0 && insertVal < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = insertVal;
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 8, 4, 10, 7, 2, 1, 15, 12, 11, 18};
        insertSort(arr);
        for (int a : arr) {
            System.out.println(a);
        }
    }
}

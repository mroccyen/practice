package com.cyen.practice.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author qingshanpeng
 * @date 2023/3/21 16:14
 */
public class S06_MergeSort {
    private static void merge(int[] arr, int low, int mid, int high) {
        int length = high - low + 1;
        int[] arrTmp = new int[length];
        int index = 0;
        int i = low;
        int j = mid + 1;
        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                arrTmp[index] = arr[i];
                i++;
            } else {
                arrTmp[index] = arr[j];
                j++;
            }
            System.out.println(Arrays.toString(Arrays.stream(arrTmp).toArray()));
            index++;
        }
        while (i <= mid) {
            arrTmp[index] = arr[i];
            index++;
            i++;
            System.out.println(Arrays.toString(Arrays.stream(arrTmp).toArray()));
        }
        while (j <= high) {
            arrTmp[index] = arr[j];
            index++;
            j++;
            System.out.println(Arrays.toString(Arrays.stream(arrTmp).toArray()));
        }
        int m = 0;
        for (int k = low; k < high + 1; k++) {
            arr[k] = arrTmp[m++];
        }
    }

    private static void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 8, 4, 20, 7, 2, 1, 15, 12, 11, 18};
        mergeSort(arr, 0, arr.length - 1);
        for (int a : arr) {
            System.out.println(a);
        }

//        int[] arr = {3, 4, 7, 8, 9, 10, 1, 2, 5, 11, 18};
//        int mid = arr.length / 2;
//        merge(arr, 0, mid, arr.length - 1);
//        for (int a : arr) {
//            System.out.println(a);
//        }
    }
}

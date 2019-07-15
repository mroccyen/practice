package com.cyenq.algorithm.sort;

/**
 * 快速排序
 *
 * @author qingsp
 * @date: 2019-07-15
 */
public class QuickSort {

    private static int partition(int[] arr, int left, int right) {
        //版本1
        /*
        int pivot = arr[left];
        int pivotIndex = left;
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            swap(arr, left, right);
        }
        swap(arr, pivotIndex, left);
        */
        //版本2
        int pivot = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }

    private static void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = partition(arr, left, right);
        sort(arr, left, mid - 1);
        sort(arr, mid + 1, right);
    }

    private static void swap(int[] arr, int left, int right) {
        int t = arr[left];
        arr[left] = arr[right];
        arr[right] = t;
    }

    private static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        sort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr = {3, 8, 4, 20, 7, 2, 1, 15, 12, 11, 18};
        quickSort(arr);
        for (int a : arr) {
            System.out.println(a);
        }
    }
}

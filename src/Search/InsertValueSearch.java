package Search;

import java.util.Arrays;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for(int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int [] arr2 = {1, 5, 10, 23, 1000, 12345};
        //System.out.println(Arrays.toString(arr));

        System.out.println(insertValueSearch(arr2, 0, arr2.length - 1, 23));
    }

    //编写差值查找算法

    /**
     *
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findValue 查找值
     * @return 如果找到，返回对应下标，如果没有找到，返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findValue) {
        System.out.println("查找次数");
        if(left > right || findValue < arr[0] || findValue > arr[arr.length - 1]) {
            return -1;
        }

        //求出mid
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if(findValue > midVal) {//说明想右边递归
            return insertValueSearch(arr, mid + 1, right, findValue);
        } else if (findValue < midVal) {//说明向左边递归
            return insertValueSearch(arr, left, mid - 1, findValue);
        } else {
            return mid;
        }
    }

}

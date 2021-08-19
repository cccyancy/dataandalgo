package Search;

import java.util.Arrays;

public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
    }

    public static int maxSize = 20;
    //需要先获取到一个fibonacci数列
    //非递归得到fibo
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for(int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //编写fibo查找
    //非递归

    /**
     *
     * @param a 数组
     * @param key 需要找到的值
     * @return 位置或者-1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0; //黄金分割点
        int mid= 0;
        int f[] = fib();

        while(high > f[k] - 1) {
            k++;
        }
        //因为f[k] 值可能大雨a 的长度，因此我们需要使用Arrays,构造一个新的数组，并且指向a[]
        //不足的部分使用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        //使用a数组最后的数填充temp
        //举例
        //temp = {1, 8, 9} => {1, 8, 9, 9, 9}
        for(int i  = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        //使用while循环
        while(low <= high) {//只要这个条件满足就可以找
            mid = low + f[k - 1] - 1;
            if(key < temp[mid]) {//继续向数组左边查找
                high = mid - 1;
                //k-- 是因为

                k--;

            } else if(key > temp[mid]) {
                low = mid + 1;
                //为什么是k-=2
                //
            } else {
                //需要确定返回的是哪个位置
                if(mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}

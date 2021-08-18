package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
  public static void main(String[] args) {
    //int[] arr = {-9, 78, 0 ,23, -567, 70, 70, 0, 23, -9, -9, -9};

    int[] arr = new int[80000];
    for (int i = 0; i < 80000; i++) {
      arr[i] = (int)(Math.random() * 8000000);
    }


    //int [] arr = {101, 34, 119, 1};
    System.out.println("排序前");

    Date date1 = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date1Str = simpleDateFormat.format(date1);
    System.out.println("排序前的时间是=" + date1Str);

    quickSort(arr, 0 , arr.length - 1);

    Date date2 = new Date();
    String date2Str = simpleDateFormat.format(date2);
    System.out.println("排序后的时间是=" + date2Str);
    //System.out.println(Arrays.toString(arr));

  }

  public static void quickSort(int[] arr, int left, int right) {
    int l = left; //左索引
    int r = right; //右下标


    int pivot = arr[(left + right) / 2];
    int temp = 0; //临时变量，交换时是使用
    //while 循环的目的是让比 pivot值小的放到左边
    //比pivot大的值放到右边
    while(l < r) {
      //在pivot的左边一直找， 找到大于等于pivot的值才退出
      while(arr[l] < pivot) {
        l += 1;
      }
      //在pivot的右边一直找，找到小于等于pivot值才推出
      while(arr[r] > pivot) {
        r -= 1;
      }

      //如果l>=r说明pivot左右两边的值已经按照左边全部是
      //小于等于pivot, 右边全部是大于等于pivot值
      if(l >= r) {
        break;
      }

      //交换
      temp = arr[l];
      arr[l] = arr[r];
      arr[r] = temp;

      //如果交换完后，发现arr[l] == pivot, r--,前移一步
      if(arr[l] == pivot) {
        r -= 1;
      }
      //如果交换完后，发现arr[r] == pivot, l--,后移一步

      if(arr[r] == pivot) {
        l += 1;
      }


    }
    //如果l == r, 必须l++, r--.否则为栈溢出
    if(l == r) {
      l += 1;
      r -= 1;
    }
    //向左递归
    if(left < r) {
      quickSort(arr, left, r);
    }
    //向右递归
    if(right > l) {
      quickSort(arr, l, right);
    }

  }
}

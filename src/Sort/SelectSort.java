package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {
  public static void main(String[] args) {
    //创建要给80000个的随机数组
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

    selectSort(arr);

    Date date2 = new Date();
    String date2Str = simpleDateFormat.format(date2);
    System.out.println("排序后的时间是=" + date2Str);
  }



  //选择排序
  public static void selectSort(int[] arr) {
    //使用逐步推到的方式，来讲解选择排序
    //第一轮：
    //原始数据： 101， 34， 119， 1
    //第一轮排序：1， 34， 119， 101
    //算法就是先从简单的开始，先从小块开始

    //在推导过程中，发现规律，因此可以使用一个循环来解决
    //时间复杂度O(n^2)
    for(int i = 0; i < arr.length - 1; i++) {
      int minIndex = i;
      int min = arr[i];
      for(int j = i + 1; j < arr.length; j++) {
        if (min > arr[j]) {//假定的最小值并不是正确的最小值
          min = arr[j]; //重置min
          minIndex = j; //充值minIndex
        }
      }

      //将最小值放在arr[0]
      if(minIndex != i) {
        arr[minIndex] = arr[i];
        arr[i] = min;
      }

//      System.out.println("第" + (i+1) + "轮后：");
//      System.out.println(Arrays.toString(arr));
    }
    /*
    //第一轮
    int minIndex = 0;
    int min = arr[0];
    for(int j = 0 + 1; j < arr.length; j++) {
      if (min > arr[j]) {//假定的最小值并不是正确的最小值
        min = arr[j]; //重置min
        minIndex = j; //充值minIndex
      }
    }
    //将最小值放在arr[0]
    if(minIndex != 0) {
      arr[minIndex] = arr[0];
      arr[0] = min;
    }

    System.out.println("第一轮后：");
    System.out.println(Arrays.toString(arr));

    //第二轮
    if(minIndex != 1) {
      minIndex = 1;
      min = arr[1];
    }
    for(int j = 1 + 1; j < arr.length; j++) {
      if (min > arr[j]) {//假定的最小值并不是正确的最小值
        min = arr[j]; //重置min
        minIndex = j; //充值minIndex
      }
    }
    //将最小值放在arr[0]
    arr[minIndex] = arr[1];
    arr[1] = min;

    System.out.println("第二轮后：");
    System.out.println(Arrays.toString(arr));

    //第三轮
    if(minIndex != 2) {
      minIndex = 2;
      min = arr[2];
    }
    for(int j = 2 + 1; j < arr.length; j++) {
      if (min > arr[j]) {//假定的最小值并不是正确的最小值
        min = arr[j]; //重置min
        minIndex = j; //充值minIndex
      }
    }
    //将最小值放在arr[0]
    arr[minIndex] = arr[2];
    arr[2] = min;

    System.out.println("第三轮后：");
    System.out.println(Arrays.toString(arr));

     */


  }
}

package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {
  public static void main(String[] args) {
    //int[] arr = {53, 3, 542, 748, 14, 214};

    //int [] arr = {8, 4, 5, 7, 1, 3, 6, 2};

    int[] arr = new int[8];
    for (int i = 0; i < 8; i++) {
      arr[i] = (int)(Math.random() * 8000000);
    }
    int temp[] = new int[arr.length]; //归并排序需要额外空间
    //System.out.println("归并排序后=" + Arrays.toString(arr));


    //int [] arr = {101, 34, 119, 1};
    System.out.println("排序前");

    Date date1 = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date1Str = simpleDateFormat.format(date1);
    System.out.println("排序前的时间是=" + date1Str);

    radixSort(arr);

    Date date2 = new Date();
    String date2Str = simpleDateFormat.format(date2);
    System.out.println("排序后的时间是=" + date2Str);
    //System.out.println(Arrays.toString(arr));
  }

  //基数排序方法
  public static void radixSort(int [] arr) {
    //根据前面的推导过程，沃恩可以得到最终的基数排序代码

    //1. 得到数组中最大的数的位数
    int max = arr[0]; // 假设第一个数就是最大数
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] > max) {
        max = arr[i];
      }
    }
    //得到最大数是几位数
    int maxLength = (max + "").length(); //最大数加上空字符串变成string，再求length


    int[][] bucket = new int[10][arr.length];

    //为了记录每个桶中实际存放了多少个数据，我们定一个一维数组来记录各个桶的每次放入数据的个数
    //比如：bucketElement[0], 记录的就是bucket[0]桶的数据个数
    int[] bucketElementCounts = new int[10];
    //使用循环
    for (int i = 0; i < maxLength; i++) {
      //针对每个元素的对应位进行排序处理
      for (int j = 0; j < arr.length; j++) {
        //取出每个元素的个位
        int digitOfElement = (int) (arr[j] / Math.pow(10, i) % 10);
        //放入到对应的桶中
        bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
        bucketElementCounts[digitOfElement]++;
      }
      //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来的数组）
      int index = 0;
      //遍历每一个桶，并将桶中的数据放入到原数组
      for (int k = 0; k < bucketElementCounts.length; k++) {
        //如果桶中有数据，我们才放入到原数组
        if (bucketElementCounts[k] != 0) {
          //循环该桶即第k个桶，放入
          for (int l = 0; l < bucketElementCounts[k]; l++) {
            //取出元素放入到arr
            arr[index] = bucket[k][l];
            index++;

          }
        }
        //每一轮处理后，需要将每个bucketElementCounts[k] = 0
        bucketElementCounts[k] = 0;
      }
      System.out.println("基数排序后" + Arrays.toString(arr));
    }
  }
    /*

    //第一轮（针对每个元素的个位进行排序处理）
    //定义一个二维数组，表示10个桶， 每个桶就是一个一维数组
    //说明：
    //1. 二维数组包含10个一维数组
    //2. 为了防止在放入数的时候，数据溢出，则每个一维数组（桶），大小定位arr.length
    //3. 很明确， 基数排序是使用空间换时间的经典算法


    int[][] bucket = new int[10][arr.length];

    //为了记录每个桶中实际存放了多少个数据，我们定一个一维数组来记录各个桶的每次放入数据的个数
    //比如：bucketElement[0], 记录的就是bucket[0]桶的数据个数
    int[] bucketElementCounts = new int[10];

    //第一轮（针对每个元素的个位进行排序处理）
    for(int j = 0; j < arr.length; j++) {
      //取出每个元素的个位
      int digitOfElement = arr[j] * 1 % 10;
      //放入到对应的桶中
      bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
      bucketElementCounts[digitOfElement] ++;
    }
    //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来的数组）
    int index = 0;
    //遍历每一个桶，并将桶中的数据放入到原数组
    for(int k = 0; k < bucketElementCounts.length; k++) {
      //如果桶中有数据，我们才放入到原数组
      if(bucketElementCounts[k] != 0) {
        //循环该桶即第k个桶，放入
        for(int l = 0; l < bucketElementCounts[k]; l++) {
          //取出元素放入到arr
          arr[index] = bucket[k][l];
          index++;

        }
      }
      //第一轮处理后，需要将每个bucketElementCounts[k] = 0
      bucketElementCounts[k] = 0;
    }
    System.out.println("第一轮，对个位的排序处理 =" + Arrays.toString(arr));

    //=====================================================================
    // 第三轮（针对每个元素的百位进行排序处理）
    for(int j = 0; j < arr.length; j++) {
        //取出每个元素的十位
        int digitOfElement = arr[j] /10 % 10;
        //放入到对应的桶中
        bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
        bucketElementCounts[digitOfElement] ++;
      }
      //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来的数组）
      //遍历每一个桶，并将桶中的数据放入到原数组

    index = 0;

    for(int k = 0; k < bucketElementCounts.length; k++) {
        //如果桶中有数据，我们才放入到原数组
        if(bucketElementCounts[k] != 0) {
          //循环该桶即第k个桶，放入
          for(int l = 0; l < bucketElementCounts[k]; l++) {
            //取出元素放入到arr
            arr[index] = bucket[k][l];
            index++;
          }
        }
      bucketElementCounts[k] = 0;
    }
      System.out.println("第二轮，对十位的排序处理 =" + Arrays.toString(arr));

    //第二轮（针对每个元素的个位进行排序处理）
    for(int j = 0; j < arr.length; j++) {
      //取出每个元素的十位
      int digitOfElement = arr[j] /100 % 10;
      //放入到对应的桶中
      bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
      bucketElementCounts[digitOfElement] ++;
    }
    //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来的数组）
    //遍历每一个桶，并将桶中的数据放入到原数组

    index = 0;

    for(int k = 0; k < bucketElementCounts.length; k++) {
      //如果桶中有数据，我们才放入到原数组
      if(bucketElementCounts[k] != 0) {
        //循环该桶即第k个桶，放入
        for(int l = 0; l < bucketElementCounts[k]; l++) {
          //取出元素放入到arr
          arr[index] = bucket[k][l];
          index++;
        }
      }
      bucketElementCounts[k] = 0;
    }
    System.out.println("第三轮，对百位的排序处理 =" + Arrays.toString(arr));
  }

     */
}

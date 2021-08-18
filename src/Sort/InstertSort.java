package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InstertSort {
  public static void main(String[] args) {
    //int[] arr = {101, 34, 119, 1, -1, 89};
    //insertSort(arr);

    //创建要给80000个的随机数组
    int[] arr = new int[80000];
    for (int i = 0; i < 80000; i++) {
      arr[i] = (int)(Math.random() * 8000000);
    }

    System.out.println("排序前");

    Date date1 = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date1Str = simpleDateFormat.format(date1);
    System.out.println("排序前的时间是=" + date1Str);

    insertSort(arr);

    Date date2 = new Date();
    String date2Str = simpleDateFormat.format(date2);
    System.out.println("排序后的时间是=" + date2Str);
    //System.out.println(Arrays.toString(arr));
  }

  //插入排序
  public static void insertSort(int[] arr) {
    //使用for循环简化代码
    for (int i = 1; i < arr.length; i++) { // 不用 - 1
      int insertVal = arr[i];
      int insertIndex = i - 1;

      while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
        arr[insertIndex + 1] = arr[insertIndex];
        insertIndex--;
      }

      //判断是否需要赋值
      if (insertIndex + 1 != i) {
        arr[insertIndex + 1] = insertVal;
      }

//      System.out.println("第" + i + "轮后：");
//      System.out.println(Arrays.toString(arr));
    }

    /*





    //使用逐步推导的方法来讲解，便于理解
    //第一轮 {101, 34, 119, 1} => {34, 101, 119, 1}

    //定义待插入的数
    int insertVal = arr[1];
    int insertIndex = 1 - 1; //即arr[1] 的前面这个数下标

    //给insertVal找到插入的位置
    //1. 确保insertIndex 不越界。不能为-1
    //2. 说明待插入数还没有找到位置
    //3. 就需要将arr[insertIndex]后移
    while(insertIndex >=0 && insertVal < arr[insertIndex]) {
      arr[insertIndex + 1] = arr[insertIndex];
      insertIndex--;
    }
    //当退出while循环时，说明插入位置找到，insertIndex + 1;
    arr[insertIndex + 1] = insertVal;

    System.out.println("第一轮后：");
    System.out.println(Arrays.toString(arr));

    //第二轮

    insertVal = arr[2];
    insertIndex = 2 - 1;

    while(insertIndex >= 0 && insertVal < arr[insertIndex]) {
      arr[insertIndex + 1] = arr[insertIndex];
      insertIndex--;
    }
    arr[insertIndex + 1] = insertVal;
    System.out.println("第二轮后：");
    System.out.println(Arrays.toString(arr));

    //第三轮

    insertVal = arr[3];
    insertIndex = 3 - 1;

    while(insertIndex >= 0 && insertVal < arr[insertIndex]) {
      arr[insertIndex + 1] = arr[insertIndex];
      insertIndex--;
    }
    arr[insertIndex + 1] = insertVal;
    System.out.println("第三轮后：");
    System.out.println(Arrays.toString(arr));

     */
  }
}

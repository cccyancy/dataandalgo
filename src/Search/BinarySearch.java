package Search;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//注意： 使用二分查找的前提是该数组是有序的
//update test
public class BinarySearch {
  public static void main(String[] args) {
    int arr[] = {1, 8, 10, 89, 1000, 1000, 1000, 1234};

//    int resIndex = binarySearch(arr, 0, arr.length - 1, 19);
//    System.out.println(resIndex);

    ArrayList<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1, 1000);
    System.out.println("resIndexList=" + resIndexList);
  }

  //二分查找算法

  /**
   * @param arr     数组
   * @param left    左边的索引
   * @param right   右边的索引
   * @param findVal 要查找的值
   * @return 如果找到就返回位置，没有就返回-1
   */
  public static int binarySearch(int[] arr, int left, int right, int findVal) {

    //左边大于右边代表递归结束
    if (left > right) {
      return -1;
    }

    int midIndex = (left + right) / 2;
    int midValue = arr[midIndex];

    if (findVal > midValue) {
      return binarySearch(arr, midIndex + 1, right, findVal);
    } else if (findVal < midValue) {
      return binarySearch(arr, left, midIndex - 1, findVal);
    } else {
      return midValue;
    }
  }

  //完成一个课后思考题
  /*
  课后思考题：
  在有多个相同数值时，如何将所有的数值都查找到

  思路分析：
    在找到mid索引时，不要马上返回
    向mid 索引值的左边扫描，将所有满足1000的元素位置，加入到合集ArrayList
    向mid 索引值的右边扫描，将所有满足1000，的元素位置，加入到集合
    将ArrayList返回
   */
  public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

    //左边大于右边代表递归结束
    if (left > right) {
      return new ArrayList<Integer>();
    }

    int midIndex = (left + right) / 2;
    int midValue = arr[midIndex];

    if (findVal > midValue) {
      return binarySearch2(arr, midIndex + 1, right, findVal);
    } else if (findVal < midValue) {
      return binarySearch2(arr, left, midIndex - 1, findVal);
    } else {
      ArrayList<Integer> resIndexList = new ArrayList<Integer>();
      int temp = midIndex - 1;
      while (true) {
        if (temp < 0 || arr[temp] != findVal) {
          break;
        }
        resIndexList.add(temp);
        temp -= 1;
      }
      resIndexList.add(midIndex);

      temp = midIndex + 1;
      while (true) {
        if (temp > arr.length - 1 || arr[temp] != findVal) {
          break;
        }
        resIndexList.add(temp);
        temp += 1;
      }
      return resIndexList;
    }
  }
}

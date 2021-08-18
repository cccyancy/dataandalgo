package Recursion;

import java.util.ArrayList;

public class Maze {
  public static void main(String[] args) {
    //创建二维数组，模拟迷宫
    int[][] map = new int[8][7];
    //使用1表示墙
    //上下全部置为1

    for(int i = 0; i < 7; i++){
      map[0][i] = 1;
      map[7][i] = 1;
    }
    //左右全部置为1
    for (int i = 0; i < 8; i++) {
      map[i][0] = 1;
      map[i][6] = 1;
    }

    //设置挡板，1表示
    map[3][1] = 1;
    map[3][2] = 1;

    //如何打印2d array
    System.out.println("2D array");
    for (int i = 0; i < 8; i++) {
      for(int j = 0; j < 7; j++) {
        System.out.print(map[i][j] + " ");
      }
      System.out.println();
    }

    /*
    //使用递归回溯，给小球招录
    setWay(map, 1, 5);
    //输出新地图，表示过的地图
    System.out.println("2D array After");
    for (int i = 0; i < 8; i++) {
      for(int j = 0; j < 7; j++) {
        System.out.print(map[i][j] + " ");
      }
      System.out.println();
    }

     */

    //使用递归回溯，给小球招录
    setWay2(map, 1, 1);
    //输出新地图，表示过的地图
    System.out.println("2D array After");
    for (int i = 0; i < 8; i++) {
      for(int j = 0; j < 7; j++) {
        System.out.print(map[i][j] + " ");
      }
      System.out.println();
    }
  }
  //使用递归回溯来给小球找路
  //1. map - 2d Array
  //2. i,j starting and ending position
  //3. if [i][j] = [6][5] then find the exit
  //4. agreement: if [i][j] == 0 means can be walked, 1 means wall, 2 means walked
  // 3 mean walked but is the wrong way
  //5. Strategy: bottom - right - up - left, if the path is not working ,recurse again

  /**
   *
   * @param map 2D ARRAY
   * @param i Starting position
   * @param j starting position
   * @return if found a way, true, else is false
   */
  public static boolean setWay(int[][] map, int i, int j) {
    if(map[6][5] == 2) {//通路已经找到
      return true;
    } else {
      if (map[i][j] == 0) {//当前点没有走过
        //按照策略走
        map[i][j] = 2; // 假定可以走通
        if (setWay(map, i+1, j)) {//向下走
          return true;
        } else if (setWay(map, i, j+1)){//向右走
          return true;
        } else if (setWay(map, i-1, j)) {//向上
          return true;
        } else if (setWay(map, i, j-1)) {//向左走
          return true;
        } else {
          //说ing该点走不通
          map[i][j] = 3;
          return false;
        }
      } else {//如果map[i][j] !=0，可能是1， 2， 3
        return false;
      }
    }
  }

  //修改找路策略，改成上-右-下-左
  public static boolean setWay2(int[][] map, int i, int j) {
    if(map[6][5] == 2) {//通路已经找到
      return true;
    } else {
      if (map[i][j] == 0) {//当前点没有走过
        //按照策略走
        map[i][j] = 2; // 假定可以走通
        if (setWay2(map, i-1, j)) {//向下走
          return true;
        } else if (setWay2(map, i, j+1)){//向右走
          return true;
        } else if (setWay2(map, i+1, j)) {//向上
          return true;
        } else if (setWay2(map, i, j-1)) {//向左走
          return true;
        } else {
          //说ing该点走不通
          map[i][j] = 3;
          return false;
        }
      } else {//如果map[i][j] !=0，可能是1， 2， 3
        return false;
      }
    }
  }
}

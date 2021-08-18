package Recursion;

public class Queen8 {
  //定义一个max表示共有多少个皇后
  int max = 8;
  //定义数组array.保存皇后放置位置的结果
  int array[] = new int[max];
  static int count = 0;
  static int judgeCount = 0;
  public static void main(String[] args) {
    //测试一把
    Queen8 queen8 = new Queen8();
    queen8.check(0);
    System.out.printf("一共有%d种解法", count);
    System.out.printf("一共判断了%d次", judgeCount);


  }

  //编写一个方法，放置第n个皇后
  //注意： check是每一次递归时，进入到check都有 for(int i = 0; i < max; i++), 因此会有回溯
  private void check(int n) {
    if(n == max) { //n=8就代表八个皇后都已经放好了
      print();
      return;
    }
    //依次放入皇后并判断是或否冲突
    for(int i = 0; i < max; i++){
      //先把当前皇后 n, 放到该行的第1列
      array[n] = i;
      //判断当放置第n个皇后到i列时，是否冲突
      if(judge(n)) { //不冲突
        //接着放n+1个皇后，即开始递归
        check(n+1);
      }
      //如果冲突，就继续执行array[n] = i
      //因为有i++,所以就是尝试下一个列
    }
  }

  //查看当我们放置第n个皇后时，就去检测该皇后是否和前面已经放置的皇后冲突
  private boolean judge(int n) {
    judgeCount++;
    for(int i = 0; i < n; i++) {
      //说明：
      //1. array[i] == array[n] 表示判断 第n个皇后是否和前面的i个皇后在同一列
      //2. Math.abs(n-i) == Math.abs(array[n] - array[i] 第n个皇后是否和前面i个皇后在同一斜线
      // 行列各加一就是斜线
      if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])) {
        return false;
      }
    }
    return true;
  }

  //写一个方法，将皇后摆放的位置输出
  private void print() {
    count++;
    for(int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println();
  }
}

package Queue;

import java.util.Scanner;


public class ArrayQueneDemo {
  public static void main(String[] args) {
    //测试一把
    //创建队列
    ArrayQueue queue = new ArrayQueue(3);
    char key = ' ';//接收用户收入

    Scanner scanner = new Scanner(System.in);
    boolean loop = true;
    //输出一个菜单
    while(loop) {
      System.out.println("s: 显示队列");
      System.out.println("e: 退出队列");
      System.out.println("a: 添加数据");
      System.out.println("g: 取出数据");
      System.out.println("h: 查看队列头部数据");
      key = scanner.next().charAt(0);
      switch (key) {
        case 's':
          queue.showQueue();
          break;
        case 'a':
          System.out.println("Type a number");
          int value = scanner.nextInt();
          queue.addQueue(value);
          break;
        case 'g': //取出数据
          try {
            int res = queue.getQueue();
            System.out.printf("The number is %d\n", res);
          } catch (Exception e){
            System.out.println(e.getMessage());
          }
          break;
        case 'h': //查看队列头的数据
          try {
            int res = queue.headQueue();
            System.out.printf("Head number is %d\n", res);

          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
          break;
        case 'e':
            scanner.close();
            loop = false;
            break;
      }
    }
    System.out.println("Program Quit");
  }
}

//使用数组模拟队列 - 编写一个ArrayQueue类

class ArrayQueue {
  private int maxSize; //队列最大容量
  private int front; //队列头
  private int rear; //队列尾
  private int[] arr; //该数据用于存放数据，模拟队列。

  //创建队列构造器
  public ArrayQueue(int arrMaxSize) {
    maxSize = arrMaxSize;
    arr = new int[maxSize];
    front = -1;//指向队列头部， 分析出front是指向队列头的前一个位置
    rear = -1;//指向队列尾部， 直接指向队列的尾部的数据（就是队列最后一个数据）
  }

  //判断队列是否满
  public boolean isFull() {
    return rear == maxSize - 1;
  }

  //判断队列是否为空
  public boolean isEmpty() {
    return rear == front;
  }

  //添加数据到队列
  public void addQueue(int n) {
    //判断队列是否满
    if(isFull()) {
      System.out.println("Queue is full");
      return;
    }

    rear++; //rear 后移
    arr[rear] = n;

  }

  //获取队列数据，出队列
  public int getQueue() {
    //判断队列是否空
    if(isEmpty()) {
      //通过抛出异常来处理
      throw new RuntimeException("Queue is empty");
    }

    int value = arr[front];
    front = (front + 1) % maxSize;
    return value;
  }
  //显示队列所有数据
  public void showQueue() {
    //遍历
    if(isEmpty()){
      System.out.println("Queue is empty");
      return;
    }
    for (int i = 0; i < arr.length; i++) {
      System.out.printf("arr[%d] = %d\n", i, arr[i]);
    }
  }
  //显示队列的头部数据，不是取出数据
  public int headQueue() {
    //判断
    if(isEmpty()) {
      throw new RuntimeException("Empty Queue");
    }
    return arr[front+1];
  }
}



package Queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
  public static void main(String[] args) {
    //创建环形队列
    CircleArray queue = new CircleArray(6); //说明设置4， 最大有效数据为3
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


class CircleArray {
  private int maxSize; //队列最大容量
  private int front; //队列头 0
  private int rear; //队列尾 0
  private int[] arr; //该数据用于存放数据，模拟队列。

  //创建队列构造器

  public CircleArray(int arrMaxSize) {
    maxSize = arrMaxSize;
    arr = new int[maxSize];

  }

  public boolean isFull() {
    return (rear + 1) % maxSize == front;
  }

  public boolean isEmpty() {
    return rear == front;
  }

  public void addQueue(int n) {
    if (isFull()) {
      System.out.println("队列满，不能加入数据");
      return;
    }
    //直接将数据加入
    arr[rear] = n;
    //将rear后移，必须考虑取模
    rear = (rear + 1) % maxSize;
  }

  public int getQueue() {
    if (isEmpty()) {
      throw new RuntimeException("Queue is empty");
    }
    //这里需要分析出front是指向队列的第一个元素
    // 1. 先把front对应的值保存到一个临时变量
    // 2. 将front后移, 考虑取模
    // 3. 将临时保存的变量返回
    int value = arr[front];
    front = (front + 1) % maxSize;
    return value;
  }

  public void showQueue() {
    if (isEmpty()) {
      System.out.println("Queue is empty");

    }

    //思路： 从front开始遍历，遍历多少个元素
    for (int i = front; i < front + size(); i++) {
      System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
    }
  }
  //求出当前数据有效数据的个数
  public int size() {
    //rear = 1;
    //front = 0;
    //maxSize = 3
    return (rear + maxSize - front) % maxSize;
  }

  public int headQueue() {
    if (isEmpty()) {
      throw new RuntimeException("Queue is empty");
    }
    return arr[front];
  }
}

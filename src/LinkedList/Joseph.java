package LinkedList;

public class Joseph {
  public static void main(String[] args) {
    //测试构建环形链表和遍历是否成功
    CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
    circleSingleLinkedList.addBoy(5); //加入五个小孩
    circleSingleLinkedList.showBoy();

    //测试小孩出圈是否正确
    circleSingleLinkedList.countBoy(1, 2, 5);//2-4-1-5-3
  }
}

//创建一个环形单向链表
class CircleSingleLinkedList {
  //创建一个first节点，当前没有编号
  private Boy first = null;
  //添加小孩节点，构建成一个环形链表
  public void addBoy(int nums) {
    //nums 做一个数据校验
    if (nums < 1) {
      System.out.println("num的值不正确");
      return;
    }
    Boy curBoy = null; //辅助指针,指向当前boy，帮助构建环形链表
    //使用for循环创建环形链表
    for (int i = 1; i <= nums; i++) {
      //根据编号创建小孩节点、
      Boy boy = new Boy(i);
      //如果是第一个小孩
      if (i == 1) {
        first = boy;
        first.setNext(first); //first指向自己，从而构成环
        curBoy = first; //让curBoy指向第一个小孩
      } else {//第二个小孩及以上
        curBoy.setNext(boy); //当前boy和新来的boy连起来
        boy.setNext(first);//新加入的boy和第一个连起来
        curBoy = boy;//curBoy后移，成为最新的boy
      }
    }
  }
  //遍历当前的环形链表
  public void showBoy() {
    //判断链表是否为空
    if(first == null) {
      System.out.println("链表为空");
      return;
    }
    //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
    Boy curBoy = first;
    while(true) {
      System.out.printf("小孩的编号 %d", curBoy.getNo());
      System.out.println();
      if(curBoy.getNext() == first) { //说明已经遍历完毕
        break;
      }
      curBoy = curBoy.getNext(); //curBoy 后移
    }
  }

  //根据用户输入，计算出出圈的顺序

  /**
   *
   * @param startNo 表示从第几个小孩开始数数
   * @param countNum 表示数几下
   * @param nums 表示最初有多少小孩在圈中
   */
  public void countBoy(int startNo, int countNum, int nums) {
    if (first == null || startNo < 1 || startNo > nums) {
      System.out.println("参数输入有误，请重新输入");
      return;
    }

    //创建辅助指针，帮助小孩完成出圈
    Boy helper = first;
    //创建helper，指向环形最后的点
    while (true) {
      if (helper.getNext() == first) {//说明helper指向了最后一个节点
        break;
      }
      helper = helper.getNext();
    }
    //小孩报数前，先让first和helper移动 k-1 次
    for(int j = 0; j < startNo - 1; j++) {
      first = first.getNext();
      helper = helper.getNext();
    }
    //当报数时，让first和helper指针同时移动m-1次，然后出圈
    //循环操作，知道圈中只有一个节点
    while(true) {
      if (helper == first) {//说明圈中中有一个节点
        break;
      }
      //让first和helper同时移动count - 1次
      for(int j = 0; j < countNum - 1; j++) {
        first = first.getNext();
        helper = helper.getNext();
      }
      //这时指向的就是出圈小孩的节点
      System.out.printf("小孩 %d 出圈", first.getNo());
      System.out.println();
      //这时将first指向的小孩出圈
      first = first.getNext();
      helper.setNext(first);
    }
    System.out.printf("最后留在圈中小孩的编号 %d", first.getNo());

  }
}

//先创建一个boy，表示一个节点
class Boy {
  private int no; //编号
  private Boy next; //指向下一个节点，默认为null

  public Boy(int no) {
    this.no = no;
  }

  public Boy getNext() {
    return next;
  }

  public void setNext(Boy next) {
    this.next = next;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

}

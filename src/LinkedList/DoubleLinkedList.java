package LinkedList;

public class DoubleLinkedList {
  private HeroNode2 head = new HeroNode2(0, "", "");

  public HeroNode2 getHead() {
    return head;
  }


  //显示链表【遍历】
  public void list() {
    //判断链表是否为空
    if (head.next == null) {
      System.out.println("Linked list is empty");
      return;
    }
    //因为头节点不能动，因此需要辅助变量遍历
    HeroNode2 temp = head.next;

    while (true) {
      //判断链表是否到最或
      if(temp == null) {
        break;
      }
      //输出节点信息
      System.out.println(temp);
      //将temp后移， 一定要后移
      temp = temp.next;
    }
  }

  //按顺序添加
  public void addByOrder(HeroNode2 heroNode) {
    HeroNode2 temp = head;
    boolean flag = false;

    while (true) {
      if (temp.next == null) {
        break;
      }
      if (temp.next.no > heroNode.no) {//位置找到
        break;
      } else if (temp.next.no == heroNode.no) {
        flag = true;
        break;
      }
      temp = temp.next;

    }

    if (flag) {
      System.out.printf("%d already exist\n", heroNode.no);
    } else {
      heroNode.next = temp.next;
      temp.next.pre = heroNode;

      temp.next = heroNode;
      heroNode.pre = temp;


    }
  }

  //添加一个节点到双向链表的最后
  public void add(HeroNode2 heroNode) {
    //因为head节点不能动，需要一个temp
    HeroNode2 temp = head;
    //遍历链表，找到最后
    while (true) {
      //找到链表最后
      if (temp.next == null) {
        break;
      }
      //如果没有找到最后，将temp后移
      temp = temp.next;
    }
    //当退出while循环时，temp就指向了链表最后
    //形成一个双向链表
    temp.next = heroNode;
    heroNode.pre = temp;

  }
  //修改一个节点的内容

  //修改节点的信息，根据编号来修改，即no编号不能改. 和单向基本一样
  // 1. 根据newHeroNode 的 no来修改即可
  public void update(HeroNode2 newHeroNode) {
    //判断是否为空
    if(head.next == null) {
      System.out.println("链表为空");
      return;
    }
    //找到需要修改的节点，根据no编号
    //先定义辅助变量
    HeroNode2 temp = head.next;
    boolean flag = false;
    while(true) {
      if (temp == null) {
        break; //已经遍历完链表
      }
      if(temp.no == newHeroNode.no) {
        flag = true;
        break;
      }
      temp = temp.next;
    }
    //根据flag判断是否找到要修改的节点
    if(flag) {
      temp.name = newHeroNode.name;
      temp.nickName = newHeroNode.nickName;
    } else {//没有找到
      System.out.printf("没有找到编号 %d 的节点， 不能修改\n", newHeroNode.no);

    }

  }

  //从双向链表中删除一个节点
  // 1.对于双向链表，可以直接找到要删除的节点，
  // 2.找到以后，自我删除即可
  public void del(int no) {
    //判断当前链表是否为空
    if (head.next == null) {//空链表
      System.out.println("链表为空，无法删除");
      return;
    }

    HeroNode2 temp = head.next; //辅助变量（指针）
    boolean flag = false; //标志是否找到待删除结点

    while(true) {
      if(temp == null) {//已经到链表最后

        break;
      }
      if(temp.no == no) {
        //找到待删除节点的前一个结点
        flag = true;
        break;
      }
      temp = temp.next; //temp后移，遍历
    }
    //判断flag
    if(flag) {
      //可以删除
      temp.pre.next = temp.next;
      //temp.next.pre = temp.pre; 有风险
      //如果是最后一个节点就不需要第二句话， 否则会出现空指针异常
      if(temp.next != null) {
        temp.next.pre = temp.pre;
      }

    } else {
      System.out.printf("要删除的节点%d不存在", no);
      System.out.println();
    }
  }

}



class HeroNode2 {
  public int no;
  public String name;
  public String nickName;
  public HeroNode2 next;//指向下一个节点 默认为null
  public HeroNode2 pre;//指向前一个结点 默认为null

  public HeroNode2(int hNo, String Name, String Nickname) {
    this.no = hNo;
    this.name = Name;
    this.nickName = Nickname;
  }
  //rewrite toString
  @Override
  public String toString() {
    return "HeroNode [no = " + no + ", name=" + name + ", nickname =" + nickName+ "]";
  }
}



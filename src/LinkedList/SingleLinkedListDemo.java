package LinkedList;

import java.util.Stack;

public class SingleLinkedListDemo {
  public static void main(String[] args) {
    //进行测试
    //先创建节点
    HeroNode hero1 = new HeroNode(1, "songjiang", "jishiyu");
    HeroNode hero2 = new HeroNode(2, "lujinyi", "yuqilin");
    HeroNode hero3 = new HeroNode(3, "wuyong", "zhiduoxing");
    HeroNode hero4 = new HeroNode(4, "linchong", "baozitou");


    //创捷一个链表
    SingleLinkedList singleLinkedList = new SingleLinkedList();
    SingleLinkedList singleLinkedList1 = new SingleLinkedList();


    //测试一下单链表的反转

    singleLinkedList1.addNode(hero1);
    singleLinkedList1.addNode(hero2);
    singleLinkedList.addNode(hero3);
    singleLinkedList.addNode(hero4);

    singleLinkedList.list();




    /*
    System.out.println("反转单链表");
    reverseList(singleLinkedList.getHead());
    singleLinkedList.list();



    System.out.println("测试逆序打印单链表, 没有改变链表结构");
    reversePrint(singleLinkedList.getHead());

     */



    /*
    singleLinkedList.addByOrder(hero1);
    singleLinkedList.addByOrder(hero4);
    singleLinkedList.addByOrder(hero2);
    singleLinkedList.addByOrder(hero3);

    HeroNode heroNode = new HeroNode(2, "xiaolu", "lujunyi~");

    singleLinkedList.update(heroNode);

    singleLinkedList.del(1);
    singleLinkedList.del(4);
    singleLinkedList.del(5);



    //显示
    singleLinkedList.list();

    //测试单链表中有效节点的个数
    System.out.println(getLength(singleLinkedList.getHead()));

    //测试一下是否得到倒数第K个
    HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 3);
    System.out.println("res= " + res);

     */


  }


  //课后作业 merge by order
  public static HeroNode mergeByOrder(HeroNode node1, HeroNode node2) {
    if (node1.next == null && node2.next == null) {
      throw new RuntimeException("both list are null");
    }
    if (node1.next == null) {
      return node2;
    }
    if(node2.next == null) {
      return node1;
    }

    HeroNode newHead = new HeroNode(0, "", "");
    HeroNode current = newHead;
    HeroNode temp1 = node1.next;
    HeroNode temp2 = node2.next;

    while (temp1 != null && temp2 != null) {
      if (temp1.no < temp2.no) {
        current.next = temp1;
        temp1 = temp1.next;
        current = current.next;
      } else {
        current.next = temp2;
        temp2 = temp2.next;
        current = current.next;
      }

    }

    if (temp1 == null) {
      current.next = temp2;
    }
    if (temp2 == null) {
      current.next = temp1;
    }
    return newHead;
  }




  //使用栈来实现逆序打印
  public static void reversePrint(HeroNode head) {
    if (head.next == null) {
      return;//空链表， 不能打印
    }
    //创建一个栈，将各个节点压入栈中
    Stack<HeroNode> stack = new Stack<HeroNode>();
    HeroNode cur = head.next;
    //将链表的所有节点压入栈中
    while (cur != null) {
      stack.push(cur);
      cur = cur.next;//cur后移，压入下一个节点
    }
    //将栈中的节点进行打印，pop出战
    while (stack.size() > 0) {
      System.out.println(stack.pop()); // stack 的特点是先进后厨
    }
  }

  //将单链表进行翻转
  public static void reverseList(HeroNode head) {
    //如果当前链表为空，或者只有一个节点，无需反转，直接返回
    if (head.next == null || head.next.next == null) {
      return;
    }

    //定义辅助指针（变量），帮助我们遍历原来的链表
    HeroNode cur = head.next;
    HeroNode next = null; //指向当前节点[cur]的下一个节点
    HeroNode reverseHead = new HeroNode(0, "", "");
    //遍历原来的链表，每遍历一个节点就将其取出，放在新的链表 reverseHead 的最前端
    //动脑筋
    while(cur != null) {
      next = cur.next; //先暂时保存当前节点的下一个节点， 因为后面需要使用
      cur.next = reverseHead.next; //将cur的下一个节点指向新链表的最前端
      reverseHead.next = cur; // 将cur连接到新链表上
      cur = next; //cur后移
    }
    //将head.next 指向reverseHead.next, 实现单链表反转
    head.next = reverseHead.next;
  }

  //查找单链表中的倒数第K个节点
  //思路
  // 1. 编写方法，接受head节点，同时接受index
  // 2. index 表示的是倒数第index个节点
  // 3. 先把链表从头到尾遍历，得到链表的总长度 getLength
  // 4. 得到size 后， 从链表的第一个开始遍历（size - index)个
  // 5. 如果找到，返回该节点，或返回空
  public static HeroNode findLastIndexNode(HeroNode head, int index) {
    //判断如果链表为空，返回null
    if(head.next == null) {
      return null;//没有找到
    }
    //第一个遍历得到链表长度（节点个数）
    int size = getLength(head);
    //第二次遍历 size - index 位置，就是我们倒数第K节点
    //先做一个index校验
    if (index <=0 || index > size) {
      return null;
    }
    //定义辅助变量, for 循环定位到倒数index
    HeroNode cur = head.next;
    for (int i = 0; i < size - index; i++) {
      cur = cur.next;
    }
    return cur;

  }

  /**
   *
   * @param head 链表头节点
   * @return 有效节点的个数
   */

  //获取单链表的节点的个数（不包括头节点）
  public static int getLength(HeroNode head) {
    if (head.next==null) {
      return 0;
    }
    int length = 0;
    //定义一个辅助变量, 没统计头节点
    HeroNode cur = head.next;
    while(cur != null) {
      length++;
      cur = cur.next;
    }
    return length;


  }

}

//Define a sing linked list for the hero
class SingleLinkedList {


  //head node, 不要动, 不存放具体数据
  private HeroNode head = new HeroNode(0, "", "");

  //返回头节点
  public HeroNode getHead() {
    return head;
  }
  //添加节点到单向列表
  //思路，当不考虑编号顺序是
  //1. 找到当前链表最后节点
  //2. 将最后这个节点的next 指向 新的节点
  public void addNode(HeroNode heroNode) {
    //因为head不能动， 因此需要辅助变量 temp

    HeroNode temp = head;
    //遍历链表，找到最后
    while(true) {
      //找到链表最后
      if(temp.next == null) {
        break;
      }
      //如果没有找到，temp后移
      temp = temp.next;
    }
    //当退出while循环时，temp就指向了链表的最后
    //将最后这个节点的next 指向 新的节点
    temp.next = heroNode;
  }







  //第二种添加方式，根据英雄排名将英雄添加到指定位置
  public void addByOrder(HeroNode heroNode) {
    //因为头节点不能动，仍然需要同过辅助指针来帮助找到添加的位置
    //因为是单链表， 因此找的temp是位于添加位置的前一个节点，否则加入不了
    HeroNode temp = head;
    boolean flag = false;//标志添加的编号是否存在，默认为false

    while(true) {
      if(temp.next == null) {//说明已经在链表最后
        break;//
      }
      if(temp.next.no > heroNode.no) {//位置找到，就在temp后面插入
        break;
      } else if (temp.next.no == heroNode.no) {//说明想添加的编号已经存在
        flag = true;//编号存在
        break;
      }
      temp = temp.next; //后移，遍历当前链表
    }
    //判断flag的值
    if(flag) {//不能添加， 编号已经存在
      System.out.printf("准备插入的英雄编号%d已经存在, 不能加入\n", heroNode.no);
    } else {
      //可以加入，temp的后面
      heroNode.next = temp.next;
      temp.next = heroNode;
    }

  }

  //修改节点的信息，根据编号来修改，即no编号不能改
  // 1. 根据newHeroNode 的 no来修改即可
  public void update(HeroNode newHeroNode) {
    //判断是否为空
    if(head.next == null) {
      System.out.println("链表为空");
      return;
    }
    //找到需要修改的节点，根据no编号
    //先定义辅助变量
    HeroNode temp = head.next;
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

  //删除节点
  //思路：
  // 1. head不能动，需要temp辅助节点找到前一个节点
  // 2. 在比较时，temp.next.no和要删除的节点的no比较
  public void del(int no) {
    HeroNode temp = head;
    boolean flag = false;
    while(true) {
      if(temp.next == null) {//已经到链表最后
        break;
      }
      if(temp.next.no == no) {
        //找到待删除节点的前一个结点
        flag = true;
        break;
      }
      temp = temp.next; //temp后移，遍历
    }
    //判断flag
    if(flag) {
      //可以删除
      temp.next = temp.next.next;
    } else {
      System.out.printf("要删除的节点%d不存在", no);
      System.out.println();
    }
  }

  //显示链表【遍历】
  public void list() {
    //判断链表是否为空
    if (head.next == null) {
      System.out.println("Linked list is empty");
      return;
    }
    //因为头节点不能动，因此需要辅助变量遍历
    HeroNode temp = head.next;

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

}


//Define a Hero Node, each hero object is a node

class HeroNode {
  public int no;
  public String name;
  public String nickName;
  public HeroNode next;

  public HeroNode(int hNo, String Name, String Nickname) {
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

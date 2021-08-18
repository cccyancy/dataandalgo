package LinkedList;

public class DoubleLinkedListDemo {
  public static void main(String[] args) {
    //测试
    System.out.println("双向链表的测试");
    HeroNode2 hero1 = new HeroNode2(1, "songjiang", "jishiyu");
    HeroNode2 hero2 = new HeroNode2(2, "lujinyi", "yuqilin");
    HeroNode2 hero3 = new HeroNode2(3, "wuyong", "zhiduoxing");
    HeroNode2 hero6 = new HeroNode2(6, "linchong", "baozitou");

    //创建一个双向链表对象
   DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
   doubleLinkedList.add(hero1);
   doubleLinkedList.add(hero2);
   doubleLinkedList.add(hero3);
   doubleLinkedList.add(hero6);

   doubleLinkedList.list();

   /*
   //修改
    HeroNode2 hero5 = new HeroNode2(4, "gongsuncheng","ruyunlong");
    doubleLinkedList.update(hero5);
    System.out.println("修改后的链表情况");
    doubleLinkedList.list();

    //删除
    doubleLinkedList.del(3);
    System.out.println("删除后");
    doubleLinkedList.list();


    */
    //顺序添加
    HeroNode2 hero4 = new HeroNode2(4, "congyang","haitun");
    HeroNode2 hero5 = new HeroNode2(5, "gongsuncheng","ruyunlong");


    doubleLinkedList.addByOrder(hero4);
    doubleLinkedList.addByOrder(hero5);

    System.out.println("顺序添加后");
    doubleLinkedList.list();



  }
}



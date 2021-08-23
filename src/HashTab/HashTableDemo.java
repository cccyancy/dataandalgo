package HashTab;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单菜单
        String key = "";
        Scanner scanner= new Scanner(System.in);
        while(true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("delete: 删除雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "delete":
                    System.out.println("请输入要删除的id");
                    id = scanner.nextInt();
                    hashTab.deleteEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }

    }
}

//创建HashTab 管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray; //数组里面放的是EmpLinkedList
    private int size;

    //构造器
    public HashTab(int size) {
        this.size = size;
        //初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        //不要忘了分别初始化每一条链表
        for(int i = 0; i < size; i ++ ) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }
    //添加雇员
    public void add(Emp emp) {
        //根据员工id，得到该员工应该加入到那条链表
        int empLinkedListNO = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    //根据输入的id查找雇员
    public void findEmpById(int id) {
        //使用hash function
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if(emp != null) {//找到
            System.out.printf("在第%d条链表中找到 雇员id = %d\n", (empLinkedListNO + 1),id );

        } else {
            System.out.println("在哈希表中没有找到该雇员");
        }
    }
    public void deleteEmpById(int id) {
        int empLinkedListNO = hashFun(id);
        empLinkedListArray[empLinkedListNO].deleteEmpById(id);
    }

    //遍历所有hash map
    public void list() {
        for(int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }

    }

    //编写hash function, 取膜法
    public int hashFun(int id) {
        return id % size;
    }
}


//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next; //默认为空

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList 表示链表
class EmpLinkedList {
    //头指针，指向第一个Emp，因此这个链表的head是指向第一个Emp
    private Emp head;
    //添加雇员到链表
    //说明
    //1. 假设添加的雇员是加到最后，id自增长，分配从小到大
    //2。 直接将该雇员自动加入本链表得自后即可
    public void add(Emp emp) {
        //添加第一个雇员
        if (head == null) {
            head = emp;//头节点有数值，就是第一个emp
            return;
        }
        //如果不是第一个，创建辅助指针帮助定位到最后
        Emp curEmp = head;
        while(true) {
            if(curEmp.next == null) {//说明到链表最后了
                break;
            }
            curEmp = curEmp.next;
        }
        //退出时直接将emp加入链表
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {//说明链表为空
            System.out.println("第 " + (no+1) + " 链表为空");
            return;
        }
        System.out.println("第" + (no+1) + "的信息为");
        Emp curEmp = head;//辅助指针
        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {//说明curEmp已经是最后节点
                break;
            }
            curEmp = curEmp.next;//后移
        }
        System.out.println();
    }

    //根据id查找雇员
    //如果查找到就返回EMP，没有找到就返回null
    public Emp findEmpById(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {//找到
                break; //这是curEmp就指向要查找的雇员
            }
            //退出
            if(curEmp.next == null) {//说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next; //后移
        }
        return curEmp;
    }

    public void deleteEmpById(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        //辅助指针
        Emp curEmp = head.next;
        Emp prevEmp = head;
        Emp headEmp = head;

        if(headEmp.id == id) {
            head = headEmp.next;
            return;
        }

        while (true) {
            if (curEmp.id == id) {//找到
                break;
            }
            curEmp = curEmp.next;
        }
        prevEmp.next = curEmp.next;
    }
}
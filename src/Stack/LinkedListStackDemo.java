package Stack;

//用链表实现栈

public class LinkedListStackDemo {
  public static void main(String args[]) {

  }
}

class StackSingleLinkedList {
  NumNode header;
  int noCount;
  int size;

  //constructor, empty stack
  public StackSingleLinkedList() {
    header = null;
    noCount = 0;
    size = 0;
  }

  //stack, custom size
  public StackSingleLinkedList(int size) {
    header = null;
    noCount = 0;
    this.size = size;
  }

  public void setHeader(NumNode header) {
    this.header = header;
  }

  public boolean isFull() {
    if (noCount == size) {
      return true;
    }
    return false;
  }

  public boolean isEmpty() {
    if (noCount == 0) {
      return true;
    }
    return false;
  }

  //入栈
  public void push (int value) {
    if (this.isFull()) {
      throw new RuntimeException("Stack is full");
    }
    header = new NumNode(value, header);
    noCount++;
  }

  //出栈
  public int pop() {
    if (this.isEmpty()){
      throw new RuntimeException("Stack is empty");
    }
    int num = header.getNum();
    header = header.next;
    noCount--;
    return num;
  }

  //返回栈顶元素
  public int peak() {
    if (this.isEmpty()) {
      throw new RuntimeException("Stack is empty");
    }
    return header.getNum();
  }
}


class NumNode {
  public int no;
  public NumNode next;

  public NumNode(int no) {
    this.no = no;
  }

  public NumNode(int no, NumNode n) {
    this.no = no;
    next = n;
  }

  public int getNum() {
    return no;
  }

}


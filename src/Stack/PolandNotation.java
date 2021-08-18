package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
  public static void main(String[] args) {

    //要完成将一个中缀表达式转成后缀表达式的功能
    //说明
    //1. 1+((2+3)*4) - 5  -> 1 2 3 + 4 * + 5 -
    //2. 因为直接对str进行操作不方便，因此，先将“1+((2+3)*4) - 5” 转成中缀的表达式对应的list
    // 即1+((2+3)*4) - 5 -> ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
    //3.将得到的中缀表达式对应的List -> 后缀表达式对一个的list
    // 即 ArrayList[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] -> [1, 2, 3, +, 4, *, +, 5, -]

    String expression = "1+((2+3)*4)-5";
    List<String> infixExpressionList = toInfixExpressionList(expression);
    System.out.println("中缀表达式对应的List");
    System.out.println(infixExpressionList);

    List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
    System.out.println("后缀表达式对应的List");
    System.out.println(suffixExpressionList);

    System.out.printf("expression=%d", calculate(suffixExpressionList));





/*
    //先定义一个逆波兰表达式
    //（30+4）*5-6 -> 3 4 + 5 * 6 -

    //1. 为了方便，逆波兰表达式的数字和符号使用空格隔开
    String suffixExpression = "30 4 + 5 * 6 -";
    //思路
    //1. 先将“ 3 4 + 5 * 6 -” 放到array list 中
    //2. 将ArrayList传递给一个方法，遍历ArrayList 配合栈完成计算
    List<String> rpnList = getListString(suffixExpression);
    System.out.println("rpnList=" + rpnList);

    int res = calculate(rpnList);
    System.out.printf("计算的结果是 = %d", res);
  }

 */
  }


  //3.将得到的中缀表达式对应的List -> 后缀表达式对一个的list
  // 即 ArrayList[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] -> [1, 2, 3, +, 4, *, +, 5, -]
  public static List<String> parseSuffixExpressionList(List<String> ls) {
    //定义两个栈
    Stack<String> s1 = new Stack<String>(); //符号栈
    //因为s2在传唤过程中没有pop操作，而且我们还需要逆序输出
    //因此比较麻烦，这里我们就不用Stack<String>, 用array list
    //Stack<String> s2 = new Stack<String>(); //存储中间结果的栈s2， s2没有出栈操作
    List<String> s2 = new ArrayList<String>(); //储存中间结果的s2
    //遍历ls
    for (String item : ls) {
      //如果是一个数，加入s2
      if (item.matches("\\d+")) {
        s2.add(item);
      } else if (item.equals("(")) {
        s1.push(item);
      } else if (item.equals(")")) {
        //如果是右括号，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
        while (!s1.peek().equals("(")) {
          s2.add(s1.pop());
        }
        s1.pop(); //! 将（ 弹出s1栈，消除小括号
      } else {
        //当item的优先级小于或等于s1栈顶运算符，将s1栈顶的运算符弹出并加入s2, 并再次返回比较
        //缺少比较优先级高地的方法

        while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
          s2.add(s1.pop());
        }
        //还需要将item压入栈
        s1.push(item);
      }
    }
    //将s1中剩余代码加入到s2中
    while(s1.size() != 0) {
      s2.add(s1.pop());
    }
    return s2; //注意：因为是存放到list,因此按顺序输出对应的List就是对应的后缀表达式
  }








  //方法： 将中缀表达式转成对应的List
  public static List<String> toInfixExpressionList(String s){
    //定义一个List, 放中缀表达式， 对应的内容
    List<String> ls = new ArrayList<String>();
    int i = 0;//这个是一个指针，用于遍历中缀表达式字符串
    String str; // 做对多位数的拼接
    char c; //每遍历到一个字符，就放入到C

    do{
      //如果c是一个非数字，我就需要加入到ls里面去
      if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
        ls.add("" + c);
        i++; //i后移
      } else { //如果是一个数，需要考虑多位数
        str = ""; //先将str置成空“”
        while (i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57) {
          str += c; //拼接
          i++;
        }
        ls.add(str);
      }
    } while (i < s.length());
    return ls;
  }



  //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
  public static List<String> getListString(String suffixExpression) {
    //将suffixExpression分割
    String[] split = suffixExpression.split(" ");
    List<String> list = new ArrayList<String>();
    for (String ele: split) {
      list.add(ele);
    }
    return list;
  }
  //完成对逆波兰表达式的计算
  /*
  从左至右扫描，将3 4 压入栈堆
  遇到 + 运算符，弹出4和3， 计算3+4，得7，再将7入栈
  将5入栈
  *运算符，弹出5和7，计算出7*5 = 35，将35入栈
  将6入栈
  最后是-运算符，计算出35-6的值，即29，由此得出最终结果
   */

  public static int calculate(List<String> ls) {
    //创建一个栈，只需要一个栈即可
    Stack<String> stack = new Stack<String>();
    //遍历ls
    for (String item : ls) {
      //使用正则表达式取出数
      if (item.matches("\\d+")) {//正则表达式，匹配的是多位数
        //入栈
        stack.push(item);
      } else {
        //pop出两个数，并运算，再入栈
        int num2 = Integer.parseInt(stack.pop());
        int num1 = Integer.parseInt(stack.pop());
        int res = 0;
        if (item.equals("+")) {
          res = num1 + num2;
        } else if (item.equals("-")) {
          res = num1 - num2;
        } else if (item.equals("*")) {
          res = num1 * num2;
        } else if (item.equals("/")) {
          res = num1 / num2;
        } else {
          throw new RuntimeException("运算符有误");
        }
        //把res 入栈
        stack.push(res + "");//准换成字符串
      }

    }
    //最后留在stack中的数据就是运算结果
    return Integer.parseInt(stack.pop());
  }
}

//编写一个 class operation 可以返回一个运算符对应的优先级
class Operation {
  private static int ADD = 1;
  private static int SUB = 2;
  private static int MUL = 2;
  private static int DIV = 2;

  //写一个方法， 返回对应的优先级数字
  public static int getValue(String operation) {
    int result = 0;
    switch (operation) {
      case "+":
        result = ADD;
        break;
      case "-":
      result = SUB;
        break;
      case "*" :
        result = MUL;
        break;
      case "/":
        result = DIV;
        break;
      default:
        System.out.println("Operation does not exist");
    }
    return result;
  }
}

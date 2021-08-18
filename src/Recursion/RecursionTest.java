package Recursion;

public class RecursionTest {
  public static void main(String[] args) {
    test(4);
    System.out.println(factorial(4));

  }

  public static void test(int n) {
    if (n > 2) {
      test(n - 1);
    }
    System.out.println(n);
  }

  public static int factorial(int n) {
    if (n == 1) {
      return 1;
    } else {
      return factorial(n - 1) * n;
    }
  }
}

package src;

import java.util.Scanner;
interface LambdaExpression {
  boolean perform(int x);
}
public class Codeathon04_Haritha {
  public static void main(String args[]) {
    LambdaExpression isOdd = n -> n % 2 != 0;
    LambdaExpression isPrime = n -> {
      if (n < 2) return false;
      for (int i = 2; i <= Math.sqrt(n); i++) {
        if (n % i == 0) return false;
      }
      return true;
    };
    LambdaExpression isPalindrome = n -> {
      int reversed = 0, original = n;
      while (n != 0) {
        int digit = n % 10;
        reversed = reversed * 10 + digit;
        n /= 10;
      }
      return original == reversed;
    };
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter value :");
    int num = scanner.nextInt();
    String[] output = new String[num];
    System.out.println("Please choose options :");
    for (int i = 0; i < num; i++) {
      int ch = scanner.nextInt();
      int no = scanner.nextInt();
      switch (ch) {
        case 1:
          output[i] = isOdd.perform(no) ? "ODD" : "EVEN";
          break;
        case 2:
          output[i] = isPrime.perform(no) ? "PRIME" : "COMPOSITE";
          break;
        case 3:
          output[i] = isPalindrome.perform(no) ? "PALINDROME" : "NOT A PALINDROME";
          break;
      }
    }
    System.out.println("Output :");
    for (int i = 0; i < num; i++)
      System.out.println(output[i]);
  }
}
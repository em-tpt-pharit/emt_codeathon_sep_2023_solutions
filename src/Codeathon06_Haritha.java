package src;

import java.util.*;

public class Codeathon06_Haritha {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the number of values for list1 and list2: ");
    int numValues = sc.nextInt();
    sc.nextLine();
    List<String> list1 = new ArrayList<>();
    System.out.println("Enter list1 elements:");   // To enter list1 values
    for (int i = 0; i < numValues; i++) {
      String input = sc.nextLine();
      list1.add(input);
    }

    List<String> list2 = new ArrayList<>();
    System.out.println("Enter list2 elements:");   // To Enter List two Values
    for (int i = 0; i < numValues; i++) {
      String input = sc.nextLine();
      list2.add(input);
    }

    Map<String, List<String>> resultMap = new HashMap<>();

    for (int i = 0; i < list1.size(); i++) {
      String str = list1.get(i);
      List<String> equalF = sameFuritsSize(str, list2);
      resultMap.put(str, equalF);
    }

    for (int i = 0; i < list1.size(); i++) {
      String str = list1.get(i);
      List<String> findFruits = resultMap.get(str);
      System.out.print(str + ": ");
      if (findFruits.isEmpty()) {
        System.out.println("[no fruit]");
      } else {
        System.out.println(String.join(", ", findFruits));
      }
    }
  }

  private static List<String> sameFuritsSize(String s1, List<String> fruitList) {    // Function to find fruits of the same size
    char word = fruits(s1);
    List<String> findFruits = new ArrayList<>();

    for (int i = 0; i < fruitList.size(); i++) {      // To find matching fruits
      String s = fruitList.get(i);
      if (s.length() == word - 'A' + 1) {
        findFruits.add(s);
      }
    }
    return findFruits;
  }

  private static char fruits(String code) {
    for (char ch : code.toCharArray()) {
      if (Character.isLetter(ch)) {
        return Character.toUpperCase(ch);
      }
    }
    return ' ';
  }
}
package src;
import java.util.Scanner;
public class Codeathon05b_Haritha {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int var1 = sc.nextInt();
        int var2 = sc.nextInt();
        var1 = var1 + var2;        // var1 now holds the sum
        var2 = (var1 -  var2);     // var2 now holds 20% of var1
        var1 = (var1 - var2);
        System.out.println("var1 = " + ((int)(0.10*var1)));
        System.out.println("var2 = " + ((int)(0.20*var2)));
    }
}
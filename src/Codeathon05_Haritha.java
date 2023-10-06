package src;
import java.util.Scanner;

class Rectangle{
    int width;
    int height;

    void display() {
        System.out.println(width + " " + height);
    }
}

class RectangleArea extends Rectangle {
    void read_input() {
        Scanner scanner = new Scanner(System.in);
        width = scanner.nextInt();
        height = scanner.nextInt();
        scanner.close();
    }

    @Override
    void display() {
        super.display();
        int area = width * height;
        System.out.println(area);
    }
}

public class Codeathon05_Haritha {
    public static void main(String[] args) {
        RectangleArea rectangle = new RectangleArea();
        rectangle.read_input();
        rectangle.display();
    }
}
package src;
import java.util.Scanner;
public class Codeathon03_Haritha {
  public static void main(String[] args)
  {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();          //  Number of Trees
    int[] heights = new int[n];    // Heights of the Trees
    for (int i = 0; i < n; i++)
    {
      heights[i] = sc.nextInt();
    }
    int maxTravelTime = 0;
    int clockwiseLength, anticlockwiseLength, shorterLength, totalLength;
    for(int i=0;i<n;i++)
    {
      for(int j=i+1;j<n;j++)
      {
        clockwiseLength = (n - j + i) % n;         // length in clock wise direction
        anticlockwiseLength = (j - i + n) % n;     // length in anti-clock wise direction

        shorterLength = Math.min(clockwiseLength, anticlockwiseLength);
        totalLength = shorterLength + heights[i] + heights[j];  // maximum path length
        if (totalLength > maxTravelTime)
        {
          maxTravelTime = totalLength;      // maximum travel time
        }
      }
    }
    System.out.println(maxTravelTime);
  }
}

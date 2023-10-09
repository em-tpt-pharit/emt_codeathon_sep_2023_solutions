package src;
import java.util.Scanner;

public class Codeathon01_Haritha
{
  public static void main(String args[])
  {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();                  // It stores the User names
    String usernames[] = new String[n];
    String results[];                     // To store YES / NO  values

    for (int i = 0; i < n; i++)
      usernames[i] = sc.next();

    //calling method possible Changes with usernames
    results =DictionarySeries.possibleChanges(usernames);

    //displaying the results of possible changes YES / NO for user names
    for (int i = 0; i < n; i++)
      System.out.println(results[i] + "\t");
  }
}

class DictionarySeries
{
  // method to know possible changes in each user name
  public static String[] possibleChanges(String usernames[])
  {
    int n = usernames.length;
    String results[] = new String[n];

    int check;
    // starting from first user name, checking each user name for possible changes
    for(int i=0;i<n;i++)
    {
      check=0;
      String duplicate = usernames[i].toLowerCase();
      // checking for possible change
      for(int j=1; j<duplicate.length(); j++)
      {
        if ((duplicate.charAt(j-1))> (duplicate.charAt(j)))
        {
          check++;
        }
      }
      if (check > 0)
        results[i]="YES";
      else
        results[i]="NO";
    }
    return results;
  }
}

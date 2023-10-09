package src;
import java.io.File;
import java.util.*;

public class Codeathon07_Haritha {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter a valid Windows directory: ");
    String inputDirectory = scanner.nextLine();

    File rootDirectory = new File(inputDirectory); // Create a File object representing the root directory provided by the user

    if (!rootDirectory.exists() || !rootDirectory.isDirectory()) {
      System.out.println("Directory Not Found on the Filesystem");
      return;
    }

    Map<String, List<String>> directoryMap = new TreeMap<>(); // Create a TreeMap to store directory paths and corresponding lists of files

    collectFiles(rootDirectory, directoryMap);

    for (Map.Entry<String, List<String>> entry : directoryMap.entrySet()) {   // Print the collected directory paths and associated file lists
      System.out.println("Directory: " + entry.getKey());
      System.out.println("Files: " + entry.getValue());
    }
  }

  private static void collectFiles(File directory, Map<String, List<String>> directoryMap) {
    File[] files = directory.listFiles();        // List all files and subdirectories in the current directory

    if (files != null) {
      List<String> txtAndExeFiles = new ArrayList<>();    // Create a list to store names of .txt and .exe files

      for (File file : files) {
        if (file.isFile() && (file.getName().endsWith(".txt") || file.getName().endsWith(".exe"))) {
          txtAndExeFiles.add(file.getName());    // If the file has a .txt or .exe extension, add the name of it
        } else if (file.isDirectory()) {
          collectFiles(file, directoryMap);
        }
      }

      if (!txtAndExeFiles.isEmpty()) {
        directoryMap.put(directory.getAbsolutePath(), txtAndExeFiles);
      }
      for (File subDirectory : files) {
        if (subDirectory.isDirectory()) {
          collectFiles(subDirectory, directoryMap);
        }
      }
    }
  }
}
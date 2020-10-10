package pl.coderslab;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TaskManager {
  static final String FILE_NAME = "tasks.csv";
  static final String[] OPTIONS = {"add", "remove", "list", "exit"};
  //  public static void main(String[] args){}
  //  public static void main(String[] args){}
  //  public static void main(String[] args){}
  //  public static void main(String[] args){}
  public static void main(String[] args) throws IOException {
    chooseOption(OPTIONS);
    odczyt(FILE_NAME);
  }

  public static void odczyt(String FILE_NAME) {
    Path path = Paths.get(FILE_NAME);
    StringBuilder reading = new StringBuilder();
    try {
      Scanner scan = new Scanner(path);
      while (scan.hasNextLine()) {
        reading.append(scan.nextLine() + "\n");
      }
    } catch (IOException ex) {
      System.err.println("Nie można odczytać pliku.");
    }
    System.out.println(reading.toString());
  }

  public static void chooseOption(String[] tab) {
    Scanner input = new Scanner(System.in);
    System.out.println(ConsoleColors.BLUE);
    System.out.println("Please select an option: " + ConsoleColors.RESET);
    for (String option : tab) {
      System.out.println(option);
    }
  }
}

package pl.coderslab;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
  static final String FILE_NAME = "tasks.csv";
  static final String[] OPTIONS = {"add", "remove", "list", "exit"};
  static String[][] Tasks;

  public static void main(String[] args) throws IOException {
    Tasks = odczyt(FILE_NAME);
    odczyt(FILE_NAME);
    chooseOption(OPTIONS);
    menu(OPTIONS);
  }

  public static String[][] odczyt(String fileName) {
    Path dir = Paths.get(fileName);
    if (!Files.exists(dir)) {
      System.out.println("Plik nie istnieje.");
      System.exit(0);
    }
    String[][] tab = null;
    try {
      List<String> strings = Files.readAllLines(dir);
      tab = new String[strings.size()][strings.get(0).split(",").length];
      for (int i = 0; i < strings.size(); i++) {
        String[] splitt = strings.get(i).split(",");
        for (int j = 0; j < splitt.length; j++) {
          tab[i][j] = splitt[j];
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return tab;
  }

  public static void chooseOption(String[] tab) {
    menu(OPTIONS);
    Scanner scan = new Scanner(System.in);
    while (scan.hasNextLine()) {
      String input = scan.nextLine();
      switch (input) {
        case "list":
          pokazListe(Tasks);
          break;
        case "exit":
          System.out.println(ConsoleColors.RED);
          System.out.println("Bye, bye.");
          System.exit(0);
        default:
          System.out.println("Please select a correct option");
      }
      menu(OPTIONS);
    }
  }

  public static void pokazListe(String[][] tab) {
    for (int i = 0; i < tab.length; i++) {
      System.out.print(i + " : ");
      for (int j = 0; j < tab[i].length; j++) {
        System.out.print(tab[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void menu(String[] tab) {
    System.out.println(ConsoleColors.BLUE);
    System.out.println("Please select an option: " + ConsoleColors.RESET);
    for (String option : tab) {
      System.out.println(option);
    }
  }
}

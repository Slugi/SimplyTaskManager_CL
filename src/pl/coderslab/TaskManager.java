package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

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
  static String[][] zadania;

  public static void main(String[] args) throws IOException {
    zadania = odczyt(FILE_NAME);
    odczyt(FILE_NAME);
    wybierzOpcje(OPTIONS);
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

  public static void wybierzOpcje(String[] tab) {
    menu(OPTIONS);
    Scanner scan = new Scanner(System.in);
    while (scan.hasNextLine()) {
      String input = scan.nextLine();
      switch (input) {
        case "list":
          pokazListe(zadania);
          break;
        case "remove":
          usuwanieZadania(zadania,podajNumer());
          System.out.println("Zadanie zostało pomyślnie usunięte.");
          break;
        case "add":
          dodawanieZadania();
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

  public static boolean czyWiekszaRownaZero(String input) {
    if (NumberUtils.isParsable(input)) {
      return Integer.parseInt(input) >= 0;
    }
    return false;
  }
  public static int podajNumer(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Podaj numer zadania do usunięcia");
    String num = scanner.nextLine();
    while(!czyWiekszaRownaZero(num)){
      System.out.println("Nieprawidłowa watrość. Podaj numer większy lub równy 0");
      scanner.nextLine();
    }

    return Integer.parseInt(num);
  }
  private static void usuwanieZadania(String[][] tab, int index){
    try{
      if(index<tab.length){
        zadania = ArrayUtils.remove(tab,index);
      }
    } catch (ArrayIndexOutOfBoundsException ex) {
      System.err.println("Podany element nie występuje na liście.");
    }
  }
  private static void dodawanieZadania(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Podaj opis zadania.");
    String opis = scanner.nextLine();
    System.out.println("Podaj termin wykonania zadania.");
    String data = scanner.nextLine();
    System.out.println("Czy zadanie jest ważne: true/false");
    String czyWazne = scanner.nextLine();
    zadania = Arrays.copyOf(zadania, zadania.length+1);
    zadania[zadania.length-1] = new String[3];
    zadania[zadania.length-1][0] = opis;
    zadania[zadania.length-1][1] = data;
    zadania[zadania.length-1][2] = czyWazne;

  }
}

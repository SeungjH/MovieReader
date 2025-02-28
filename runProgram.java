import java.util.Scanner;

/**
 * This class is used to run the program.
 */
public class runProgram {

  /**
   * This main method is used to run movieSearchApp
   * @param args
   */
  public static void main(String[] args) {
    MovieSearchInterface BD = new MovieSearch();
    Scanner in = new Scanner(System.in);
    MovieSearchAppInterface movieSearchApp = new MovieSearchApp(BD, in);
    movieSearchApp.runCommandLoop();
  }
}

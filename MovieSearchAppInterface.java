import java.util.List;

public interface MovieSearchAppInterface {
//runCommandLoop will run the mainMenuPrompt and use it to execute searches
  public void runCommandLoop();

  // prompt for main menu
  public char mainMenuPrompt();

//prompt to load data
  public void loadData();

  // search by title
  public void searchTitleCommand(List<String> words);

//search by year
  public void searchYearCommand(List<String> year);

  // search by popularity
  public void searchPopularityCommand(List<String> words);

  // search by year range
  public void searchYearRange(List<String>rangeMin, List<String>rangeMax);

//search by popularity range
  public void searchPopularityRange(List<String>rangeMin, List<String>rangeMax);


}

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MovieSearch implements MovieSearchInterface {

  private MovieReader movieReader;
  private MovieRedBlackTree movieRBT;

  public MovieSearch (){
    movieReader = new MovieReader();
    movieRBT = new MovieRedBlackTree();
  }

  public boolean loadData(String filename) {
    List<MovieInterface> loadedMovies;
    try {
      loadedMovies = movieReader.readMovieData(filename);
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist: " + e.getMessage());
      return false;
    }

//    RBTList<String>[] loadedTitle = new RBTList[loadedMovies.size()];
//    for (int i = 0; i<loadedMovies.size();i++) {
//      if (loadedTitle[i] == null) {
//        loadedTitle[i] = new RBTList<String>(loadedMovies.get(i).getTitle());
//      }
//      loadedTitle[i].add(loadedMovies.get(i));
//    }
//    for (int i = 0; i< loadedTitle.length;i++) {
//      if (loadedTitle[i] != null) {
//        movieRBT.insertByTitle(loadedTitle[i]);
//      }
//    }

    RBTList<String> list;
    for (MovieInterface movie : loadedMovies) {
      list = new RBTList<>(movie.getTitle());
      list.add(movie);
      movieRBT.insertByTitle(list);
    }

    RBTList<Integer>[] loadedYears = new RBTList[80]; //movies range from 1920-2000
    int yearIndex;
    for (int i = 0; i<loadedMovies.size();i++) {
      yearIndex = loadedMovies.get(i).getYear() - 1920; //starting with first movie @ index
      // 1920, so we need to subtract
      if (loadedYears[yearIndex] == null) { //handles duplicates by creating an new RBT if
        // nothing exists there
        loadedYears[yearIndex] = new RBTList<Integer>(yearIndex + 1920);
      }
      loadedYears[yearIndex].add(loadedMovies.get(i));
    }
    for (int i = 0; i< loadedYears.length;i++) {
      if (loadedYears[i] != null) {
        movieRBT.insertByYear(loadedYears[i]);
      }
    }

    RBTList<Integer>[] loadedPop = new RBTList[101];
    int popIndex;
    for (int i = 0; i<loadedMovies.size();i++) {
      popIndex = loadedMovies.get(i).getPopularity();
      if (loadedPop[popIndex] == null) { //handles duplicates by creating an new RBT if
        // nothing exists there
        loadedPop[popIndex] = new RBTList<Integer>(popIndex);
      }
      loadedPop[popIndex].add(loadedMovies.get(i));
    }
    for (int i = 0; i< loadedPop.length;i++) {
      if (loadedPop[i] != null) {
        movieRBT.insertByPopularity(loadedPop[i]);
      }
    }
    return true;
  }

  public List<MovieInterface> getMoviesByTitle(String title) {
    List<MovieInterface> movieList = movieRBT.getDataByTitle(title.strip());
    if (movieList == null) {
      movieList = new ArrayList<>();
    }
    return movieList;

  }

  public List<MovieInterface> getMoviesByPopularity(int popularity) {
    List<MovieInterface> movieList = movieRBT.getDataByPopularity(popularity);
    if (movieList == null) {
      movieList = new ArrayList<>();
    }

    return movieList;

  }

  public List<MovieInterface> getMoviesByYear(int year) {
    List<MovieInterface> movieList = movieRBT.getDataByYear(year);
    if (movieList == null) {
      movieList = new ArrayList<>();
    }

    return movieList;
  }

  public List<List<MovieInterface>> compareMoviesByYear(int year1, int year2){
    List<MovieInterface> listYear1 = getMoviesByYear(year1);
    List<MovieInterface> listYear2 = getMoviesByYear(year2);

    List<List<MovieInterface>> compareYear = new ArrayList<>();
    compareYear.add(listYear1);
    compareYear.add(listYear2);

    return compareYear;
  }

  public ArrayList<RBTList<Integer>> getMoviesByYearRange(int year1, int year2) {
    return movieRBT.getRangeData(year1, year2, true);
  }

  public ArrayList<RBTList<Integer>> getMoviesByPopularityRange(int pop1, int pop2){
    return movieRBT.getRangeData(pop1, pop2, false);
  }

}

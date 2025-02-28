//import java.util.ArrayList;
//import java.util.List;
//
//public interface MovieSearchInterface {
//  boolean loadData(String filename);
//  List<MovieInterface> getMoviesByTitle(String title);
//  List<MovieInterface> getMoviesByPopularity(int popularity);
//  List<MovieInterface> getMoviesByYear(int year);
//  List<List<MovieInterface>> compareMoviesByYear(int year1, int year2);
//  public ArrayList<RBTList<Integer>> getMoviesByYearRange(int year1, int year2);
//
//  public ArrayList<RBTList<Integer>> getMoviesByPopularityRange(int year1, int year2);
//
//
//}
import java.util.ArrayList;
import java.util.List;

public interface MovieSearchInterface {
  // public Backend ();
  public List<MovieInterface> getMoviesByTitle(String title);
  public List<MovieInterface> getMoviesByPopularity(int popularity);
  public List<MovieInterface> getMoviesByYear(int year);
  public List<List<MovieInterface>> compareMoviesByYear(int year1, int
      year2);
  public ArrayList<RBTList<Integer>> getMoviesByYearRange(int year1, int year2);
  public ArrayList<RBTList<Integer>> getMoviesByPopularityRange(int pop1, int pop2);
  public boolean loadData(String filename);
}

import java.io.FileNotFoundException;
import java.util.List;

public interface MovieReaderInterface {
    // public MovieReaderInterface();
    public List<MovieInterface> readMovieData(String filename) throws FileNotFoundException; // reads data into Movie objects stored in a list

}

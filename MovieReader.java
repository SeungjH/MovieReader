import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MovieReader implements MovieReaderInterface{

    /**
     * default MovieReader constructor
     */
    public MovieReader() {
    }

    /**
     * This method reads the .csv file that from the parameter and creates a Movie object with it.
     * @param filename filepath of the .csv file that is used to create a Movie object
     * @return  a list (List<MovieInterface>)of Movie instances
     * @throws FileNotFoundException
     */
    public List<MovieInterface> readMovieData(String filename) throws FileNotFoundException {
        List<MovieInterface> movieObjects = new ArrayList<>();
        //try-catch for catching FileNotFoundException

//        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//            String line;
//            while ((line = br.readLine()) != null) {

        try  {
            FileInputStream input = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.getMessage();
        }

        Scanner scanner = new Scanner(new File(filename));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //this is for comma separated strings and splitting general csv file parts
            String[] sep = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            //year field (if year is empty, we replace that with 0)
            int year;
            if (sep[0].isEmpty()) {
                year = 0;
            } else {
                year = Integer.parseInt(sep[0]);
            }
            //runtime field (if runtime is empty, we replace that with 0)
            int runtime;
            if (sep[1].isEmpty()) {
                runtime = 0;
            } else {
                runtime = Integer.parseInt(sep[1]);
            }
            //title, category, leadActor, leadActress, director fields
            String title = sep[2].replace("\"", "");
            String category = sep[3].replace("\"", "");
            String leadActor = sep[4].replace("\"", "");
            String leadActress = sep[5].replace("\"", "");
            String director = sep[6].replace("\"", "");
            //popularity field (if popularity is empty, we replace that with 0)
            int popularity;
            if (sep[7].isEmpty()) {
                popularity = 0;
            } else {
                popularity = Integer.parseInt(sep[7]);
            }
            //wonAwards field
            boolean wonAwards = Boolean.parseBoolean(sep[8]);
            //creating new Movie instance (newMovie)
            Movie newMovie = new Movie( year, runtime ,title,  category,  leadActor,  leadActress,  director,  popularity,  wonAwards);
            //add the newMovie to movieObjects that has a type of List<Movie>
            movieObjects.add(newMovie);
        }
        scanner.close();
        //returning a List<MovieInterface> filled with Movie instances(movieObject)
        return movieObjects;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        String filePath = "/Users/bruanhan/Desktop/CS400GroupBlueDW/testcsv.csv";
//        MovieReader csvReader = new MovieReader();
//        List<MovieInterface> movieObjects = csvReader.readMovieData(filePath);
//        for (MovieInterface dataObject : movieObjects) {
//            System.out.println(dataObject.toString());
//        }
    }
}

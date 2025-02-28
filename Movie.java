/**
 * This movie class implements MovieInterface and its instances are made by MovieReader
 */
public class Movie implements MovieInterface{
    private int year;
    private String title;
    private int runtime;
    private String category;
    private String leadActor;
    private String leadActress;
    private String director;
    private int popularity;
    private boolean wonAwards;

    /**
     * Movie class constructor
     * @param year year of the movie
     * @param title title of the movie
     * @param runtime runtime of the movie
     * @param category category of the movie
     * @param leadActor leadActor of the movie
     * @param leadActress leadActress of the movie
     * @param director director of the movie
     * @param popularity popularity of the movie
     * @param wonAwards wonAwards of the movie
     */
    public Movie(int year, int runtime, String title, String category, String leadActor, String leadActress, String director, int popularity, boolean wonAwards){
        this.year = year;
        this.runtime = runtime;
        this.title = title;
        this.category = category;
        this.leadActor = leadActor;
        this.leadActress = leadActress;
        this.director = director;
        this.popularity = popularity;
        this.wonAwards = wonAwards;

    }

    /**
     * Getter for year
     * @return year
     */
    @Override
    public int getYear(){
        return year;

    }
    /**
     * Getter for title
     * @return title
     */
    @Override
    public String getTitle(){
        return title;

    }
    /**
     * Getter for runtime in minutes
     * @return runtime in minutes
     */
    @Override
    public int getRuntime(){
        return runtime;

    }
    /**
     * Getter for category
     * @return category
     */
    @Override
    public String getCategory(){
        return category;

    }
    /**
     * Getter for leadActor
     * @return leadActor
     */
    @Override
    public String getLeadActor(){
        return leadActor;

    }
    /**
     * Getter for year
     * @return leadActress
     */
    @Override
    public String getLeadActress(){
        return leadActress;

    }
    /**
     * Getter for director
     * @return director
     */
    @Override
    public String getDirector(){
        return director;

    }
    /**
     * Getter for popularity
     * @return popularity
     */
    @Override
    public int getPopularity(){
        return popularity;

    }
    /**
     * Getter for wonAwards
     * @return wonAwards
     */
    @Override
    public boolean wonAwards(){
        return wonAwards;

    }

    /**
     * Overriden toString method to textually show what Movie object has when MovieReader is used.
     * This method is impemented outside of MoveInterface and only for the visual progress checking
     * purposes.
     *
     * @return toString value that has year, runtime, title, categoty, leadActor, leadActress,
     *         director, popularity, and wonAwards.
     */
    @Override
    public String toString() {
        return "movieObject - [" +
                "year: " + year +
                ", runtime: " + runtime +
                ", title: '" + title + '\'' +
                ", category: '" + category + '\'' +
                ", leadActor: '" + leadActor + '\'' +
                ", leadActress: '" + leadActress + '\'' +
                ", director: '" + director + '\'' +
                ", popularity: " + popularity +
                ", wonAwards: " + wonAwards +
                ']';
    }

}

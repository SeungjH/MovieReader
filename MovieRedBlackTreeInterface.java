import java.util.ArrayList;

public interface MovieRedBlackTreeInterface {
    public boolean insertByYear(RBTList<Integer> movies) throws IllegalArgumentException, NullPointerException;

    public boolean insertByTitle(RBTList<String> movies) throws IllegalArgumentException, NullPointerException;

    public boolean insertByPopularity(RBTList<Integer> movies) throws IllegalArgumentException, NullPointerException;

    // true -> year, false -> populity
    public ArrayList<RBTList<Integer>> getRangeData(int min, int max, boolean mode);

    public RBTList<Integer> getDataByYear(int year);

    public RBTList<String> getDataByTitle(String title);

    public RBTList<Integer> getDataByPopularity(int popularity);

    public RedBlackTree<RBTList<Integer>> getYearRBT();

    public RedBlackTree<RBTList<String>> getTitleRBT();

    public RedBlackTree<RBTList<Integer>> getPopularityRBT();
}

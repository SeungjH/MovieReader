
// --== CS400 Spring 2023 File Header Information ==--
// Notes to Grader: <optional extra notes>

import java.util.ArrayList;

/**
 * MovieRedBlackTreeAE class for making three different RedBlackTree depends on
 * the node data. This class have three RedBlackTree with year, title,
 * popularity data
 * and with this class we can insert node with sorting with year to yearRBT and
 * insert
 * ndoe with sorting with title to titleRBT, and insert node with sorting with
 * popularity
 * to popularity node. Also we can get speicific node data with getDataBy(Year,
 * Title, Popularity)
 * And also can get whole RBT with get(Year,Title,Popularity)RBT methods
 */
public class MovieRedBlackTree<T extends Comparable<T>> implements MovieRedBlackTreeInterface {
    private RedBlackTree<RBTList<Integer>> yearRBT; // RBT type fileds for year
    private RedBlackTree<RBTList<String>> titleRBT; // RBT type fileds for title
    private RedBlackTree<RBTList<Integer>> popularityRBT; // RBT type fileds for popularity

    // Constructor
    public MovieRedBlackTree() {
        yearRBT = new RedBlackTree<RBTList<Integer>>();
        titleRBT = new RedBlackTree<RBTList<String>>();
        popularityRBT = new RedBlackTree<RBTList<Integer>>();
    }

    /**
     * this method let us to insert nodes with sorting by year to yearRBT\with using
     * insert method in RedBackTreeAE
     * 
     * @param movies the RBTListAEtype ndoes continas movies
     * @return true if the node is inserted, false if not
     * @throws NullPointerException if the parameter is null
     */
    @Override
    public boolean insertByYear(RBTList<Integer> movies) throws NullPointerException {
        boolean result = false;

        if (movies == null) {
            throw new NullPointerException("The data is null");
        }
        // insert parameter to yearRBT with insert method in RedBlackTreeAE class
        yearRBT.insert(movies);

        // if nodes inserted, ready for return true;
        if (yearRBT.contains(movies)) {
            result = true;
        }
        return result;
    }

    /**
     * this method let us to insert nodes with sorting by title to titleRBT
     * with using insert method in RedBackTreeAE
     * 
     * @param movies the RBTListAEtype ndoes continas movies
     * @return true if the node is inserted, false if not
     * @throws NullPointerException if the parameter is null
     */
    @Override
    public boolean insertByTitle(RBTList<String> movies) throws NullPointerException {
        boolean result = false;
        if (movies == null) {
            throw new NullPointerException("The data is null");
        }

        // insert parameter to titleRBT with insert method in RedBlackTreeAE class
        titleRBT.insert(movies);

        // if nodes inserted, ready for return true;
        if (titleRBT.contains(movies)) {
            result = true;
        }
        return result;
    }

    /**
     * this method let us to insert nodes with sorting by popularity to
     * popularityRBT
     * with using insert method in RedBackTreeAE
     * 
     * @param movies the RBTListAEtype ndoes continas movies
     * @return true if the node is inserted, false if not
     * @throws NullPointerException if the parameter is null
     */
    @Override
    public boolean insertByPopularity(RBTList<Integer> movies) throws NullPointerException {
        boolean result = false;
        if (movies == null) {
            throw new NullPointerException("The data is null");
        }
        // insert parameter to titleRBT with insert method in RedBlackTreeAE class
        popularityRBT.insert(movies);

        // if nodes inserted, ready for return true;
        if (popularityRBT.contains(movies)) {
            result = true;
        }
        return result;
    }

    /**
     * If the user input secific data (ex 1994), then this method find node that
     * has same data with userinput and return it
     * 
     * @param year specific year that user want to see
     * @return the node that has data same with userinput
     */
    @Override
    public RBTList<Integer> getDataByYear(int year) {
        // change parameter to node
        RBTList<Integer> list = new RBTList<Integer>(year);
        // if the node are in the RBT then return the node
        if (yearRBT.contains(list)) {
            return yearRBT.findNodeWithData(list).data;
        }
        return null;
    }

    /**
     * If the user input secific data (ex Titanic), then this method find node that
     * has same data with userinput and return it
     * 
     * @param title specific title that user want to see
     * @return the node that has data same with userinput
     */
    @Override
    public RBTList<String> getDataByTitle(String title) {
        // change parameter to node
        RBTList<String> list = new RBTList<String>(title);
        // if the node are in the RBT then return the node
        if (titleRBT.contains(list)) {
            return titleRBT.findNodeWithData(list).data;
        }
        return null;

    }

    /**
     * If the user input secific data (ex 60), then this method find node that
     * has same data with userinput and return it
     * 
     * @param popularity specific popularity that user want to see
     * @return the node that has data same with userinput
     */
    @Override
    public RBTList<Integer> getDataByPopularity(int popularity) {
        // change parameter to node
        RBTList<Integer> list = new RBTList<Integer>(popularity);
        // if the node are in the RBT then return the node
        if (popularityRBT.contains(list)) {
            return popularityRBT.findNodeWithData(list).data;
        }
        return null;

    }

    /**
     * If the user want to see specific data with range(for example, year : 1990 ~
     * 1994 ,
     * popularity : 60 ~ 70), this method return the nodes that are included in
     * specific range.
     * user input the min, max which is refer to min ~ max and one boolen value (if
     * user input is true
     * -> this method is working in the year, if user input is false -> this method
     * is working in the
     * popularity)
     * 
     * @param min  the lowest value in the range
     * @param max  the highest value in the range
     * @param mode ture -> using with yearRBT, false, using with popularityRBT
     * @return ArrayList that contains specific nodes that are include min ~ max in
     *         yearRBT or popularityRBT or nothing
     */
    @Override
    public ArrayList<RBTList<Integer>> getRangeData(int min, int max, boolean mode) {
        // with parameter make it to node
        RBTList<Integer> minNode = new RBTList<Integer>(min);
        RBTList<Integer> maxNode = new RBTList<Integer>(max);
        // if mode is ture -> useing yearRBT
        if (mode == true) {
//            // if one of the node is not in RBT -> then return nothing
//            if (!yearRBT.contains(minNode) || !yearRBT.contains(maxNode)) {
//                return null;
//                // else then return ArrayList contains specific nodes in the range
//            } else {
                return yearRBT.SubTreeData(minNode, maxNode);
            }
            // if mode is false -> useing populairtyRBT
        else {
//            // if one of the node is not in RBT -> then return nothing
//            if (!popularityRBT.contains(minNode) || !popularityRBT.contains(maxNode)) {
//                return null;
//                // else then return ArrayList contains specific nodes in the range
//            } else {
                return popularityRBT.SubTreeData(minNode, maxNode);
            }
        }



    /**
     * this method return whole yearRBT
     * 
     * @return yearRBT
     */
    @Override
    public RedBlackTree getYearRBT() {
        return yearRBT;
    }

    /**
     * this method return whole titleRBT
     * 
     * @return titleRBT
     */
    @Override
    public RedBlackTree getTitleRBT() {
        return titleRBT;
    }

    /**
     * this method return whole popularityRBT
     * 
     * @return popularityRBT
     */
    @Override
    public RedBlackTree getPopularityRBT() {
        return popularityRBT;
    }

}

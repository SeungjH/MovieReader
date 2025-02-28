import java.util.ArrayList;

// --== CS400 Spring 2023 File Header Information ==--
// Name: <Hyeonmin Yang>
// Email: <hyang486@wisc.edu>
// Team: <BJ>
// TA: <Naman Gupta>
// Lecturer: <CS400 Lecture002>
// Notes to Grader: <optional extra notes>

public interface RedBlackTreeInterface<T extends Comparable<T>> extends SortedCollectionInterface<T> {
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException;

    public boolean remove(T data) throws NullPointerException, IllegalArgumentException;

    public boolean contains(T data);

    public int size();

    public boolean isEmpty();

    public ArrayList<T> SubTreeData(T data1, T data2) throws NullPointerException;

}

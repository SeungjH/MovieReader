import java.util.ArrayList;

public class RBTList<T extends Comparable<T>> extends ArrayList<MovieInterface>
        implements Comparable<RBTList<T>> {

    public T value; // either year or title
    ArrayList movieList;

    public RBTList(T value) {
        super();
        this.value = value;
    }

    @Override
    public int compareTo(RBTList<T> otherValue) {
        return value.compareTo(otherValue.value);
    }
}

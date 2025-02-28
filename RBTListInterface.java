// --== CS400 Spring 2023 File Header Information ==--
// Name: <Hyeonmin Yang>
// Email: <hyang486@wisc.edu>
// Team: <BJ>
// TA: <Naman Gupta>
// Lecturer: <CS400 Lecture002>
// Notes to Grader: <optional extra notes>

/**
 * place holder interface from BackEndEngineer
 */
public interface RBTListInterface<T extends Comparable<T>> extends Comparable<T> { // extends ArrayList
    // all methods from ArrayList and Comparable
    public int compareTo(RBTList<T> otherValue);
}

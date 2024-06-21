import java.util.Comparator;

/**
 * Comparator class to compare two Student objects based on their names.
 * @author Yinglong Lin
 * @version Java 11 / VSCode
 * @since 2024-6-21 (date of last revision)
 */
public class ComparatorByName implements Comparator<Student> {

    /**
     * Compares two Student objects by their names in lexicographical order.
     * 
     * @param s1 the first Student object to be compared
     * @param s2 the second Student object to be compared
     * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
     */
    public int compare(Student s1, Student s2) {
        return s1.getName().compareTo(s2.getName());
    }
}

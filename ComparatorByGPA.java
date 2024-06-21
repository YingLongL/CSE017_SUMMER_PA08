import java.util.Comparator;

/**
 * Comparator class to compare two Student objects based on their GPA.
 * @author Yinglong Lin
 * @version Java 11 / VSCode
 * @since 2024-6-21 (date of last revision)
 */
public class ComparatorByGPA implements Comparator<Student> {

    /**
     * Compares two Student objects by their GPA in descending order.
     * 
     * @param s1 the first Student object to be compared
     * @param s2 the second Student object to be compared
     * @return a negative integer, zero, or a positive integer as the first argument has a greater than, equal to, or less than GPA than the second.
     */
    public int compare(Student s1, Student s2) {
        return Double.compare(s2.getGPA(), s1.getGPA());
    }
}

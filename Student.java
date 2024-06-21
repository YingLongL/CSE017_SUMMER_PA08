/**
 * Class representing a Student with an id, name, and GPA.
 * Implements the Comparable interface to allow comparison by id.
 * @author Yinglong Lin
 * @version Java 11 / VSCode
 * @since 2024-6-21 (date of last revision)
 */
public class Student implements Comparable<Student> {
    private int id;
    private String name; 
    private double gpa;

    /**
     * Constructs a new Student with the specified id, name, and gpa.
     * 
     * @param id the student's id
     * @param name the student's name
     * @param gpa the student's GPA
     */
    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    /**
     * Gets the student's id.
     * 
     * @return the student's id
     */
    public int getId() { 
        return id; 
    }

    /**
     * Gets the student's name.
     * 
     * @return the student's name
     */
    public String getName() { 
        return name; 
    }

    /**
     * Gets the student's GPA.
     * 
     * @return the student's GPA
     */
    public double getGPA() { 
        return gpa; 
    }

    /**
     * Compares this student with another student by id.
     * 
     * @param other the other student to be compared
     * @return a negative integer, zero, or a positive integer as this student's id is less than, equal to, or greater than the specified student's id
     */
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }

    /**
     * Returns a string representation of the student.
     * 
     * @return a string containing the student's id, name, and GPA
     */
    public String toString() {
        return String.format("Student(id=%d, name=%s, gpa=%.2f)", id, name, gpa);
    }
}

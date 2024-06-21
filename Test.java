import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Test class to demonstrate the functionality of ArrayList and LinkedList with Student objects.
 * It reads student data from a file, populates both lists, sorts them using different comparators, 
 * and prints the sorted lists along with the performance of the sorting methods.
 * @author Yinglong Lin
 * @version Java 11 / VSCode
 * @since 2024-6-21 (date of last revision)
 */
public class Test {

    /**
     * Main method to run the test.
     * 
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        ArrayList<Student> arrayList = new ArrayList<>();
        LinkedList<Student> linkedList = new LinkedList<>();

        // Read students from file and populate lists
        try (BufferedReader br = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double gpa = Double.parseDouble(parts[2]);
                Student s = new Student(id, name, gpa);
                arrayList.add(s);
                linkedList.add(s);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        Comparator<Student> nameComparator = new ComparatorByName();
        Comparator<Student> gpaComparator = new ComparatorByGPA();

        // Sort ArrayList by ID
        ArrayList.sortIterations = 0;
        arrayList.sort(null);
        int arrayListIdIterations = ArrayList.sortIterations;
        System.out.println("ArrayList sorted by ID");
        printList(arrayList);

        // Sort LinkedList by ID
        LinkedList.sortIterations = 0;
        linkedList.sort(null);
        int linkedListIdIterations = LinkedList.sortIterations;
        System.out.println("\nLinkedList sorted by ID");
        printList(linkedList);

        // Sort ArrayList by name
        ArrayList.sortIterations = 0;
        arrayList.sort(nameComparator);
        int arrayListNameIterations = ArrayList.sortIterations;
        System.out.println("\nArrayList sorted by name");
        printList(arrayList);

        // Sort LinkedList by name
        LinkedList.sortIterations = 0;
        linkedList.sort(nameComparator);
        int linkedListNameIterations = LinkedList.sortIterations;
        System.out.println("\nLinkedList sorted by name");
        printList(linkedList);

        // Sort ArrayList by gpa
        ArrayList.sortIterations = 0;
        arrayList.sort(gpaComparator);
        int arrayListGpaIterations = ArrayList.sortIterations;
        System.out.println("\nArrayList sorted by gpa");
        printList(arrayList);

        // Sort LinkedList by gpa
        LinkedList.sortIterations = 0;
        linkedList.sort(gpaComparator);
        int linkedListGpaIterations = LinkedList.sortIterations;
        System.out.println("\nLinkedList sorted by gpa");
        printList(linkedList);

        // Performance comparison
        System.out.println("\nPerformance of the sort method (# iterations)");
        System.out.println("List            by ID           by Name         by GPA");
        System.out.printf("ArrayList       %-15d %-15d %-15d\n", arrayListIdIterations, arrayListNameIterations, arrayListGpaIterations);
        System.out.printf("LinkedList      %-15d %-15d %-15d\n", linkedListIdIterations, linkedListNameIterations, linkedListGpaIterations);
    }

    /**
     * Prints the elements of the ArrayList.
     * 
     * @param list the ArrayList of Student objects to be printed
     */
    private static void printList(ArrayList<Student> list) {
        Iterator<Student> iterator = list.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            System.out.printf("%-15d %-30s %5.2f\n", s.getId(), s.getName(), s.getGPA());
        }
    }

    /**
     * Prints the elements of the LinkedList.
     * 
     * @param list the LinkedList of Student objects to be printed
     */
    private static void printList(LinkedList<Student> list) {
        Iterator<Student> iterator = list.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            System.out.printf("%-15d %-30s %5.2f\n", s.getId(), s.getName(), s.getGPA());
        }
    }
}

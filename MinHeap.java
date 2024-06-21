import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.ArrayList;

/**
 * Provided ALA8 solution file for PA8
 * 
 * @param <E> the type of elements in this heap
 * @author Yinglong Lin
 * @version Java 11 / VSCode
 * @since 2024-6-21 (date of last revision)
 */
public class MinHeap<E> {
    public static int addIterations, removeIterations;
    // ArrayList where the nodes of the heap are stored
    private ArrayList<E> list;
    private Comparator<E> comp;

    /**
     * Default Constructor
     * Creates an empty MinHeap with no comparator.
     */
    public MinHeap() {
        list = new ArrayList<>();
        comp = null;
        addIterations = 0;
        removeIterations = 0;
    }

    /**
     * Constructor with one parameter
     * Creates an empty MinHeap with the given comparator.
     * 
     * @param c the comparator to determine the order of the heap
     */
    public MinHeap(Comparator<E> c) {
        list = new ArrayList<>();
        comp = c;
        addIterations = 0;
        removeIterations = 0;
    }

    /**
     * Method size
     * 
     * @return the number of nodes in the heap
     */
    public int size() {
        return list.size();
    }

    /**
     * Method isEmpty
     * 
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Method to empty the heap
     */
    public void clear() {
        list.clear();
    }

    /**
     * Method toString
     * 
     * @return a formatted string containing the values of the nodes of the heap
     */
    public String toString() {
        return list.toString();
    }

    /**
     * Method getRoot
     * 
     * @return the value of the root
     * @throws NoSuchElementException if the heap is empty
     */
    public E getRoot() {
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }
        return list.get(0);
    }

    /**
     * Method contains
     * 
     * @param value the value being searched in the heap
     * @return true if the value is found, false otherwise
     */
    public boolean contains(E value) {
        return list.contains(value);
    }

    /**
     * Method add
     * Adds a value to the heap and reconstructs the heap to maintain the MinHeap properties.
     * 
     * @param value the value to be added to the heap
     */
    public void add(E value) {
        addIterations = 0;
        list.add(value);
        int currentIndex = list.size() - 1;
        while (currentIndex > 0) {
            addIterations++;
            int parentIndex = (currentIndex - 1) / 2;
            E current = list.get(currentIndex);
            E parent = list.get(parentIndex);
            if (comp == null) {
                if (((Comparable<E>) current).compareTo(parent) < 0) {
                    list.set(currentIndex, parent);
                    list.set(parentIndex, current);
                } else
                    break;
            } else {
                if (comp.compare(current, parent) < 0) {
                    list.set(currentIndex, parent);
                    list.set(parentIndex, current);
                } else
                    break;
            }
            currentIndex = parentIndex;
        }
    }

    /**
     * Method remove
     * Removes and returns the value of the root, reconstructing the heap to maintain the MinHeap properties.
     * 
     * @return the value of the root, or null if the heap is empty
     */
    public E remove() {
        removeIterations = 0;
        if (list.isEmpty())
            return null;
        E removedItem = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        int currentIndex = 0;
        while (currentIndex < list.size()) {
            removeIterations++;
            int left = 2 * currentIndex + 1;
            int right = 2 * currentIndex + 2;
            if (left >= list.size())
                break;
            int minIndex = left;
            E min = list.get(minIndex);
            if (right < list.size()) {
                if (comp == null) {
                    if (((Comparable<E>) min).compareTo(list.get(right)) > 0)
                        minIndex = right;
                } else {
                    if (comp.compare(min, list.get(right)) > 0)
                        minIndex = right;
                }
            }
            E current = list.get(currentIndex);
            min = list.get(minIndex);
            if (comp == null) {
                if (((Comparable<E>) current).compareTo(min) > 0) {
                    list.set(minIndex, current);
                    list.set(currentIndex, min);
                    currentIndex = minIndex;
                } else
                    break;
            } else {
                if (comp.compare(current, min) > 0) {
                    list.set(minIndex, current);
                    list.set(currentIndex, min);
                    currentIndex = minIndex;
                } else
                    break;
            }
        }
        return removedItem;
    }
}

import java.util.Iterator;
import java.util.Comparator;

/**
 * Generic class to implement an array-based list.
 * Provided ALA7 solution file for PA8.
 * 
 * @param <E> the type of elements in this list
 * @author Yinglong Lin
 * @version Java 11 / VSCode
 * @since 2024-6-21 (date of last revision)
 */
public class ArrayList<E> {
    public static int containsIterations, addIterations, removeIterations;
    // data member: array for the list elements
    private E[] elements;
    // data member: size of the list
    private int size;

    /**
     * Default constructor creates the array with a default length of 10 and sets size to 0.
     */
    public ArrayList() { // O(1)
        elements = (E[]) new Object[10];// instantiate a concrete class because we cannot instantiate E
        size = 0;
    }

    /**
     * Constructor with one parameter creates the array with length equal to capacity and sets size to 0.
     * 
     * @param capacity length of the array elements
     */
    public ArrayList(int capacity) { // O(1)
        elements = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * Method to add a new item at the end of the list.
     * 
     * @param item the value of the item to be added
     * @return true if item was added successfully, false otherwise
     */
    public boolean add(E item) {// O(1) --> O(n)
        return add(size, item); // adding at the first free index
    }

    /**
     * Method to add a new item at a given position index.
     * 
     * @param index the position where item should be added
     * @param item  the value of the element to be added
     * @return true if item was added successfully, false otherwise
     * @throws ArrayIndexOutOfBoundsException if index < 0 or index > size
     */
    public boolean add(int index, E item) { // O(n)
        addIterations = 0;
        if (index > size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        ensureCapacity(); // O(n)
        for (int i = size - 1; i >= index; i--) {
            addIterations++;
            elements[i + 1] = elements[i];
        }
        elements[index] = item;
        size++;
        return true;
    }

    /**
     * Get the value of the element at index.
     * 
     * @param index of the element being accessed
     * @return the value of the element at index
     * @throws ArrayIndexOutOfBoundsException if index < 0 or index >= size
     */
    public E get(int index) { // O(1)
        checkIndex(index); // O(1)
        return elements[index];
    }

    /**
     * Set the value of the element at index.
     * 
     * @param index    of the element being modified
     * @param value    new value of the element at index
     * @return the old value of the element at index
     * @throws ArrayIndexOutOfBoundsException if index < 0 or index >= size
     */
    public E set(int index, E newValue) { // O(1)
        checkIndex(index); // O(1)
        E oldValue = elements[index];
        elements[index] = newValue;
        return oldValue;
    }

    /**
     * Get the size of the list.
     * 
     * @return the number of elements in the list
     */
    public int size() { // O(1)
        return size;
    }

    /**
     * Clear the list by setting size to 0.
     */
    public void clear() {// O(1)
        size = 0;
    }

    /**
     * Predicate to check if the list is empty.
     * 
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() { // O(1)
        return (size == 0);
    }

    /**
     * Remove the element at a given index.
     * 
     * @param index the position of the element to be removed
     * @return true if the element was removed successfully, false otherwise
     * @throws ArrayIndexOutOfBoundsException if index < 0 or index >= size
     */
    public boolean remove(int index) { // O(n)
        checkIndex(index); // O(1)
        for (int i = index; i < size - 1; i++) {
            removeIterations++;
            elements[i] = elements[i + 1];
        }
        size--;
        return true;
    }

    /**
     * Resize the length of the array 'elements' to the size of the list.
     */
    public void trimToSize() { // O(n)
        if (size != elements.length) {
            E[] newElements = (E[]) new Object[size];// capacity = size
            for (int i = 0; i < size; i++) // n iterations
                newElements[i] = elements[i];
            elements = newElements;
        }
    }

    /**
     * Grow the length of the array 'elements' by 1.5 if it is full.
     */
    private void ensureCapacity() { // O(n) - worst case
        if (size >= elements.length) {
            int newCap = (int) (elements.length * 1.5);
            E[] newElements = (E[]) new Object[newCap];
            for (int i = 0; i < size; i++) {
                addIterations++;
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    /**
     * Check if the index is valid.
     * 
     * @param index to be checked
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    private void checkIndex(int index) { // O(1)
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds. Must be between 0 and " + (size - 1));
    }

    /**
     * Override toString() from class Object.
     * 
     * @return a formatted string containing the elements of the list
     */
    public String toString() { // O(n)
        String output = "[";
        for (int i = 0; i < size - 1; i++)
            output += elements[i] + " ";
        output += elements[size - 1] + "]";
        return output;
    }

    /**
     * Override iterator() from the interface Collection.
     * 
     * @return iterator object pointing to the first element of the list
     */
    public Iterator<E> iterator() { // O(1)
        return new ArrayIterator();
    }

    /**
     * Inner class to implement the interface Iterator<E>.
     */
    private class ArrayIterator implements Iterator<E> {
        // Data member current: the index of the element at which the iterator is pointing
        private int current = 0;

        /**
         * @return true if current did not reach the end of the list, false otherwise
         */
        public boolean hasNext() { // O(1)
            return current < size;
        }

        /**
         * @return the value of the current element and moves the index current to the next element
         * @throws ArrayIndexOutOfBoundsException if current is out of bounds
         */
        public E next() { // O(1)
            if (current < 0 || current >= size)
                throw new ArrayIndexOutOfBoundsException("No more elements");
            return elements[current++];
        }
    }

    /**
     * Method contains(Object)
     * Checks if the list contains a specific element.
     * 
     * @param o the element to be checked
     * @return true if the list contains the specified element, false otherwise
     */
    public boolean contains(Object o) { // O(n)
        Iterator<E> iter = iterator();
        containsIterations = 0;
        while (iter.hasNext()) {
            containsIterations++;
            if (iter.next().equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method remove(Object)
     * Removes the first occurrence of the specified element from the list.
     * 
     * @param o the element to be removed
     * @return true if the element was successfully removed, false otherwise
     */
    public boolean remove(Object o) { // O(n)
        removeIterations = 0;
        for (int i = 0; i < size; i++) {
            removeIterations++;
            if (elements[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public static int sortIterations = 0;

    /**
     * Sorts the list using the specified comparator.
     * 
     * @param comp the comparator to determine the order of the list. A null value indicates that the elements' natural ordering should be used.
     */
    public void sort(Comparator<E> comp) {
        sortIterations = 0;
        MinHeap<E> heap;

        if (comp != null) {
            heap = new MinHeap<>(comp);
        } else {
            heap = new MinHeap<>();
        }

        for (int i = 0; i < size; i++) {
            sortIterations++;
            heap.add(elements[i]);
        }

        clear();

        while (!heap.isEmpty()) {
            sortIterations++;
            add(heap.remove());
        }
    }
}

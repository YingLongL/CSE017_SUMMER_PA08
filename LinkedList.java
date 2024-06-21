import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 * LinkedList Generic Class.
 * Provided ALA7 solution file for PA8.
 * 
 * @param <E> the type of elements in this list
 * @author Yinglong Lin
 * @version Java 11 / VSCode
 * @since 2024-6-21 (date of last revision)
 */
public class LinkedList<E> {
    public static int containsIterations, addIterations, removeIterations;
    // Data members
    private Node head, tail;
    private int size;

    /**
     * Inner class Node.
     */
    private class Node {
        E value;
        Node next;

        Node(E initialValue) {
            value = initialValue;
            next = null;
        }
    }

    /**
     * Default Constructor.
     * Creates an empty linked list.
     */
    public LinkedList() { // O(1)
        head = tail = null;
        size = 0;
    }

    /**
     * Adding a value at the head of the list.
     * 
     * @param value the value to be added
     * @return true if the operation was successful
     */
    public boolean addFirst(E value) { // O(1)
        Node newNode = new Node(value);
        if (head == null) { // first node to be added
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
        return true;
    }

    /**
     * Adding a value at the tail of the list.
     * 
     * @param value the value to be added
     * @return true if the operation was successful
     */
    public boolean addLast(E item) { // O(1)
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }

    /**
     * Adding a value at the tail of the list.
     * Calls addLast.
     * 
     * @param value the value to be added
     * @return true if the operation was successful
     */
    public boolean add(E item) { // O(1)
        return addLast(item);
    }

    /**
     * Get the value of the node at the head of the list.
     * 
     * @return value of the node at the head
     * @throws NoSuchElementException if the list is empty
     */
    public E getFirst() { // O(1)
        if (head == null)
            throw new NoSuchElementException();
        return head.value;
    }

    /**
     * Get the value of the node at the tail of the list.
     * 
     * @return value of the node at the tail
     * @throws NoSuchElementException if the list is empty
     */
    public E getLast() { // O(1)
        if (head == null)
            throw new NoSuchElementException();
        return tail.value;
    }

    /**
     * Removes the node at the head of the list.
     * 
     * @return true if the remove operation was successful and decrements the size
     * @throws NoSuchElementException if the list is empty
     */
    public boolean removeFirst() { // O(1)
        if (head == null)
            throw new NoSuchElementException();
        head = head.next;
        if (head == null) // removed the only node in the LL
            tail = null;
        size--;
        return true;
    }

    /**
     * Removes the node at the tail of the list.
     * 
     * @return true if the remove operation was successful and decrements the size
     * @throws NoSuchElementException if the list is empty
     */
    public boolean removeLast() { // O(n)
        if (head == null)
            throw new NoSuchElementException();
        if (size == 1)
            return removeFirst();
        Node current = head;
        while (current.next != tail) { // looking for the node before the tail
            current = current.next;
        }
        current.next = null; // current becomes the last node
        tail = current;
        size--;
        return true;
    }

    /**
     * toString method.
     * 
     * @return a formatted string that contains the values of all the nodes in the list
     */
    public String toString() { // O(n)
        String output = "[";
        Node node = head;
        while (node != null) {
            output += node.value + " ";
            node = node.next;
        }
        output += "]";
        return output;
    }

    /**
     * Clear method.
     * Resets size to 0 and head and tail to null.
     */
    public void clear() { // O(1)
        head = tail = null;
        size = 0;
    }

    /**
     * isEmpty method.
     * 
     * @return true if the list is empty
     */
    public boolean isEmpty() { // O(1)
        return (size == 0);
    }

    /**
     * Size method.
     * 
     * @return the number of nodes in the list
     */
    public int size() { // O(1)
        return size;
    }

    /**
     * Iterator method.
     * 
     * @override iterator() from the interface Collection
     * @return an iterator object pointing to the first value in the list
     */
    public Iterator<E> iterator() { // O(1)
        return new LinkedListIterator();
    }

    /**
     * Inner class that implements the interface Iterator.
     */
    private class LinkedListIterator implements Iterator<E> {
        private Node current = head;

        /**
         * hasNext method.
         * 
         * @return true if current is not null
         */
        public boolean hasNext() { // O(1)
            return (current != null);
        }

        /**
         * next method.
         * 
         * @return the value of the node referenced by current and modifies current to hold the reference of the next node
         * @throws NoSuchElementException if current is null
         */
        public E next() { // O(1)
            if (current == null)
                throw new NoSuchElementException();
            E value = current.value;
            current = current.next;
            return value;
        }
    }

    /**
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
     * Adds a value at a specified index in the list.
     * 
     * @param index the index at which the value should be added
     * @param item  the value to be added
     * @return true if the operation was successful
     * @throws ArrayIndexOutOfBoundsException if the index is out of bounds
     */
    public boolean add(int index, E item) { // O(n)
        addIterations = 0;
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == 0) {
            return addFirst(item);
        }
        if (index == size) {
            return addLast(item);
        }
        Node current = head;
        Node previous = null;
        for (int i = 0; i < index; i++) {
            addIterations++;
            previous = current;
            current = current.next;
        }
        Node newNode = new Node(item);
        previous.next = newNode;
        newNode.next = current;
        size++;
        return true;
    }

    /**
     * Removes a specific element from the list.
     * 
     * @param o the element to be removed
     * @return true if the element was successfully removed, false otherwise
     */
    public boolean remove(Object o) {
        removeIterations = 0;
        Node current = head;
        Node previous = null;
        while (current != null && !current.value.equals(o)) {
            removeIterations++;
            previous = current;
            current = current.next;
        }
        if (current == null) {
            return false;
        }
        if (current == head) {
            return removeFirst();
        }
        previous.next = current.next;
        size--;
        return true;
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

        Node current = head;
        while (current != null) {
            sortIterations++;
            heap.add(current.value);
            current = current.next;
        }

        clear();

        while (!heap.isEmpty()) {
            sortIterations++;
            add(heap.remove());
        }
    }
}

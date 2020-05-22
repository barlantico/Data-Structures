
/* Brian Arlantico
   cssc1453
*/

package data_structures;

import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class LinearList<E extends Comparable<E>> implements LinearListADT<E> {

    class Node<T>  {

        T data;
        Node<T> next;
        Node<T> prev;

        //default constructor
        public Node (T obj) {
            data = obj;
            next = null;
            prev = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int currentSize;
    private long modificationCounter;

    //default no-arg constructor
    public LinearList() {
        tail = head = null;
        currentSize = 0;
        modificationCounter = 0;
    }

    /**
      * Adds the element to the beginning of the linked list.
      *
      * @return boolean true if object is added.
      */
    @Override
    public boolean addFirst(E obj) {
        Node<E> newNode = new Node<>(obj);
        newNode.next = head;
        if (currentSize > 0)
            head.prev = newNode;
        head = newNode;
        incrementSize();
        if (currentSize == 1) //sets head and tail to single element.
            tail = head;
        return true;
    }

    /**
     * Adds the element to the end of the linked list.
     *
     * @return boolean true if object is added.
     */
    @Override
    public boolean addLast(E obj) {
        Node<E> newNode = new Node<>(obj);
        if (head == null) {
            head = tail = newNode;
            incrementSize();
            return true;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        incrementSize();
        return true;
    }

    /**
     * Removes and returns the first (head) element of the list.
     *
     * @return E data of node.
     */
    @Override
    public E removeFirst() {
        E tmp;
        if (head == null)
            return null;

        tmp = head.data;
        if (head == tail)
            head = tail = null;
         else {
            head = head.next;
            head.prev = null;
        }
         decrementSize();
        return tmp;
    }

    /**
     * Removes and returns the last (tail) element of the list.
     *
     * @return E data of node.
     */
    @Override
    public E removeLast() {
        E tmp;
        if (tail == null)
            return null;

        tmp = tail.data;
        if (head == tail) //single element case
            head = tail = null;
        else {
            tail = tail.prev;
            tail.next = null;
        }

        decrementSize();
        return tmp;
    }

    /**
     *  Transverses list and removes the node
     *  matching the argument obj.
     *
     * @return E data of node. Returns null if not found.
     */
    @Override
    public E remove(E obj) {

        Node<E> succNode;
        Node<E> predNode;
        Node<E> tmp = head;

        if (head == null)
            return null;

        while (tmp != null && ((Comparable<E>)tmp.data).compareTo(obj) != 0) {
            tmp = tmp.next;
        }

        if (tmp == null) //obj not found
            return null;

        if (((Comparable<E>)tmp.data).compareTo(obj) == 0) {
            if (tmp == head)
                return removeFirst();
            else if (tmp == tail)
                return removeLast();
            else {
                predNode = tmp.prev;
                succNode = tmp.next;
                predNode.next = succNode;
                succNode.prev = predNode;
                decrementSize();
                return tmp.data;
            }
        }
        return null;
    }

    /**
     * Returns but does not remove the data
     * from the head node.
     *
     * @return E data of first node.
     */
    @Override
    public E peekFirst() {
        if (head == null)
            return null;
        return head.data;
    }

    /**
     * Returns but does not remove the data
     * from the tail node.
     *
     * @return E data of last node.
     */
    @Override
    public E peekLast() {
        if (tail == null)
            return null;
        return tail.data;
    }
    /**
     * Transverses the list and returns boolean
     * whether the obj was found in the list.
     *
     * @return boolean true if found. False otherwise.
     */
    @Override
    public boolean contains(E obj) {
        Node<E> tmp = head;
        while (tmp != null) {
            if (((Comparable<E>)obj).compareTo(tmp.data) == 0)
                return true;
            tmp = tmp.next;
        }
        return false;
    }

    /**
     * Calls contains method but instead
     * returns obj if found in list, no removal.
     *
     * @return E data of found obj.
     */
    @Override
    public E find(E obj) {
        if (contains(obj))
            return obj;
        return null;
    }

    /**
     * Sets head and tail to null and currentSize
     * to 0.
     */
    @Override
    public void clear() {
        tail = head = null;
        currentSize = 0;
    }

    /**
     * Compares head to null to determine if empty.
     *
     * @return True if list is empty. False otherwise.
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns false as linkedlists have no
     * set size.
     *
     * @return boolean False.
     */
    @Override
    public boolean isFull() {
        return false;
    }

    /**
     * Returns size of list.
     *
     * @return int currentSize.
     */
    @Override
    public int size() {
        return currentSize;
    }

    private void incrementSize() {
        currentSize++;
        modificationCounter++;
    }

    private void decrementSize() {
        currentSize--;
        modificationCounter++;
    }

    /**
     * Returns an iterator that transverses
     * from head to tail.
     *
     * @return Iterator<E> iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    class IteratorHelper implements Iterator<E> {
        long stateCheck;
        Node<E> element, temp;

        public IteratorHelper() {
            element = head;
            stateCheck = modificationCounter;
        }

        public boolean hasNext() { //fail-fast iterator.
            if (modificationCounter != stateCheck)
                throw new ConcurrentModificationException();
            return element != null;
        }

        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            temp = element;
            if (temp == head) {
                element = temp.next;
                return temp.data;
            }
            element = temp.next;
            return temp.data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}


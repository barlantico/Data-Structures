
/**
 *  Program 3
 *  A priority queue implemented by a binary minimum heap.
 *  CS310
 *  8 April 2019
 *  @author  Brian Arlantico cssc1453
 */

package data_structures;

import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class BinaryHeapPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
    private int currentSize;
    private int maxSize;
    private long modCounter;
    private int entryNumber;
    private Wrapper<E> [] binaryHeap;

     //constructor
    public BinaryHeapPriorityQueue(int size) {
        currentSize = 0;
        maxSize = size;
        modCounter = 0;
        entryNumber = 0;
        binaryHeap = new Wrapper[size];
    }

    //default no-arg constructor
    public BinaryHeapPriorityQueue() {
        this(DEFAULT_MAX_CAPACITY);
    }

     /**
      * Inserts an object into the binaryHeap with minHeap ordering.
      *
      * @param  object     Comparable object E to be inserted into binaryHeap.
      *
      * @return boolean true if insertion was successful but false otherwise.
      */
    @Override
    public boolean insert(E object) {
        if (isFull()) //cannot insert into full container.
            return false;

        currentSize++;
        binaryHeap[currentSize - 1] = new Wrapper<E>(object); //inserts object into last index.
        trickleUp(); //inserts object into proper position.
        modCounter++;
        return true;
    }

    /**
     * Removes the minimum element that has been in the longest from the binaryHeap.
     *
     * @return E object that was the highest priority (min element).
     */
    @Override
    public E remove() {
        E temp;
        if (isEmpty()) //cannot remove from empty container.
            return null;

        temp = binaryHeap[0].data;
        trickleDown(0); //replaces first node w last index and tricklesdown the tree to the correct
                              //position.
        currentSize--;
        modCounter++;
        return temp;
    }

    /**
     * Deletes all instances of the parameter obj from the PQ if found.
     *
     * @param  obj      obj to be removed from binaryHeap.
     *
     * @return boolean true if obj was found and removed, false otherwise.
     */
    @Override
    public boolean delete(E obj) {
        boolean delete = false;
        for (int i = 0; i < currentSize; i++) {
            if (binaryHeap[i] != null && ((Comparable<E>)binaryHeap[i].data).compareTo(obj) == 0) {
                trickleDown(i);

                currentSize--; //new 1 might be higher index???
                modCounter++;
                delete = true;
                i--;
            }
        }
        return delete;
    }

    /**
     * Returns the object of highest priority that has been in the
     * PQ the longest, but does NOT remove it.
     *
     * @return E obj with the highest priority, null if empty.
     */
    @Override
    public E peek() {
        if (!isEmpty())
            return binaryHeap[0].data;
        return null;
    }

    /**
     * Traverses the binaryHeap searching for specified object.
     *
     * @param  obj      Comparable obj to be located within binaryHeap.
     *
     * @return E obj with the highest priority, null if empty.
     */
    @Override
    public boolean contains(E obj) {
         for (int i = 0; i < currentSize; i++) {
             if (binaryHeap[i] != null && ((Comparable<E>)binaryHeap[i].data).compareTo(obj) == 0)
                 return true;
         }
         return false;
    }

    /**
     * Returns size of the binaryHeap.
     *
     * @return int currentSize of binaryHeap.
     */
    @Override
    public int size() {
        return currentSize;
    }

    /**
     * Returns binaryHeap to empty state.
     */
    @Override
    public void clear() {
        currentSize = 0;
        modCounter++;
    }

    /**
     * Returns true if binaryHeap is empty.
     *
     * @return boolean true if currentSize equals 0.
     */
    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * Returns true if binaryHeap is at full capacity.
     *
     * @return boolean true if currentSize equals maxSize.
     */
    @Override
    public boolean isFull() {
        return currentSize == maxSize;
    }


    /**
     * Trickles last element up tree to proper position after insertion.
     */
    private void trickleUp() {
        int newIndex = currentSize - 1;
        int parentIndex = (newIndex-1) >> 1;
        Wrapper<E> newValue = binaryHeap[newIndex];

        while(parentIndex >= 0 && newValue.compareTo(binaryHeap[parentIndex]) < 0) {
            binaryHeap[newIndex] = binaryHeap[parentIndex];
            newIndex = parentIndex;
            parentIndex = (parentIndex - 1) >> 1;
        }

        binaryHeap[newIndex] = newValue;

    }


    /**
     * Replaces index with last element and trickles down the binaryHeap.
     *
     * @param  index      index that is replaced by last element.
     */
    private void trickleDown(int index) {
        int current = index;
        int child = getNextChild(current);
        while (child != -1 && binaryHeap[current].compareTo(binaryHeap[child]) < 0 && binaryHeap[child].compareTo(binaryHeap[currentSize-1]) < 0) {
            binaryHeap[current] = binaryHeap[child];
            current = child;
            child = getNextChild(current);
        }
        binaryHeap[current] = binaryHeap[currentSize - 1];
    }

    /**
     * Returns smallest child index.
     *
     * @param  current      current index of parent.
     *
     * @return int index of smaller child element.
     */
    private int getNextChild(int current) {
        int left = (2 * current) + 1;
        int right = left+1;
        if (right < currentSize) {
            if (binaryHeap[left].compareTo(binaryHeap[right]) < 0)
                return left;
            return right;
        }
        if (left < currentSize)
            return left;
        return -1;

        }

        //creates wrapper objects for the binaryHeap.
    protected class Wrapper<E> implements Comparable<Wrapper<E>> {
        long number;
        E data;

        public Wrapper (E d) {
            number = entryNumber++; //utilized to compare priority between same elements.
            data = d;
        }

        public int compareTo(Wrapper<E> o) {
            if (((Comparable<E>)data).compareTo(o.data) == 0)
                return (int) (number - o.number);
            return ((Comparable<E>)data).compareTo(o.data);
        }

        public String toString() {
            return ""+data;
        }
    }

    public void printArray() { //debugging method.
        for (int i = 0; i < maxSize; i++)
            System.out.print(binaryHeap[i] + " ");
    }

    /**
     * Returns an iterator of the objects within the binaryHeap.
     *
     * @return iterator of sorted minimum binaryHeap.
     */
    @Override
    public Iterator iterator() {
        return new IteratorHelper();
    }

    class IteratorHelper implements Iterator<E> {
        int iterIndex;
        long stateCheck;
        Wrapper<E> [] auxArray;

        public IteratorHelper() {
            iterIndex = 0;
            stateCheck = modCounter;
            auxArray = binaryHeap.clone(); //auxiliary array for sorting min heap.
            insertionSort(auxArray);
        }

        public boolean hasNext() {
            if (stateCheck != modCounter)
                throw new ConcurrentModificationException();
            return iterIndex < currentSize;
        }

        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return auxArray[iterIndex++].data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        //insertion sort within iterator.
        public void insertionSort(Wrapper<E>[] array) {
            Wrapper<E> [] n = array;
            int in, out;
            Wrapper<E> temp;
            for (out = 1; out < currentSize; out++) {
                temp = n[out];
                in = out;
                while (in > 0 && (n[in-1]).compareTo(temp) > 0) {
                    n[in] = n[in-1];
                    in--;
                }
                n[in] = temp;
            }
        }
    }
}

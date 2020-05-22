/* Brian Arlantico
   cssc1453
*/
package data_structures;

import java.util.Iterator;

public class Queue<E extends Comparable<E>> implements Iterable<E>{

    private LinearList<E> queue;
    private int currentSize;

    public Queue() {
        queue = new LinearList<>();
        currentSize = 0;
    }

    /*inserts the object obj into the queue
     */
    public void enqueue(E obj) {
        queue.addLast(obj);
        currentSize++;
    }


    /* removes and returns the object at the front of the queue
     */
    public E dequeue() {
        if (currentSize != 0) {
            currentSize--;
        }
        return queue.removeFirst();
    }

    /* returns the number of objects currently in the queue
     */
    public int size() {
        return currentSize;
    }

    /* returns true if the queue is empty, otherwise false
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /* returns but does not remove the object at the front of the queue
     */
    public E peek() {
        return queue.peekFirst();
    }

    /* returns true if the Object obj is in the queue
     */
    public boolean contains(E obj) {
        return queue.contains(obj);
    }

    /* returns the queue to an empty state
     */
    public void makeEmpty() {
        currentSize = 0;
        queue.clear();
    }

    /* removes the Object obj if it is in the queue and
     * returns true, otherwise returns false.
     */
    public boolean remove(E obj) {
        if (queue.remove(obj) != null) {
            currentSize--;
            return true;
        }
        return false;
    }

    /* returns an iterator of the elements in the queue. The elements
     * must be in the same sequence as dequeue would return them.
     */
    public Iterator<E> iterator() {
        return queue.iterator();
    }
}

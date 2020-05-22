/* Brian Arlantico
   cssc1453
*/

package data_structures;

import java.util.Iterator;

public class Stack<E extends Comparable<E>> implements Iterable<E> {

    private LinearList<E> stack;
    private int currentSize;

    public Stack() {
        stack = new LinearList<>();
        currentSize = 0;
    }
    /* inserts the object obj into the stack
     */
    public void push(E obj) {
        stack.addFirst(obj);
        currentSize++;
    }

    /* pops and returns the element on the top of the stack
     */
    public E pop() {
        if (currentSize != 0) {
            currentSize--;
        }
        return stack.removeFirst();
    }

    /* returns the number of elements currently in the stack
     */
    public int size() {
        return currentSize;
    }

    /* return true if the stack is empty, otherwise false
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /* returns but does not remove the element on the top of the stack
     */
    public E peek() {
        return stack.peekFirst();
    }

    /* returns true if the object obj is in the stack,
     * otherwise false
     */
    public boolean contains(E obj) {
     return stack.contains(obj);
    }

    /* returns the stack to an empty state
     */
    public void makeEmpty() {
        currentSize = 0;
        stack.clear();
    }

    /* removes the Object obj if it is in the stack and
     * returns true, otherwise returns false.
     */
    public boolean remove(E obj) {
            if (stack.remove(obj) != null) {
                currentSize--;
                return true;
            }
            return false;
    }

    /* returns a iterator of the elements in the stack. The elements
     * must be in the same sequence as pop() would return them.
     */
    public Iterator<E> iterator() {
        return stack.iterator();
    }
}

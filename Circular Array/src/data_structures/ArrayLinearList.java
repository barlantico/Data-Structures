/**
 *  Program 1
 *  A circular data structure implementing LinearListADT
 *  and utilizing front and rear indices in order to be as efficient as possible.
 *  CS310
 *  2-18-19
 *  @author  Brian Arlantico cssc1453
 */

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class ArrayLinearList<E extends Comparable<E>> implements LinearListADT<E> {
    private int front, rear, currentSize, maxSize, modifierCounter;
    private E[] list;

    @SuppressWarnings("unchecked")
    public ArrayLinearList(int maxSize) {
        list = (E[]) new Comparable[maxSize];
        this.maxSize = maxSize;
        currentSize = 0;
        modifierCounter = 0;
    }

    @SuppressWarnings("unchecked")
    public ArrayLinearList() { //Default constructor.
        this(DEFAULT_MAX_CAPACITY);
    }

    @Override
    /**
     *  Prints indices of front and rear.
     */
    public void ends() {
        System.out.println("Front: " + front + " Rear: " + rear);

    }

    @Override
    /**
     *  Adds element to the front of the array.
     *
     *  @param obj to be added to array as front.
     *
     *  @returns boolean true if element add was successful.
     *           false if array is full.
     */
    public boolean addFirst(E obj) {

        //aborts if array is full.
        if (currentSize == maxSize)
            return false;

        //adds element to middle of empty list.
        if (currentSize == 0) {
            list[(maxSize - 1) / 2] = obj;
            front = (maxSize - 1) / 2;
            rear = (maxSize - 1) / 2;
            incrementSize();
            return true;


        } else if (front <= rear) {

            //makes sure array stays in bounds.
            if (front - 1 >= 0) {
                list[front - 1] = obj;
                front--;
                incrementSize();
                return true;
            }

            //wraps front of array to the end.
            if (front == 0) {
                list[maxSize - 1] = obj;
                front = maxSize - 1;
                incrementSize();
                return true;
            }

        } else {

            list[front - 1] = obj;
            front--;
            incrementSize();
            return true;

        }
        return false;

    }


    @Override
    /**
     *  Adds element to the end of the array.
     *
     *  @param obj to be added to the array as the rear.
     *
     *  @returns boolean true if add was successful
     *           false if array is full.
     */
    public boolean addLast(E obj) {

        //aborts if array is full.
        if (currentSize == maxSize)
            return false;

        if (currentSize == 0) { //Just as in addFirst, empty list houses
            list[(maxSize - 1) / 2] = obj; //first element in middle.
            front = (maxSize - 1) / 2;
            rear = (maxSize - 1) / 2;
            incrementSize();
            return true;

        } else {
            //wraps rear around array.
            if ((rear + 1) == maxSize) {
                list[0] = obj;
                rear = 0;
                incrementSize();
                return true;
            }
            //If no wrapping needed, just puts rear one index after previous rear.
            list[rear + 1] = obj;
            rear++;
            incrementSize();
            return true;
        }
    }

    @Override
    /**
     *  Removes the first element and returns it.
     *
     *  @returns E original first element that was removed.
     */
    public E removeFirst() {
        if (currentSize == 0)
            return null;

        E temp = list[front];

        //clears list if only one element.
        if (front == rear) {
            clear();
            return temp;

        } else {

            //wraps front to list[0].
            if (front == maxSize - 1) {
                list[front] = null;
                front = 0;
                decrementSize();
                return temp;
            }

            //moves front one index forward after removal.
            temp = list[front];
            list[front] = null;
            front++;
            decrementSize();
            return temp;

        }
    }

    @Override
    /**
     *  Removes the last element of array and returns it.
     *
     *  @returns E original last element that was removed.
     */
    public E removeLast() {

        if (currentSize == 0)
            return null;

        E temp = list[rear];

        if (front == rear) {
            clear();
            return temp;

        } else if (rear > front) {
            list[rear] = null;
            rear--;
            decrementSize();
            return temp;

        } else {

            //wraps rear from list[0] to list[maxSize - 1].
            if (rear - 1 < 0) {
                list[rear] = null;
                rear = maxSize - 1;
                currentSize--;
                modifierCounter++;
                return temp;
            }

            //Otherwise, rear moved back one index.
            list[rear] = null;
            rear--;
            decrementSize();
            return temp;
        }
    }

    @Override
    /**
     *  Returns removed element and shifts array if needed.
     *
     *  @param obj to be searched for and removed.
     *
     *  @returns E obj that was chosen to be removed. Null otherwise.
     */
    public E remove(E obj) {
        E temp;
        E element;
        int index = -1; //-1 means object could not be found.

        if (rear > front) {
            for (int i = front; i <= rear; i++) {
                if (list[i].equals(obj)) {
                    index = i;
                    break;
                }
            }

            //all elements have been searched and obj not found.
            if (index == -1)
                return null;

            element = list[index];

            //checks if its more efficient to shift front right.
            if (index - front < rear - index) {

                //Shifts front to index one index right.
                for (int j = index; j > front; j--) {
                    list[j] = list[j - 1];
                }

                list[front] = null;
                front++;
                decrementSize();
                return element;

            } else {

                //Shifts elements left from index to rear.
                for (int j = index; j < rear; j++) {
                    list[j] = list[j + 1];
                }

                list[rear] = null;
                rear--;
                decrementSize();
                return element;
            }
        }

        if (front > rear) {

            for (int i = front; i < maxSize; i++) {
                if (list[i].equals(obj)) {
                    index = i;
                    break;
                }
            }

            //If obj could not be found, wraps array
            //and searches until rear.
            if (index == -1) {
                for (int i = 0; i <= rear; i++) {
                    if (list[i].equals(obj)) {
                        index = i;
                        break;
                    }
                }
            }

            //Could not be found.
                if (index == -1)
                    return null;

                element = list[index];

                //calls removeLast function if
                //index is rear.
                if (index == rear) {
                    removeLast();
                    return element;

                }

                //Shifts elements left from rear to index.
                if (index - rear < front - index) {

                    //temp value since rear gets overwritten.
                    temp = list[rear];

                    for (int j = index; j < rear - 1; j++) {
                        list[j] = list[j + 1];
                    }

                    //checks if its in bounds still.
                    if (rear - 1 >= 0)
                        list[rear-1] = temp;

                    list[rear] = null;
                    decrementSize();

                    //wraps rear.
                    if (rear == 0) {
                        rear = maxSize - 1;
                        list[rear] = temp;
                        return element;
                    }
                    rear--;
                    return element;
                }

                if (front - index < index - rear) {

                    //Calls removeFirst if index is front.
                    if (index == front) {
                        removeFirst();
                        return element;
                    }

                    //Shifts elements from front to index.
                    for (int j = index - 1; j >= front; j--) {

                        list[j + 1] = list[j];
                    }
                    //moves front forward one index.
                    list[front] = null;
                    front++;
                    decrementSize();
                    return element;
                }
            }

            //If only one element check if its
            //equal to obj.
            if (front == rear) {
                if (list[front] == obj) {
                    clear();
                    return obj;
                }
                return null;
            }
            //default returns null if not located.
            return null;
        }

    @Override
    /**
     *  returns first element if array is not empty.
     *
     *  @returns E first element of array.
     */
    public E peekFirst() {
        if (currentSize == 0)
            return null;
        return list[front];
    }

    @Override
    /**
     *  returns last element if array is not empty.
     *
     *  @returns E last element of array.
     */
    public E peekLast() {
        if (currentSize == 0)
            return null;
        return list[rear];
    }

    @Override
    /**
     *  Checks if a specific element is within the array.
     *
     *  @param obj to be searched for in array.
     *
     *  @returns boolean true if obj was found. False otherwise.
     */
    public boolean contains(E obj) {
        if (rear > front) {
            for (int i = front; i <= rear; i++) {
                if (list[i].equals(obj))
                    return true;
            }
        }

        if (front > rear) {

            for (int i = front; i < maxSize; i++) {
                if (list[i].equals(obj))
                    return true;
            }
            //wraps array to search from index 0 to rear.
            for (int i = 0; i <= rear; i++) {
                if (list[i].equals(obj))
                    return true;
            }

        }
        //default case, if item is not found.
        return false;
    }

    @Override
    /**
     *  Calls contains but returns element E if found instead of
     *  a boolean.
     *
     *  @param obj to be searched for in array.
     *
     *  @returns E element if it was found in the array. Null otherwise.
     */
    public E find(E obj) {
        if (contains(obj))
            return obj;
        return null;
    }

    @Override
    /**
     *  resets the data field values and points list to a new
     *  initialized array
     */
    public void clear() {
        currentSize = 0;
        front = 0;
        rear = 0;
        modifierCounter++;
        list = (E[]) new Comparable[maxSize];
    }

    @Override
    /**
     *  @returns boolean true if array is empty. True otherwise.
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    /**
     *  @returns boolean true if elements occupy every index of the
     *  array.
     */
    public boolean isFull() {
        return currentSize == maxSize;
    }

    @Override
    /**
     *  @returns int representing amount of elements in array.
     */
    public int size() {
        return this.currentSize;
    }

    /**
     *  increments size and modifierCounter of add methods.
     */
    public void incrementSize() {
        currentSize++;
        modifierCounter++;
    }

    /**
     *  decrements size and increments modifierCounter of remove methods.
     */
    public void decrementSize() {
        currentSize--;
        modifierCounter++;
    }

    @Override
    /**
     *  Transverses the array from front to rear index.
     *  @returns Iterator<E>
     */
    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    private class IteratorHelper implements Iterator<E> {
        private int iterIndex, counter, stateCheck;
        private E temp;

        public IteratorHelper() {

            iterIndex = front; //index of first element.
            counter = 0; //counts how many elements have beed accounted for.
            stateCheck = modifierCounter; //ensures changes do not occur when iterating.

        }

        public boolean hasNext() {
            if (stateCheck != modifierCounter) //if a change has been made throw error.
                throw new ConcurrentModificationException();

            return counter < currentSize; //continues until every element has been accounted for.
        }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            temp = list[iterIndex];
            if (rear > front) {
                if (iterIndex < rear)
                    iterIndex++;
                    }

            else if (front > rear) {
                //if iterIndex is between front and max.
                if (iterIndex < maxSize - 1 && iterIndex >= front) {
                    iterIndex++;
                }
                //iterator wraps array.
                else if (iterIndex == maxSize - 1) {
                    iterIndex = 0;
                }

                else { //increments normally if its not a special case.
                    iterIndex++;
                }

            } else {
                    //rear > front, so increment normally.
                    iterIndex++;
                }

            counter++;
            return temp;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}

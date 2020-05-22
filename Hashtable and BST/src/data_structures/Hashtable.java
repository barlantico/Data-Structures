/*
   Brian Arlantico
   cssc1453
*/

package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Hashtable<K extends Comparable<K>, V> implements DictionaryADT<K,V> {

    private LinearList<DictionaryNode<K,V>> [] list;
    private int currentSize, maxSize, tableSize;
    private long modificationCounter;

    private class DictionaryNode<K,V> implements Comparable<DictionaryNode<K,V>>{
        K key;
        V value;

        public DictionaryNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(DictionaryNode<K, V> node) {
            return ((Comparable<K>)key).compareTo((K)node.key);
        }
    }

    public Hashtable(int n) {
        currentSize = 0;
        maxSize = n;
        modificationCounter = 0;
        tableSize = (int) (maxSize * 1.3f);
        list = new LinearList[tableSize];
        for (int i = 0; i < tableSize; i++)
            list[i] = new LinearList<DictionaryNode<K,V>>();
    }

    private int getIndex(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % tableSize;
    }

    @Override
    public boolean contains(K key) {
        if (isEmpty())
            return false;

        if (list[getIndex(key)].contains(new DictionaryNode<>(key, null)))
            return true;

        return false;
    }

    @Override
    public boolean add(K key, V value) {
        if (isFull())
            return false;

        if(list[getIndex(key)].contains(new DictionaryNode<K,V>(key, null)))
            return false;

        list[getIndex(key)].addLast(new DictionaryNode<K,V>(key, value));
        currentSize++;
        modificationCounter++;
        return true;
    }

    @Override
    public boolean delete(K key) {
        if (!contains(key))
            return false;

        list[getIndex(key)].remove(new DictionaryNode<K,V>(key, null));
            currentSize--;
            modificationCounter++;
            return true;
        }



    @Override
    public V getValue(K key) {
        DictionaryNode<K,V> node;
        if (isEmpty())
            return null;

        node = list[getIndex(key)].find(new DictionaryNode<K,V>(key, null));
        if (node != null)
        return (V)node.value;

        return null;
    }

    @Override
    public K getKey(V value) {
        DictionaryNode<K, V> node;
        if (isEmpty())
            return null;
        for (int i = 0; i < tableSize; i++) {
            Iterator<DictionaryNode<K, V>> values = list[i].iterator();

            while (values.hasNext()) {
                node = values.next();
                if (((Comparable<V>) node.value).compareTo((V)value) == 0)
                    return (K) node.key;
            }

        }
        return null;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isFull() {
        return currentSize == maxSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < maxSize; i++)
            list[i].clear();
        currentSize = 0;
        modificationCounter++;
    }

    @Override
    public Iterator<K> keys() {
        return new IteratorHelperKeys();

    }

    @Override
    public Iterator<V> values() {
        return new IteratorHelperValues();
    }

    abstract class IteratorHelper<N> implements Iterator<N> {
        DictionaryNode<K, V>[] n;
        DictionaryNode<K, V>[] aux;
        DictionaryNode<K, V>[] dictionaryNodes;
        protected int index, newIndex;
        protected long stateCheck;

        IteratorHelper() {
            dictionaryNodes = new DictionaryNode[currentSize];
            index = 0;
            newIndex = 0;
            stateCheck = modificationCounter;
            for (int i = 0; i < tableSize; i++) {
                Iterator<DictionaryNode<K, V>> nodeIterator = list[i].iterator();
                while (nodeIterator.hasNext())
                    dictionaryNodes[newIndex++] = nodeIterator.next();
            }
            dictionaryNodes = shellSort(dictionaryNodes);

        }

        @Override
        public boolean hasNext() {
            if (stateCheck != modificationCounter)
                throw new ConcurrentModificationException();
            return index < currentSize;
        }

        @Override
        public abstract N next();


        private DictionaryNode<K, V>[] shellSort(DictionaryNode[] array) {
            DictionaryNode<K, V>[] n = array;
            int in, out, h = 1;
            DictionaryNode<K, V> temp;
            int size = n.length;
            while (h <= size / 3)
                h = h*3+1;
            while (h > 0) {
                for (out = h; out < size; out++) {
                    temp = n[out];
                    in = out;
                    while (in > h - 1 && n[in - h].compareTo(temp) >= 0) {
                        n[in] = n[in - h];
                        in -= h;
                    }
                    n[in] = temp;
                }
                h = (h-1)/3;
            }
            return n;
        }
    }

    class IteratorHelperKeys extends IteratorHelper {
        IteratorHelperKeys() {
            super();
        }

        public K next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return (K)dictionaryNodes[index++].key;
        }
    }

    class IteratorHelperValues extends IteratorHelper {
        IteratorHelperValues() {
            super();
        }

        public V next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return (V)dictionaryNodes[index++].value;
        }
    }


    //LINKED LIST CLASS FOR CHAINING
     class LinearList<E extends Comparable<E>> {

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
        public boolean contains(E obj) {
            return find(obj) != null;
        }

        /**
         * Calls contains method but instead
         * returns obj if found in list, no removal.
         *
         * @return E data of found obj.
         */
        public E find(E obj) {
            Node<E> tmp = head;
            while (tmp != null) {
                if (((Comparable<E>)obj).compareTo(tmp.data) == 0)
                    return tmp.data;
                tmp = tmp.next;
            }
            return null;
        }

        /**
         * Sets head and tail to null and currentSize
         * to 0.
         */
        public void clear() {
            tail = head = null;
            currentSize = 0;
        }

        /**
         * Compares head to null to determine if empty.
         *
         * @return True if list is empty. False otherwise.
         */
        public boolean isEmpty() {
            return head == null;
        }

        /**
         * Returns false as linkedlists have no
         * set size.
         *
         * @return boolean False.
         */
        public boolean isFull() {
            return false;
        }

        /**
         * Returns size of list.
         *
         * @return int currentSize.
         */
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


}

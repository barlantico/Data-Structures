/*
   Brian Arlantico
   cssc1453
*/

package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<K extends Comparable<K>, V> implements DictionaryADT<K, V> {

    private DictionaryNode root;
    private int currentSize;
    private long modificationCounter;
    private K foundKey;

    private class DictionaryNode<K,V> implements Comparable<DictionaryNode<K,V>>{
        K key;
        V value;
        DictionaryNode leftChild;
        DictionaryNode rightChild;

        public DictionaryNode(K key, V value) {
            this.key = key;
            this.value = value;
            leftChild = rightChild = null;
        }

        @Override
        public int compareTo(DictionaryNode<K, V> node) {
            return ((Comparable<K>)key).compareTo((K)node.key);
        }
    }

    public BinarySearchTree() {
        root = null;
        modificationCounter = 0;
        currentSize = 0;
    }
    @Override
    public boolean contains(K key) {
        return find(key, root) != null;
    }

    @Override
    public boolean add(K key, V value) {
        if (root == null)
            root = new DictionaryNode<K,V>(key,value);
        else if (contains(key))
            return false;
        else
            insert(key,value, root,null,false);

        currentSize++;
        modificationCounter++;
        return true;
    }

    private void insert(K key, V value, DictionaryNode<K,V> n, DictionaryNode parent, boolean wasLeft) {
        if (n == null) {
            if (wasLeft) parent.leftChild = new DictionaryNode<K,V>(key,value);
            else parent.rightChild = new DictionaryNode<K,V>(key,value);
        }
        else if (((Comparable<K>)key).compareTo((K)n.key) < 0)
            insert(key,value,n.leftChild,n,true);
        else
            insert(key,value,n.rightChild,n,false);
    }

    @Override
    public boolean delete(K key) {
        if (isEmpty())
            return false;
        if (contains(key)) {
            remove(root,key);
            modificationCounter++;
            currentSize--;
            return true;
        }
        return false;
    }

    private DictionaryNode<K,V> remove(DictionaryNode<K,V> root, K key) {
        DictionaryNode<K,V> current = root;
        if(current == null){
            return current;
        }
        if(((Comparable<K>)current.key).compareTo(key) > 0){
            current.leftChild = remove(current.leftChild, key);
        }else if(((Comparable<K>)current.key).compareTo(key) < 0){
            current.rightChild = remove(current.rightChild, key);
        }else{
            if(current.leftChild == null && current.rightChild == null){
                current = null;
            }else if(current.rightChild == null){
                current = current.leftChild;
            }else if(current.leftChild == null){
                current = current.rightChild;
            }else{
                DictionaryNode<K,V> temp  = getPredecessor(current.rightChild);
                current.key = temp.key;
                current.value = temp.value;
                current.rightChild = remove(current.rightChild, temp.key);
            }
        }
        return current;
    }


    private DictionaryNode<K,V> getPredecessor(DictionaryNode<K,V> root) {
        DictionaryNode<K,V> predecessor = root;
        while (predecessor.leftChild != null) {
            predecessor = predecessor.leftChild;
        }

        return predecessor;
    }

    @Override
    public V getValue(K key) {
        if (isEmpty())
            return null;

        return (V)find(key, root);
    }

    private V find(K key, DictionaryNode<K,V> n) {
        if (n == null) return null;

        if (((Comparable<K>)key).compareTo(n.key) < 0)
            return (V)find(key, n.leftChild);

        if (((Comparable<K>)key).compareTo(n.key) > 0)
            return (V)find(key,n.rightChild);

        return (V) n.value;
    }

    @Override
    public K getKey(V value) {
        if (isEmpty())
            return null;
        else
            foundKey = null;
            getKey(root,value);
            return foundKey;

    }

    private void getKey(DictionaryNode<K,V> n, V value) {
        if (n != null) {
            getKey(n.leftChild, value);
            if (((Comparable<V>)value).compareTo((V)n.value) == 0)
                foundKey = n.key;
            getKey(n.rightChild,value);

        }
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public void clear() {
        root = null;
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
        DictionaryNode<K,V>[] bstArray;
        private long stateCheck;
        private int arrayIndex;
        protected int index;

        IteratorHelper() {
            bstArray = new DictionaryNode[currentSize];
            stateCheck = modificationCounter;
            index = arrayIndex = 0;
            createArray(root);
        }

        @Override
        public boolean hasNext() {
            if (stateCheck != modificationCounter)
                throw new ConcurrentModificationException();
            return index < currentSize;
        }

        @Override
        public abstract N next();

        private void createArray(DictionaryNode<K,V> n) {
            if (n != null) {
                createArray(n.leftChild);
                bstArray[arrayIndex++] = n;
                createArray(n.rightChild);
            }
        }
    }

    private class IteratorHelperKeys extends IteratorHelper {

        IteratorHelperKeys() {
            super();
        }

        @Override
        public K next() {
            if(!hasNext()) throw new NoSuchElementException();

            return (K)bstArray[index++].key;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class IteratorHelperValues extends IteratorHelper {

        IteratorHelperValues() {
            super();
        }

        @Override
        public V next() {
            if(!hasNext()) throw new NoSuchElementException();

            return (V)bstArray[index++].value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


}

/*
   Brian Arlantico
   cssc1453
*/

package data_structures;

import java.util.Iterator;
import java.util.TreeMap;

public class BalancedTree<K extends Comparable<K>, V> implements DictionaryADT<K, V> {

    private TreeMap<K,V> tree;


    public BalancedTree() {
        tree = new TreeMap<>();
    }

    @Override
    public boolean contains(K key) {
        return tree.containsKey(key);
    }

    @Override
    public boolean add(K key, V value) {
        if (tree.containsKey(key))
            return false;

        tree.put(key,value);
        return true;
    }

    @Override
    public boolean delete(K key) {
        if (isEmpty())
            return false;

        return tree.remove(key) != null;
    }

    @Override
    public V getValue(K key) {
        if (isEmpty() || !tree.containsKey(key))
            return null;

        return (V)tree.get(key);

    }

    @Override
    public K getKey(V value) {
        if (isEmpty())
            return null;

        for (K key : tree.keySet()) {
            if(((Comparable<V>)value).compareTo((V)tree.get(key)) == 0)
                return key;
        }
        return null;
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public Iterator<K> keys() {
        return tree.keySet().iterator();
    }

    @Override
    public Iterator<V> values() {
        return tree.values().iterator();
    }
}

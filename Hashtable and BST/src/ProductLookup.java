/*
   Brian Arlantico
   cssc1453
*/
import data_structures.*;
import java.util.Iterator;

public class ProductLookup {
    Hashtable<String,StockItem> table;

    // Constructor. There is no argument-less constructor, or default size
    public ProductLookup(int maxSize) {
        table = new Hashtable<>(maxSize);

    }

    // Adds a new StockItem to the dictionary
    public void addItem(String SKU, StockItem item) {
        table.add(SKU, item);
    }

    // Returns the StockItem associated with the given SKU, if it is
    // in the ProductLookup, null if it is not.
    public StockItem getItem(String SKU) {
            return table.getValue(SKU);
    }

    // Returns the retail price associated with the given SKU value.
    // -.01 if the item is not in the dictionary
    public float getRetail(String SKU) {
        if (table.getValue(SKU)==null)
            return (float) -.01;
        return table.getValue(SKU).getRetail();
    }

    // Returns the cost price associated with the given SKU value.
    // -.01 if the item is not in the dictionary
    public float getCost(String SKU) {
        if (table.getValue(SKU)==null)
            return (float) -.01;
        return table.getValue(SKU).getCost();
    }

    // Returns the description of the item, null if not in the dictionary.
    public String getDescription(String SKU) {
        if (table.getValue(SKU)==null)
            return null;
        return table.getValue(SKU).getDescription();
    }

    // Deletes the StockItem associated with the SKU if it is
    // in the ProductLookup. Returns true if it was found and
    // deleted, otherwise false.
    public boolean deleteItem(String SKU) {
        return table.delete(SKU);
    }

    // Prints a directory of all StockItems with their associated
    // price, in sorted order (ordered by SKU).
    public void printAll() {
        Iterator items = table.values();
        int index = 0;
        StockItem[] array = new StockItem[table.size()];
        while (items.hasNext())
            array[index++] = (StockItem) items.next();

        insertionSort(array,table.size());
        for (StockItem i : array)
            System.out.println("SKU: " + i.SKU + " Price: " + i.getCost() );
    }

    // Prints a directory of all StockItems from the given vendor,
    // in sorted order (ordered by SKU).
    public void print(String vendor) {
        StockItem tmp; //aux array and sort then print
        StockItem[] auxArray = new StockItem[table.size()];
        int index = 0;
        Iterator items = table.values();
        while (items.hasNext()) {
            tmp = (StockItem) items.next();
            if (tmp.getVendor().equals((String)vendor))
            auxArray[index++] = tmp;
        }

        insertionSort(auxArray,index);
        for (int i = 0; i < index; i++) {
            System.out.println(auxArray[i]);
        }
    }

    // An iterator of the SKU keys.
    public Iterator<String> keys() {
        return table.keys();
    }

    // An iterator of the StockItem values.
    public Iterator<StockItem> values() {
        return table.values();
    }

    private void insertionSort(StockItem[] array, int size) {
        StockItem [] n = array;
        int in, out;
        StockItem temp;
        for (out = 1; out < size; out++) {
            temp = n[out];
            in = out;
            while (in > 0 && (n[in-1].SKU).compareTo(temp.SKU) > 0) {
                n[in] = n[in-1];
                in--;
            }
            n[in] = temp;
        }
    }
}
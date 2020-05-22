import data_structures.*;
import java.util.Iterator;

public class Driver1
{
    private DictionaryADT<Integer,Integer> RBT;
    private DictionaryADT<Integer,Integer> BST;
    private DictionaryADT<Integer,Integer> HT;
    ProductLookup items = new ProductLookup(10);

    public Driver1()
    {
        RBT = new BalancedTree<>();
        BST = new BalancedTree<>();
        HT = new Hashtable<>(4);
        runTests();
    }

    public void runTests()
    {
        System.out.println("///TESTING BINARY SEARCH TREE///\n");
        BST.add(1,1);
        System.out.println("BST Size: " + BST.size());
        System.out.println("BST Value: " + BST.getValue(1));
        BST.add(1,10);
        System.out.println("BST Size: " + BST.size());
        System.out.println("BST Value: " + BST.getValue(1));
        BST.add(2,2);
        System.out.println("BST Size: " + BST.size());
        System.out.println("BST Value: " + BST.getValue(2));
        System.out.println("\nDELETING KEYS 2 & 1");
        System.out.println("2 SUCCESSFUL: " + BST.delete(2));
        System.out.println("BST Size: " + BST.size());
        System.out.println("BST Value: " + BST.getValue(2));
        System.out.println("1 SUCCESSFUL: " + BST.delete(1));
        System.out.println("BST Size: " + BST.size());
        System.out.println("BST Value: " + BST.getValue(1));

        System.out.println("\nRESET");
        BST.clear();
        BST.add(5,5);
        BST.add(3,3);
        BST.add(6,6);
        BST.add(1,1);
        BST.add(4,4);
        BST.add(7,7);
        BST.add(2,2);
        BST.add(9,2);
        System.out.println("BST Size: " + BST.size());
        System.out.println("\nGET TESTS\n");
        System.out.println("KEY 2 VALUE: " + BST.getValue(2));
        System.out.println("VALUE 2 KEY: " + BST.getKey(2));

        System.out.println("\nDELETING ALL KEYS\n");
        System.out.println("2 SUCCESSFUL: " + BST.delete(2));
        System.out.println("BST Size: " + BST.size());
        System.out.println("6 SUCCESSFUL: " + BST.delete(6));
        System.out.println("BST Size: " + BST.size());
        System.out.println("3 SUCCESSFUL: " + BST.delete(3));
        System.out.println("BST Size: " + BST.size());
        System.out.println("5 SUCCESSFUL: " + BST.delete(5));
        System.out.println("BST Size: " + BST.size());
        System.out.println("7 SUCCESSFUL: " + BST.delete(7));
        System.out.println("BST Size: " + BST.size());
        System.out.println("4 SUCCESSFUL: " + BST.delete(4));
        System.out.println("BST Size: " + BST.size());
        System.out.println("4 SUCCESSFUL: " + BST.delete(4));
        System.out.println("BST Size: " + BST.size());
        System.out.println("1 SUCCESSFUL: " + BST.delete(1));
        System.out.println("BST Size: " + BST.size());
        System.out.println("9 SUCCESSFUL: " + BST.delete(9));
        System.out.println("BST Size: " + BST.size());;

        System.out.println("\n///TESTING HASHTABLE///\n");
        HT.add(1,1);
        System.out.println("DOES HT CONTAIN 1: " + HT.contains(1));
        HT.clear();

        for (int i = 0; i < 100; i++)
        {
            HT.add(i, i+1);
            System.out.println("Added key: " + i + " with value: " + (i+1));
        }

        System.out.println("\n////TESTING HT DELETE///\n");
        System.out.println("1 SUCCESSFUL: " + HT.delete(1));
        System.out.println("11 SUCCESSFUL: " + HT.delete(11));
        System.out.println("100 SUCCESSFUL: " + HT.delete(100));

        System.out.println("\n///TESTING HT GETTERS///\n");
        System.out.println("VALUE(3) KEY: " + HT.getKey(3));
        System.out.println("KEY(2) VALUE: " + HT.getValue(2));
        System.out.println("KEY(1) VALUE: " + HT.getValue(1));

        items.addItem("101",new StockItem("101","cool","sony",(float)0.1, (float)10));
        System.out.println(items.getItem("101").getVendor());

        HT.clear();
        System.out.println(HT.size());
        HT.add(20,200);
        HT.add(15,150);
        HT.add(42,420);

        Iterator hashIterator = HT.keys();
        while(hashIterator.hasNext())
            System.out.println(hashIterator.next());

    }

    public static void main(String[] args)
    {
        new Driver1();
    }
}
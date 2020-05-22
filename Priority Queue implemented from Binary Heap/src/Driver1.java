/**
 * Program 3 Driver for CS-310
 * @author John Robinson
 */
import data_structures.*;
import java.util.ArrayList;

public class Driver1
{
    private PriorityQueue<Integer> list;

    private Driver1()
    {
        list = new BinaryHeapPriorityQueue<Integer>(1000000);
        runTests();
    }


    private void runTests()
    {
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            list.insert(i);
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;


         startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            list.remove();
        }

         endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("BH remove (n = 10000) : " + duration);

        for (int i = 0; i < 100000; i++) {
            list.insert(i);
        }

         startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            list.remove();
        }

         endTime = System.nanoTime();
         duration = endTime - startTime;

        System.out.println("BH remove (n = 100000) : " + duration);

        for (int i = 0; i < 500000; i++) {
            list.insert(i);
        }

         startTime = System.nanoTime();
        for (int i = 0; i < 500000; i++) {
            list.remove();
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("BH remove (n = 500000) : " + duration);

        for (int i = 0; i < 1000000; i++) {
            list.insert(i);
        }

        startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            list.remove();
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("BH remove (n = 1000000) : " + duration);

    }

    public static void main(String[] args)
    {
        new Driver1();
    }
}
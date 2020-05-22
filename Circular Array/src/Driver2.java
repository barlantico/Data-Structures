import data_structures.*;
import java.util.ArrayList;
import java.util.LinkedList;
public class Driver2 {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;



        startTime = System.nanoTime();
        for (int i = 9999; i >=0; i--) {
            list.remove(list.size()-1);
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("AL removeLast (n = 10000) : " + duration);

        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        startTime = System.nanoTime();
        for (int i = 9999; i >=0; i--) {
            list.remove(0);
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("AL removeFirst (n = 10000) : " + duration);

        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }

        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            list.remove(list.size() - 1);
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("AL removeLast (n = 100000) : " + duration);

        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }


        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            list.remove(0);
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("AL removeFirst (n = 100000) : " + duration);

        for (int i = 0; i < 500000; i++) {
            list.add(i);
        }


        startTime = System.nanoTime();
        for (int i = 0; i < 500000; i++) {
            list.remove(list.size()-1);
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("AL removeLast (n = 500000) : " + duration);

        for (int i = 0; i < 500000; i++) {
            list.add(i);
        }

        startTime = System.nanoTime();
        for (int i = 0; i < 500000; i++) {
            list.remove(0);
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("AL removeFirst (n = 500000) : " + duration);

        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }

        startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            list.remove(list.size()-1);
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("ALL removeLast (n = 1000000) : " + duration);

        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }

        startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            list.remove(0);
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("AL removeFirst (n = 1000000) : " + duration);



    }
}

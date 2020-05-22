import data_structures.*;
import java.util.ArrayList;
import java.util.LinkedList;
public class Driver2 {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            list.addFirst(i);
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;



        startTime = System.nanoTime();
        for (int i = 9999; i >=0; i--) {
            list.removeLast();
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("LL removeLast (n = 10000) : " + duration);

        for (int i = 0; i < 10000; i++) {
            list.addFirst(i);
        }

        startTime = System.nanoTime();
        for (int i = 9999; i >=0; i--) {
            list.removeFirst();
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("LL removeFirst (n = 10000) : " + duration);

        for (int i = 0; i < 100000; i++) {
            list.addFirst(i);
        }

        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            list.removeLast();
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("LL removeLast (n = 100000) : " + duration);

        for (int i = 0; i < 100000; i++) {
            list.addFirst(i);
        }


        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            list.removeFirst();
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("LL removeFirst (n = 100000) : " + duration);

        for (int i = 0; i < 500000; i++) {
            list.addFirst(i);
        }


        startTime = System.nanoTime();
        for (int i = 0; i < 500000; i++) {
            list.removeLast();
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("LL removeLast (n = 500000) : " + duration);

        for (int i = 0; i < 500000; i++) {
            list.addFirst(i);
        }

        startTime = System.nanoTime();
        for (int i = 0; i < 500000; i++) {
            list.removeFirst();
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("LL removeFirst (n = 500000) : " + duration);

        for (int i = 0; i < 1000000; i++) {
            list.addFirst(i);
        }

        startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            list.removeLast();
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("LL removeLast (n = 1000000) : " + duration);

        for (int i = 0; i < 1000000; i++) {
            list.addFirst(i);
        }

        startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            list.removeFirst();
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("LL removeFirst (n = 1000000) : " + duration);



    }
}

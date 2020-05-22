import data_structures.*;

public class Driver4 {
    private LinearListADT<Integer> list;

    public Driver4() {
        list = new ArrayLinearList(8);
        runTests();
    }

    private void runTests() {

        list.addFirst(6);
        list.addFirst(7);
        list.addFirst(8);
        list.addFirst(5);
        list.addFirst(4);
        list.addFirst(3);
        list.addLast(10);

        list.ends();

        for (Integer i : list)
            System.out.print(i + " ");

        list.remove(5);
        list.ends();

        for (Integer i : list)
            System.out.print(i + " ");

        list.remove(4);
        System.out.println("");
        list.ends();

        for (Integer i : list)
            System.out.print(i + " ");

        System.out.println(list.peekLast());
        System.out.println(list.peekFirst());

        System.out.println(list.size());
        System.out.println(list.contains(6));
        System.out.println(list.find(6));
        System.out.println(list.contains(5));
        System.out.println(list.find(5));

        System.out.println(list.isEmpty());
        System.out.println(list.isFull());
        list.clear();

        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println(list.isFull());
        }




    public static void main(String[] args) {
        new Driver4();
    }
}

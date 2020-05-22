import data_structures.*;

public class Driver {
    private LinearListADT<Object> list;

    public Driver() {
        list = new ArrayLinearList(100);
        runTests();
    }

    private void runTests() {
        for (int i = 1; i <= 100; i++)
            list.addFirst(Integer.toString(i));

        System.out.println(list.isFull());
        list.ends();
        for (Object i : list)
            System.out.print(i + " ");
        list.ends();

        System.out.println("");
        System.out.println(list.remove("69"));
        System.out.println(list.contains("69"));

        for (Object i : list)
            System.out.print(i + " ");
        list.ends();

        System.out.println(list.remove("39"));
        System.out.println(list.contains("39"));

        System.out.println(list.remove("100"));
        System.out.println(list.contains("100"));

        System.out.println(list.remove("1"));
        System.out.println(list.contains("1"));

        list.ends();

        for (Object i : list)
            System.out.print(i+" ");


        System.out.println(list.isFull());


        list.remove("22");
        System.out.println(list.contains("22"));

        for (Object i : list)
            System.out.print(i + " ");

        System.out.println("");
        System.out.println(list.remove("22"));
        System.out.println(list.contains("22"));

        System.out.println(list.remove("77"));

        for (Object i : list)
            System.out.print(i + " ");
        list.ends();


        list.addFirst(2.52);
        list.addLast("John");

        list.ends();

        System.out.println(list.peekFirst());

        System.out.println(list.peekLast());


        for (Object i : list)
            System.out.print(i + " ");

        System.out.println(list.contains(2.52));
    }




    public static void main(String[] args) {
        new Driver();
    }
}

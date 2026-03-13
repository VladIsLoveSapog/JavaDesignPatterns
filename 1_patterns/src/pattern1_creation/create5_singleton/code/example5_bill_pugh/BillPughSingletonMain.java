package pattern1_creation.create5_singleton.code.example5_bill_pugh;

public class BillPughSingletonMain {
    public static void main(String[] args) {
        System.out.println("=== Bill Pugh Singleton ===\n");

        BillPughSingleton s1 = BillPughSingleton.getInstance();
        BillPughSingleton s2 = BillPughSingleton.getInstance();

        System.out.println("s1 hashCode: " + s1.hashCode());
        System.out.println("s2 hashCode: " + s2.hashCode());
        System.out.println("s1 == s2? " + (s1 == s2));
    }
}

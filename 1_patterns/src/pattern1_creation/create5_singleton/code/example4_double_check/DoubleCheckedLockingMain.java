package pattern1_creation.create5_singleton.code.example4_double_check;

public class DoubleCheckedLockingMain {
    public static void main(String[] args) {
        System.out.println("=== Double-Checked Locking Singleton ===\n");

        DoubleCheckedLockingSingleton s1 = DoubleCheckedLockingSingleton.getInstance();
        DoubleCheckedLockingSingleton s2 = DoubleCheckedLockingSingleton.getInstance();

        System.out.println("s1 hashCode: " + s1.hashCode());
        System.out.println("s2 hashCode: " + s2.hashCode());
        System.out.println("s1 == s2? " + (s1 == s2));
    }
}

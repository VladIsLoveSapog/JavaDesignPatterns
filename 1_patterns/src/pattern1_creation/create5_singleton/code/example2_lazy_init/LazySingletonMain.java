package pattern1_creation.create5_singleton.code.example2_lazy_init;

public class LazySingletonMain {
    public static void main(String[] args) {
        System.out.println("=== Lazy Initialization Singleton ===\n");

        LazySingleton s1 = LazySingleton.getInstance();
        LazySingleton s2 = LazySingleton.getInstance();

        System.out.println("s1 hashCode: " + s1.hashCode());
        System.out.println("s2 hashCode: " + s2.hashCode());
        System.out.println("s1 == s2? " + (s1 == s2));
    }
}

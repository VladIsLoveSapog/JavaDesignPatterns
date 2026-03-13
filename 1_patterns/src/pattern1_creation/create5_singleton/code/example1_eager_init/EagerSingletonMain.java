package pattern1_creation.create5_singleton.code.example1_eager_init;

public class EagerSingletonMain {
    public static void main(String[] args) {
        System.out.println("=== Eager Initialization Singleton ===\n");

        EagerSingleton s1 = EagerSingleton.getInstance();
        EagerSingleton s2 = EagerSingleton.getInstance();

        System.out.println("s1 hashCode: " + s1.hashCode());
        System.out.println("s2 hashCode: " + s2.hashCode());
        System.out.println("s1 == s2? " + (s1 == s2));
    }
}

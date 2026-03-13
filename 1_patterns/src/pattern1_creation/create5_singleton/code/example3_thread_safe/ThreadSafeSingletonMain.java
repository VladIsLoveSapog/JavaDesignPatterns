package pattern1_creation.create5_singleton.code.example3_thread_safe;

public class ThreadSafeSingletonMain {
    public static void main(String[] args) {
        System.out.println("=== Thread-Safe Singleton ===\n");

        ThreadSafeSingleton s1 = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton s2 = ThreadSafeSingleton.getInstance();

        System.out.println("s1 hashCode: " + s1.hashCode());
        System.out.println("s2 hashCode: " + s2.hashCode());
        System.out.println("s1 == s2? " + (s1 == s2));
    }
}

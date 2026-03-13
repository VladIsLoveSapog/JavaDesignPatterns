package pattern1_creation.create5_singleton.code.example6_enum;

public class EnumSingletonMain {
    public static void main(String[] args) {
        System.out.println("=== Enum Singleton ===\n");

        EnumSingleton s1 = EnumSingleton.INSTANCE;
        EnumSingleton s2 = EnumSingleton.INSTANCE;

        System.out.println("s1 hashCode: " + s1.hashCode());
        System.out.println("s2 hashCode: " + s2.hashCode());
        System.out.println("s1 == s2? " + (s1 == s2));

        // Демонстрация: данные общие, т.к. экземпляр один
        s1.setProperty("db.url", "jdbc:postgresql://localhost:5432/mydb");
        s1.setProperty("db.user", "admin");

        System.out.println("\nЗаписали через s1, читаем через s2:");
        System.out.println("db.url  = " + s2.getProperty("db.url"));
        System.out.println("db.user = " + s2.getProperty("db.user"));
    }
}

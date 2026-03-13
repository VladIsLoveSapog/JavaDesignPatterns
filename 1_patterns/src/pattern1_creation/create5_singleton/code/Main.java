package pattern1_creation.create5_singleton.code;

/**
 * Демонстрация работы синглтона на примере {@link Database}.
 *
 * <p>Программа дважды вызывает {@code Database.getInstance()} и показывает,
 * что оба вызова возвращают один и тот же объект: адреса в памяти совпадают,
 * а сравнение по ссылке ({@code ==}) возвращает {@code true}.
 */
public class Main {
    public static void main(String[] args) {
        Database db1 = Database.getInstance();
        Database db2 = Database.getInstance();

        // При выводе toString() по умолчанию покажет один и тот же hashCode —
        // это подтверждает, что db1 и db2 указывают на один объект.
        System.out.println("db1: " + db1);
        System.out.println("db2: " + db2);

        // Сравнение по ссылке (==) проверяет, что обе переменные
        // указывают на один и тот же объект в куче (heap).
        System.out.println("db1 == db2? " + (db1 == db2));
    }
}

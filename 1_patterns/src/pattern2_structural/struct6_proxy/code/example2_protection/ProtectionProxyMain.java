package pattern2_structural.struct6_proxy.code.example2_protection;

/**
 * Паттерн Заместитель (Proxy) — демонстрация защищающего прокси (Protection Proxy).
 * <p>
 * Три пользователя с разными ролями ({@link Role#READER}, {@link Role#EDITOR}, {@link Role#ADMIN})
 * пытаются выполнить операции чтения, записи и удаления над одним и тем же документом.
 * Защищающий прокси {@link ProtectionProxyDocument} разграничивает доступ,
 * позволяя каждому пользователю выполнять только разрешённые операции.
 */
public class ProtectionProxyMain {
    public static void main(String[] args) {
        // Создаём реальный документ с начальным содержимым
        Document realDocument = new SecureDocument("Отчёт Q1", "Выручка за первый квартал: 1 000 000 руб.");

        // Создаём прокси для трёх пользователей с разными ролями
        Document readerProxy = new ProtectionProxyDocument(realDocument, Role.READER, "Иванов");
        Document editorProxy = new ProtectionProxyDocument(realDocument, Role.EDITOR, "Петрова");
        Document adminProxy = new ProtectionProxyDocument(realDocument, Role.ADMIN, "Сидоров");

        // === Демонстрация: READER ===
        System.out.println("=== Пользователь с ролью READER ===");
        System.out.println(readerProxy.read());       // Успех: чтение разрешено
        readerProxy.write("Попытка изменить отчёт");  // Отказ: нет прав на запись
        readerProxy.delete();                          // Отказ: нет прав на удаление
        System.out.println();

        // === Демонстрация: EDITOR ===
        System.out.println("=== Пользователь с ролью EDITOR ===");
        System.out.println(editorProxy.read());                        // Успех: чтение разрешено
        editorProxy.write("Обновлённый отчёт: выручка 1 200 000 руб.");  // Успех: запись разрешена
        System.out.println(editorProxy.read());                        // Проверяем изменения
        editorProxy.delete();                                          // Отказ: нет прав на удаление
        System.out.println();

        // === Демонстрация: ADMIN ===
        System.out.println("=== Пользователь с ролью ADMIN ===");
        System.out.println(adminProxy.read());   // Успех: чтение разрешено
        adminProxy.delete();                     // Успех: удаление разрешено
        System.out.println(adminProxy.read());   // Документ удалён — соответствующее сообщение
    }
}

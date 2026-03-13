package pattern3_behavior.behavior3_command.code.example1_file_operations;

/**
 * Демонстрация паттерна Команда на примере операций с текстовыми файлами.
 *
 * <p>Это простейшая реализация Command <b>без поддержки отмены (undo)</b> —
 * первое знакомство с паттерном. Команды создаются как объекты и передаются
 * исполнителю ({@link TextFileOperationExecutor}), который их выполняет
 * и сохраняет в истории.
 *
 * <p>Также демонстрируется использование <b>лямбда-выражений</b>:
 * интерфейс {@link TextFileOperation} помечен как {@code @FunctionalInterface},
 * поэтому вместо создания отдельного класса можно передать лямбду.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Операции с файлами ===\n");

        // Invoker — исполнитель команд
        TextFileOperationExecutor executor = new TextFileOperationExecutor();

        // Receiver — файлы, над которыми выполняются операции
        TextFile file1 = new TextFile("file1.txt");
        TextFile file2 = new TextFile("file2.txt");

        // ConcreteCommand — команда открытия файла
        System.out.println(executor.executeOperation(new OpenTextFileOperation(file1)));

        // ConcreteCommand — команда сохранения файла
        System.out.println(executor.executeOperation(new SaveTextFileOperation(file2)));

        // Лямбда-выражение вместо отдельного класса ConcreteCommand:
        // TextFileOperation — функциональный интерфейс, поэтому можно передать лямбду
        TextFile file3 = new TextFile("file3.txt");
        System.out.println(executor.executeOperation(() -> file3.open() + " [via lambda]"));
    }
}

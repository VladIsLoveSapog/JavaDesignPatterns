package pattern3_behavior.behavior3_command.code.example1_file_operations;

import java.util.ArrayList;
import java.util.List;

/**
 * Исполнитель операций над файлами (роль <b>Invoker</b>).
 *
 * <p>Хранит историю всех выполненных команд ({@link TextFileOperation}).
 * При вызове {@link #executeOperation(TextFileOperation)} команда сначала
 * добавляется в список, а затем выполняется. Это позволяет в дальнейшем
 * реализовать повтор команд, логирование или пакетное выполнение.
 *
 * <p><b>Когда использовать:</b> когда нужно централизовать выполнение
 * операций, вести их журнал или организовать очередь запросов.
 */
public class TextFileOperationExecutor {

    /**
     * История выполненных операций.
     * Может использоваться для повторного выполнения, логирования или аудита.
     */
    private final List<TextFileOperation> textFileOperations = new ArrayList<>();

    /**
     * Выполняет команду и добавляет её в историю.
     *
     * @param textFileOperation команда для выполнения
     * @return результат выполнения команды
     */
    public String executeOperation(TextFileOperation textFileOperation) {
        textFileOperations.add(textFileOperation);
        return textFileOperation.execute();
    }
}

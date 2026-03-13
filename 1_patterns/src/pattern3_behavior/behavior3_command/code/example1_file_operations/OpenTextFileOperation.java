package pattern3_behavior.behavior3_command.code.example1_file_operations;

/**
 * Команда открытия текстового файла (роль <b>ConcreteCommand</b>).
 *
 * <p>Инкапсулирует вызов метода {@link TextFile#open()} в объект-команду.
 * Хранит ссылку на получателя ({@link TextFile}) и делегирует ему
 * фактическое выполнение операции.
 *
 * <p><b>Когда использовать:</b> когда нужно передать операцию открытия файла
 * как объект — например, поставить её в очередь операций
 * ({@link TextFileOperationExecutor}) или сохранить для повторного вызова.
 */
public class OpenTextFileOperation implements TextFileOperation {

    /** Получатель команды — файл, который будет открыт. */
    private TextFile textFile;

    /**
     * Создаёт команду открытия для указанного файла.
     *
     * @param textFile файл-получатель, над которым выполняется операция
     */
    public OpenTextFileOperation(TextFile textFile) {
        this.textFile = textFile;
    }

    /**
     * Выполняет операцию открытия, делегируя вызов получателю.
     *
     * @return описание результата операции открытия
     */
    @Override
    public String execute() {
        return textFile.open();
    }
}

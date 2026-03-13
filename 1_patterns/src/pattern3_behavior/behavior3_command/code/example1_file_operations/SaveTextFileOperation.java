package pattern3_behavior.behavior3_command.code.example1_file_operations;

/**
 * Команда сохранения текстового файла (роль <b>ConcreteCommand</b>).
 *
 * <p>Инкапсулирует вызов метода {@link TextFile#save()} в объект-команду.
 * Хранит ссылку на получателя ({@link TextFile}) и делегирует ему
 * фактическое выполнение операции.
 *
 * <p><b>Когда использовать:</b> когда нужно передать операцию сохранения файла
 * как объект — например, поставить её в очередь операций
 * ({@link TextFileOperationExecutor}) или сохранить для повторного вызова.
 */
public class SaveTextFileOperation implements TextFileOperation {

    /** Получатель команды — файл, который будет сохранён. */
    private TextFile textFile;

    /**
     * Создаёт команду сохранения для указанного файла.
     *
     * @param textFile файл-получатель, над которым выполняется операция
     */
    public SaveTextFileOperation(TextFile textFile) {
        this.textFile = textFile;
    }

    /**
     * Выполняет операцию сохранения, делегируя вызов получателю.
     *
     * @return описание результата операции сохранения
     */
    @Override
    public String execute() {
        return textFile.save();
    }
}

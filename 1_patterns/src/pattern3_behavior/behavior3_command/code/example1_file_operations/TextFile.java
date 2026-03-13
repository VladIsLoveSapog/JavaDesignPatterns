package pattern3_behavior.behavior3_command.code.example1_file_operations;

/**
 * Текстовый файл — получатель команд (роль <b>Receiver</b>).
 *
 * <p>Содержит бизнес-логику работы с файлом: открытие и сохранение.
 * Конкретные команды ({@link OpenTextFileOperation}, {@link SaveTextFileOperation})
 * делегируют выполнение именно этому классу.
 *
 * <p><b>Почему отдельный класс:</b> паттерн Command отделяет инициатора
 * запроса от объекта, который его выполняет. {@code TextFile} — это объект,
 * над которым выполняется действие; команда лишь вызывает нужный метод.
 *
 * <p><b>Когда использовать:</b> когда бизнес-логика (открытие, сохранение,
 * форматирование файла) должна быть отделена от механизма вызова и очередей.
 */
public class TextFile {

    /** Имя текстового файла. */
    private String name;

    /**
     * Создаёт текстовый файл с указанным именем.
     *
     * @param name имя файла
     */
    public TextFile(String name) {
        this.name = name;
    }

    /**
     * Открывает файл.
     *
     * @return описание выполненной операции открытия
     */
    public String open() {
        return "Opening file " + name;
    }

    /**
     * Сохраняет файл.
     *
     * @return описание выполненной операции сохранения
     */
    public String save() {
        return "Saving file " + name;
    }
}

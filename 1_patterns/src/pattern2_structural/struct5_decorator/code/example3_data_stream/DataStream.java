package pattern2_structural.struct5_decorator.code.example3_data_stream;

/**
 * Компонент (Component) — интерфейс потока данных.
 *
 * <p>Этот пример вдохновлён стандартной библиотекой Java: классы
 * {@code InputStream}, {@code BufferedInputStream}, {@code GZIPInputStream}
 * и т.д. построены именно по паттерну Декоратор.
 *
 * <p>Интерфейс определяет две операции: чтение и запись данных.
 * Декораторы будут добавлять к этим операциям дополнительную обработку —
 * сжатие и Base64-кодирование.
 */
public interface DataStream {

    /**
     * Записывает данные в поток.
     *
     * @param data данные для записи
     */
    void write(String data);

    /**
     * Читает данные из потока.
     *
     * @return прочитанные данные
     */
    String read();
}

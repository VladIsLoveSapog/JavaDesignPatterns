package pattern2_structural.struct5_decorator.code.example3_data_stream;

/**
 * Конкретный компонент (Concrete Component) — простой строковый поток данных.
 *
 * <p>Хранит данные в обычной строке в памяти. Запись сохраняет данные,
 * чтение — возвращает сохранённые данные.
 *
 * <p>Это базовый объект, который можно обернуть декораторами для
 * добавления сжатия, кодирования и другой обработки.
 */
public class StringDataStream implements DataStream {

    /** Буфер для хранения данных. */
    private String buffer = "";

    /**
     * Сохраняет данные в буфер.
     *
     * @param data данные для записи
     */
    @Override
    public void write(String data) {
        buffer = data;
    }

    /**
     * Возвращает данные из буфера без какой-либо обработки.
     *
     * @return данные из буфера
     */
    @Override
    public String read() {
        return buffer;
    }
}

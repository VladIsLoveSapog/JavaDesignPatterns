package pattern2_structural.struct5_decorator.code.example3_data_stream;

/**
 * Базовый декоратор (Base Decorator) для потоков данных.
 *
 * <p>Реализует интерфейс {@link DataStream} и делегирует все вызовы
 * обёрнутому потоку. Конкретные декораторы наследуются от этого класса
 * и добавляют свою обработку до или после делегирования.
 *
 * <p><b>Аналогия из стандартной библиотеки:</b> класс {@code FilterInputStream}
 * играет точно такую же роль — он является базовым декоратором
 * для всех обёрток потоков ввода-вывода в Java.
 */
public abstract class DataStreamDecorator implements DataStream {

    /**
     * Ссылка на оборачиваемый поток.
     * Может быть как конкретным компонентом, так и другим декоратором.
     */
    protected DataStream wrappedStream;

    /**
     * @param wrappedStream поток, который оборачивается данным декоратором
     */
    public DataStreamDecorator(DataStream wrappedStream) {
        this.wrappedStream = wrappedStream;
    }

    /**
     * По умолчанию делегирует запись обёрнутому потоку.
     */
    @Override
    public void write(String data) {
        wrappedStream.write(data);
    }

    /**
     * По умолчанию делегирует чтение обёрнутому потоку.
     */
    @Override
    public String read() {
        return wrappedStream.read();
    }
}

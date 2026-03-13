package pattern2_structural.struct5_decorator.code.example3_data_stream;

/**
 * Конкретный декоратор (Concrete Decorator) — «сжатие» данных.
 *
 * <p>При записи имитирует сжатие данных (в учебных целях — просто
 * удаляет пробелы и переводит в нижний регистр). При чтении
 * добавляет пометку о том, что данные были сжаты.
 *
 * <p>В реальном проекте здесь мог бы быть GZIP или Deflate,
 * как в {@code GZIPOutputStream} из стандартной библиотеки Java.
 */
public class CompressionDecorator extends DataStreamDecorator {

    /**
     * @param wrappedStream поток, к которому применяется сжатие
     */
    public CompressionDecorator(DataStream wrappedStream) {
        super(wrappedStream);
    }

    /**
     * «Сжимает» данные перед записью в обёрнутый поток.
     * Сжатие симулируется удалением пробелов и приведением к нижнему регистру.
     *
     * @param data исходные данные
     */
    @Override
    public void write(String data) {
        String compressed = compress(data);
        wrappedStream.write(compressed);
    }

    /**
     * Читает данные из обёрнутого потока и помечает их как сжатые.
     *
     * @return данные с пометкой о сжатии
     */
    @Override
    public String read() {
        return "[compressed] " + wrappedStream.read();
    }

    /**
     * Имитация сжатия: удаляем пробелы и переводим в нижний регистр.
     * В учебных целях этого достаточно, чтобы показать принцип —
     * декоратор трансформирует данные «на лету».
     *
     * @param data исходные данные
     * @return «сжатые» данные
     */
    private String compress(String data) {
        return data.replaceAll("\\s+", "").toLowerCase();
    }
}

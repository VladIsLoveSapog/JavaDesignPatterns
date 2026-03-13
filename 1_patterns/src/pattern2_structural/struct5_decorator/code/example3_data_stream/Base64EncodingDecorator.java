package pattern2_structural.struct5_decorator.code.example3_data_stream;

import java.util.Base64;

/**
 * Конкретный декоратор (Concrete Decorator) — Base64-кодирование данных.
 *
 * <p>При записи кодирует данные в формат Base64. При чтении —
 * декодирует обратно. Это демонстрирует симметричную обработку:
 * декоратор умеет как добавлять свой «слой», так и снимать его.
 *
 * <p>Base64 часто используется для передачи бинарных данных
 * в текстовом виде (например, в JSON, XML, email).
 */
public class Base64EncodingDecorator extends DataStreamDecorator {

    /**
     * @param wrappedStream поток, данные которого будут кодироваться в Base64
     */
    public Base64EncodingDecorator(DataStream wrappedStream) {
        super(wrappedStream);
    }

    /**
     * Кодирует данные в Base64 перед записью в обёрнутый поток.
     *
     * @param data исходные данные в текстовом виде
     */
    @Override
    public void write(String data) {
        String encoded = Base64.getEncoder().encodeToString(data.getBytes());
        wrappedStream.write(encoded);
    }

    /**
     * Читает данные из обёрнутого потока и декодирует их из Base64.
     * Если данные не были закодированы — может выбросить исключение,
     * поэтому важно соблюдать порядок декораторов.
     *
     * @return декодированные данные
     */
    @Override
    public String read() {
        String encodedData = wrappedStream.read();
        byte[] decodedBytes = Base64.getDecoder().decode(encodedData);
        return new String(decodedBytes);
    }
}

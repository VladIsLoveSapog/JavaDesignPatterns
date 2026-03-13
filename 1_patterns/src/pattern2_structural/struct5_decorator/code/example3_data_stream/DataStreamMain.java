package pattern2_structural.struct5_decorator.code.example3_data_stream;

/**
 * Демонстрация паттерна Декоратор на примере потоков данных.
 *
 * <p>Этот пример ближе всего к реальному использованию паттерна Декоратор
 * в стандартной библиотеке Java (java.io). Показывает, как декораторы
 * добавляют обработку данных (сжатие, кодирование) поверх базового потока,
 * не меняя его код.
 *
 * <p><b>Ожидаемый вывод:</b>
 * <pre>
 * === Простой поток ===
 * Записано: Hello World
 * Прочитано: Hello World
 *
 * === Поток с Base64-кодированием ===
 * Записано (Base64): Hello World
 * Прочитано (декодировано): Hello World
 *
 * === Поток со сжатием ===
 * Записано (со сжатием): Hello World
 * Прочитано: [compressed] helloworld
 *
 * === Комбинация: сжатие + Base64 ===
 * Записано (сжатие → Base64): Hello World
 * Прочитано (Base64 → декомпрессия): [compressed] aGVsbG93b3JsZA==
 * </pre>
 */
public class DataStreamMain {

    public static void main(String[] args) {
        // 1. Простой поток — данные записываются и читаются как есть
        System.out.println("=== Простой поток ===");
        DataStream simpleStream = new StringDataStream();
        simpleStream.write("Hello World");
        System.out.println("Записано: Hello World");
        System.out.println("Прочитано: " + simpleStream.read());

        System.out.println();

        // 2. Оборачиваем поток Base64-декоратором.
        //    При записи данные кодируются, при чтении — декодируются обратно.
        System.out.println("=== Поток с Base64-кодированием ===");
        DataStream base64Stream = new Base64EncodingDecorator(new StringDataStream());
        base64Stream.write("Hello World");
        System.out.println("Записано (Base64): Hello World");
        System.out.println("Прочитано (декодировано): " + base64Stream.read());

        System.out.println();

        // 3. Оборачиваем поток декоратором сжатия.
        //    При записи данные «сжимаются», при чтении — помечаются как сжатые.
        System.out.println("=== Поток со сжатием ===");
        DataStream compressedStream = new CompressionDecorator(new StringDataStream());
        compressedStream.write("Hello World");
        System.out.println("Записано (со сжатием): Hello World");
        System.out.println("Прочитано: " + compressedStream.read());

        System.out.println();

        // 4. Комбинируем декораторы: сначала сжатие, затем Base64.
        //    Цепочка записи: CompressionDecorator → Base64EncodingDecorator → StringDataStream
        //    Данные сначала сжимаются, затем кодируются в Base64.
        //    Это аналог конструкции из java.io:
        //    new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("file.gz")))
        System.out.println("=== Комбинация: сжатие + Base64 ===");
        DataStream combinedStream = new CompressionDecorator(
                new Base64EncodingDecorator(new StringDataStream())
        );
        combinedStream.write("Hello World");
        System.out.println("Записано (сжатие → Base64): Hello World");
        System.out.println("Прочитано (Base64 → декомпрессия): " + combinedStream.read());
    }
}

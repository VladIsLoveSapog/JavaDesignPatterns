package pattern2_structural.struct5_decorator.code.example2_message;

/**
 * Демонстрация паттерна Декоратор на примере обработки сообщений.
 *
 * <p>Показывает, как один и тот же объект-сообщение можно обернуть
 * различными декораторами (шифрование, временная метка) и как
 * порядок оборачивания влияет на результат.
 *
 * <p><b>Ожидаемый вывод (временная метка будет актуальной):</b>
 * <pre>
 * Simple Message: Hello, World!
 * Encrypted Message: !dlroW ,olleH
 * Timestamped Message: [2024-01-15 14:30:00] Hello, World!
 * Encrypted & Timestamped Message: [2024-01-15 14:30:00] !dlroW ,olleH
 * </pre>
 */
public class MessageMain {

    public static void main(String[] args) {
        // 1. Создаём простое сообщение — конкретный компонент
        Message simpleMessage = new SimpleMessage("Hello, World!");
        System.out.println("Simple Message: " + simpleMessage.getContent());

        // 2. Оборачиваем сообщение декоратором шифрования.
        //    Текст будет развёрнут задом наперёд: "Hello, World!" → "!dlroW ,olleH"
        Message encryptedMessage = new EncryptedMessageDecorator(simpleMessage);
        System.out.println("Encrypted Message: " + encryptedMessage.getContent());

        // 3. Оборачиваем оригинальное сообщение декоратором временной метки.
        //    Обратите внимание: мы оборачиваем simpleMessage, а не encryptedMessage —
        //    каждый декоратор работает независимо.
        Message timestampedMessage = new TimestampedMessageDecorator(simpleMessage);
        System.out.println("Timestamped Message: " + timestampedMessage.getContent());

        // 4. Комбинируем декораторы: сначала шифруем, затем добавляем метку.
        //    Цепочка вызовов: TimestampedDecorator → EncryptedDecorator → SimpleMessage
        //    Порядок важен! Если поменять местами — результат будет другим.
        Message encryptedTimestampedMessage = new TimestampedMessageDecorator(encryptedMessage);
        System.out.println("Encrypted & Timestamped Message: " + encryptedTimestampedMessage.getContent());
    }
}

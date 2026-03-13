package pattern3_behavior.behavior10_template_method.code.example3_document_generator;

/**
 * Клиентский код — демонстрация паттерна <b>Template Method</b>
 * на примере генерации документов.
 *
 * <p>Создаются два генератора: {@link HtmlDocumentGenerator} и
 * {@link PlainTextDocumentGenerator}. Оба используют один и тот же
 * шаблонный метод {@link AbstractDocumentGenerator#generateDocument()},
 * но отличаются реализацией абстрактных шагов и поведением хука
 * {@code addTimestamp()}.
 *
 * <p><b>Ожидаемый результат:</b>
 * <ul>
 *   <li>HTML-документ — содержит метку времени (хук по умолчанию)</li>
 *   <li>Текстовый документ — НЕ содержит метку времени (хук переопределён)</li>
 * </ul>
 */
public class Main {
    public static void main(String[] args) {
        // HTML-генератор: хук addTimestamp() не переопределён → метка времени добавляется
        AbstractDocumentGenerator htmlGenerator = new HtmlDocumentGenerator();
        System.out.println("--- HTML-документ ---");
        htmlGenerator.generateDocument();

        System.out.println();

        // Текстовый генератор: хук addTimestamp() → false → метка времени НЕ добавляется
        AbstractDocumentGenerator textGenerator = new PlainTextDocumentGenerator();
        System.out.println("--- Текстовый документ ---");
        textGenerator.generateDocument();
    }
}

package pattern3_behavior.behavior1_chain.code.example3_validation;

/**
 * <p><b>Демонстрация паттерна «Цепочка обязанностей»</b> на примере
 * валидации документов с терминальным узлом.</p>
 *
 * <p>Цепочка валидаторов строится в следующем порядке:</p>
 * <ol>
 *     <li>{@link JsonValidator} — обрабатывает документы типа {@code "JSON"};</li>
 *     <li>{@link XmlValidator} — обрабатывает документы типа {@code "XML"};</li>
 *     <li>{@link CsvValidator} — обрабатывает документы типа {@code "CSV"};</li>
 *     <li>{@link TerminalValidator} — выбрасывает {@link UnsupportedDocumentException}
 *         для всех остальных типов.</li>
 * </ol>
 *
 * <p>Демонстрируются три сценария:</p>
 * <ul>
 *     <li>JSON-документ — успешно обрабатывается первым звеном;</li>
 *     <li>XML-документ — успешно обрабатывается вторым звеном;</li>
 *     <li>TXT-документ — проходит всю цепочку и вызывает исключение
 *         в терминальном узле.</li>
 * </ul>
 */
public class Main {

    public static void main(String[] args) {
        // Создание валидаторов
        DocumentValidator jsonValidator = new JsonValidator();
        DocumentValidator xmlValidator = new XmlValidator();
        DocumentValidator csvValidator = new CsvValidator();
        DocumentValidator terminalValidator = new TerminalValidator();

        // Настройка цепочки: JSON → XML → CSV → Terminal
        jsonValidator.setNext(xmlValidator);
        xmlValidator.setNext(csvValidator);
        csvValidator.setNext(terminalValidator);

        // Тест 1: JSON-документ — обрабатывается JsonValidator
        Document jsonDoc = new Document("JSON", "{\"name\": \"test\"}");
        System.out.println("=== Тест 1: валидация JSON-документа ===");
        System.out.println("Отправка документа: " + jsonDoc);
        jsonValidator.validate(jsonDoc);

        // Тест 2: XML-документ — обрабатывается XmlValidator
        Document xmlDoc = new Document("XML", "<root><name>test</name></root>");
        System.out.println("\n=== Тест 2: валидация XML-документа ===");
        System.out.println("Отправка документа: " + xmlDoc);
        jsonValidator.validate(xmlDoc);

        // Тест 3: TXT-документ — доходит до TerminalValidator, выбрасывается исключение
        Document txtDoc = new Document("TXT", "Просто текстовый файл");
        System.out.println("\n=== Тест 3: валидация TXT-документа (неподдерживаемый тип) ===");
        System.out.println("Отправка документа: " + txtDoc);
        try {
            jsonValidator.validate(txtDoc);
        } catch (UnsupportedDocumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }
    }
}

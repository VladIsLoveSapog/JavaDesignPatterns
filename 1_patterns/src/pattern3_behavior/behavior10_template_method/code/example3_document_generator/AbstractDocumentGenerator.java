package pattern3_behavior.behavior10_template_method.code.example3_document_generator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Абстрактный генератор документов — <b>AbstractClass</b> паттерна Template Method.
 *
 * <p>Определяет скелет алгоритма генерации документа в шаблонном методе
 * {@link #generateDocument()}, объявленном как {@code final}.
 * Подклассы обязаны реализовать абстрактные шаги {@link #createTitle()} и
 * {@link #createBody()}, но не могут изменить общий порядок выполнения.
 *
 * <p><b>Типы методов в этом классе:</b>
 * <ul>
 *   <li>{@code generateDocument()} — шаблонный метод ({@code final})</li>
 *   <li>{@code createTitle()}, {@code createBody()} — абстрактные шаги</li>
 *   <li>{@code addTimestamp()} — хук (возвращает {@code true} по умолчанию)</li>
 *   <li>{@code getTimestamp()}, {@code printDocument()} — конкретные шаги</li>
 * </ul>
 *
 * @see HtmlDocumentGenerator
 * @see PlainTextDocumentGenerator
 */
public abstract class AbstractDocumentGenerator {

    /**
     * <b>Шаблонный метод</b> — определяет неизменяемый алгоритм генерации документа.
     *
     * <p>Порядок шагов фиксирован и не может быть изменён подклассами,
     * поскольку метод объявлен как {@code final}.
     * Алгоритм:
     * <ol>
     *   <li>Создать заголовок (абстрактный шаг)</li>
     *   <li>Создать тело документа (абстрактный шаг)</li>
     *   <li>Если хук {@link #addTimestamp()} вернул {@code true} — добавить метку времени</li>
     *   <li>Напечатать готовый документ (конкретный шаг)</li>
     * </ol>
     */
    public final void generateDocument() {
        String title = createTitle();           // абстрактный шаг
        String body = createBody();             // абстрактный шаг
        String result = title + "\n" + body;
        if (addTimestamp()) {                   // хук (boolean)
            result += "\n" + getTimestamp();    // конкретный шаг
        }
        printDocument(result);                  // конкретный шаг
    }

    /**
     * <b>Абстрактный шаг</b> — создаёт заголовок документа.
     *
     * <p>Каждый подкласс формирует заголовок в своём формате
     * (HTML, plain text и т.д.).
     *
     * @return строка с заголовком документа
     */
    protected abstract String createTitle();

    /**
     * <b>Абстрактный шаг</b> — создаёт тело документа.
     *
     * <p>Каждый подкласс формирует тело в своём формате.
     *
     * @return строка с телом документа
     */
    protected abstract String createBody();

    /**
     * <b>Хук</b> — определяет, нужно ли добавлять метку времени к документу.
     *
     * <p>По умолчанию возвращает {@code true}. Подклассы могут переопределить
     * этот метод, вернув {@code false}, чтобы отключить добавление метки времени.
     *
     * @return {@code true}, если метка времени должна быть добавлена
     */
    protected boolean addTimestamp() {
        return true;
    }

    /**
     * <b>Конкретный шаг</b> — формирует строку с текущей датой и временем.
     *
     * <p>Используется шаблонным методом, если хук {@link #addTimestamp()}
     * вернул {@code true}.
     *
     * @return отформатированная строка с меткой времени
     */
    private String getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Дата создания: " + LocalDateTime.now().format(formatter);
    }

    /**
     * <b>Конкретный шаг</b> — выводит готовый документ в консоль.
     *
     * @param document итоговый текст документа для печати
     */
    private void printDocument(String document) {
        System.out.println("=== Документ ===");
        System.out.println(document);
        System.out.println("================");
    }
}

package pattern3_behavior.behavior1_chain.code.example1_message;

/**
 * Демонстрация паттерна «Цепочка обязанностей» (Chain of Responsibility)
 * для распределения уровня уведомлений.
 *
 * <p>Создаётся цепочка из трёх обработчиков, связанных по возрастанию
 * приоритета:
 * <ol>
 *   <li>{@link LogReportMessageSender} — запись в лог ({@link PriorityLevel#LOW})</li>
 *   <li>{@link EmailMessageSender} — отправка email ({@link PriorityLevel#MIDDLE})</li>
 *   <li>{@link SMSMessageSender} — отправка SMS ({@link PriorityLevel#HIGH})</li>
 * </ol>
 *
 * <p><b>Принцип работы:</b> сообщение подаётся на вход первого обработчика.
 * Каждый обработчик проверяет, соответствует ли его уровень приоритета уровню
 * сообщения, и при совпадении выполняет действие. Затем запрос передаётся
 * следующему обработчику в цепочке.
 *
 * @see Handler
 * @see MessageSender
 * @see PriorityLevel
 */
public class Main {

    /**
     * Точка входа — собирает цепочку обработчиков и отправляет сообщения
     * с различными уровнями приоритета.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        // Обработчик для записи в лог, минимальный приоритет
        MessageSender logMessageSender = new LogReportMessageSender(PriorityLevel.LOW);

        // Обработчик для уведомления по email, средний приоритет
        MessageSender emailMessageSender = new EmailMessageSender(PriorityLevel.MIDDLE);

        // Обработчик для уведомления по СМС, высокий приоритет
        MessageSender smsMessageSender = new SMSMessageSender(PriorityLevel.HIGH);

        // Связываем обработчики по возрастанию приоритета
        logMessageSender.setNext(emailMessageSender);
        emailMessageSender.setNext(smsMessageSender);

        // Начинаем работу цепочки с наименьшего приоритета
        // Сообщение с низким приоритетом
        logMessageSender.handle("Something is happening!", PriorityLevel.LOW);

        // Сообщение со средним приоритетом
        System.out.println("---------------------------------------------------------------------");
        logMessageSender.handle("Something went wrong!", PriorityLevel.MIDDLE);

        // Сообщение с высоким приоритетом
        System.out.println("---------------------------------------------------------------------");
        logMessageSender.handle("We had a problem!", PriorityLevel.HIGH);
    }
}

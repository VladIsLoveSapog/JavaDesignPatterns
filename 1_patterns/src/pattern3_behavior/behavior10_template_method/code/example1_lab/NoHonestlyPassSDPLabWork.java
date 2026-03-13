package pattern3_behavior.behavior10_template_method.code.example1_lab;

/**
 * Нечестная сдача лабораторной работы — <b>ConcreteClass</b> паттерна Template Method.
 *
 * <p>Студент списывает работу, пытается выдать её за свою,
 * но преподаватель замечает обман и отправляет на переделку.
 *
 * <p><b>Переопределены оба хука:</b>
 * <ul>
 *   <li>{@link #writeOff()} — студент списывает чужую работу</li>
 *   <li>{@link #correctRemarks()} — преподаватель замечает обман
 *       и отправляет на переделку</li>
 * </ul>
 *
 * @see PassLabWork
 * @see HonestlyPassSDPLabWork
 */
public class NoHonestlyPassSDPLabWork extends PassLabWork {

    /** Хук — студент списывает чужую работу, надеясь, что никто не заметит. */
    @Override
    public void writeOff() {
        System.out.println("Гениально списал работу, думая что никто не заметит.");
    }

    /** Абстрактный шаг — студент делает вид, что программу написал сам. */
    @Override
    public void writeProgram() {
        System.out.println("Сделал вид, что программу написал я.");
    }

    /** Абстрактный шаг — студент маскирует чужой отчёт под свой. */
    @Override
    public void writeReport() {
        System.out.println("Что-то добавил в чужой отчет, чтобы не было видно, что я списал.");
    }

    /** Абстрактный шаг — студент показывает «свою» программу. */
    @Override
    public void showProgram() {
        System.out.println("Показал \"свою\" программу.");
    }

    /** Абстрактный шаг — студент показывает «свой» отчёт. */
    @Override
    public void showReport() {
        System.out.println("Показал \"свой\" отчет.");
    }

    /** Хук — преподаватель замечает списывание и отправляет на переделку. */
    @Override
    public void correctRemarks() {
        System.out.println("Препод гад заметил списывание и отправил на переделку.");
    }
}

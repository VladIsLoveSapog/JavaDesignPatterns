package pattern3_behavior.behavior10_template_method.code.example1_lab;

/**
 * Честная сдача лабораторной работы — <b>ConcreteClass</b> паттерна Template Method.
 *
 * <p>Реализует все абстрактные шаги алгоритма: студент самостоятельно
 * пишет программу и отчёт, затем показывает их преподавателю.
 *
 * <p><b>Хуки не переопределены:</b>
 * <ul>
 *   <li>{@link #writeOff()} — студент не списывает</li>
 *   <li>{@link #correctRemarks()} — замечаний нет, исправлять нечего</li>
 * </ul>
 *
 * @see PassLabWork
 * @see NoHonestlyPassSDPLabWork
 */
public class HonestlyPassSDPLabWork extends PassLabWork {

    /** Абстрактный шаг — студент честно пишет программу самостоятельно. */
    @Override
    public void writeProgram() {
        System.out.println("Честно написал программу.");
    }

    /** Абстрактный шаг — студент честно пишет отчёт самостоятельно. */
    @Override
    public void writeReport() {
        System.out.println("Честно написал отчет");
    }

    /** Абстрактный шаг — студент показывает свою программу преподавателю. */
    @Override
    public void showProgram() {
        System.out.println("Показал свою честно написанную программу.");
    }

    /** Абстрактный шаг — студент показывает свой отчёт преподавателю. */
    @Override
    public void showReport() {
        System.out.println("Показал свой честно написанный отчет.");
    }
}

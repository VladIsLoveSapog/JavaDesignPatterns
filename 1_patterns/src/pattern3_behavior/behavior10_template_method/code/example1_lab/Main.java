package pattern3_behavior.behavior10_template_method.code.example1_lab;

/**
 * Клиентский код — демонстрация паттерна <b>Template Method</b>
 * на примере сдачи лабораторной работы.
 *
 * <p>Создаются два варианта сдачи: {@link HonestlyPassSDPLabWork}
 * (честная) и {@link NoHonestlyPassSDPLabWork} (нечестная).
 * Оба используют один и тот же шаблонный метод {@link PassLabWork#pass()},
 * но отличаются реализацией абстрактных шагов и хуков.
 *
 * <p><b>Ожидаемый результат:</b>
 * <ul>
 *   <li>Честная сдача — хуки не срабатывают, студент проходит без замечаний</li>
 *   <li>Нечестная сдача — оба хука активны: списывание + переделка</li>
 * </ul>
 */
public class Main {
    public static void main(String[] args) {
        // Честная сдача: хуки writeOff() и correctRemarks() не переопределены
        IPassLabWork passLW = new HonestlyPassSDPLabWork();
        passLW.pass();

        System.out.println("------------");

        // Нечестная сдача: оба хука переопределены — списал и отправлен на переделку
        passLW = new NoHonestlyPassSDPLabWork();
        passLW.pass();
    }
}

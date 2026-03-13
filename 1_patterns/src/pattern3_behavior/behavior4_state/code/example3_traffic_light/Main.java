package pattern3_behavior.behavior4_state.code.example3_traffic_light;

/**
 * Демонстрация паттерна Состояние на примере светофора.
 *
 * <p>Светофор циклически переключается между тремя состояниями:
 * Красный → Зелёный → Жёлтый → Красный → ...
 *
 * <p>Каждое конкретное состояние ({@link RedLight}, {@link GreenLight},
 * {@link YellowLight}) самостоятельно определяет, какой сигнал будет
 * следующим, а контекст ({@link TrafficLight}) лишь делегирует
 * вызов текущему состоянию.
 *
 * <p><b>Ожидаемый вывод:</b>
 * <pre>
 * Текущий сигнал: Красный
 * Красный → Зелёный: движение разрешено.
 * Текущий сигнал: Зелёный
 * Зелёный → Жёлтый: внимание, скоро смена сигнала.
 * Текущий сигнал: Жёлтый
 * Жёлтый → Красный: движение запрещено.
 * Текущий сигнал: Красный
 * Красный → Зелёный: движение разрешено.
 * Текущий сигнал: Зелёный
 * </pre>
 */
public class Main {
    public static void main(String[] args) {
        // Создаём светофор — начальное состояние: Красный
        TrafficLight trafficLight = new TrafficLight();
        System.out.println("Текущий сигнал: " + trafficLight.getCurrentColor());

        // Переключение 1: Красный → Зелёный
        trafficLight.nextSignal();
        System.out.println("Текущий сигнал: " + trafficLight.getCurrentColor());

        // Переключение 2: Зелёный → Жёлтый
        trafficLight.nextSignal();
        System.out.println("Текущий сигнал: " + trafficLight.getCurrentColor());

        // Переключение 3: Жёлтый → Красный (цикл замкнулся)
        trafficLight.nextSignal();
        System.out.println("Текущий сигнал: " + trafficLight.getCurrentColor());

        // Переключение 4: Красный → Зелёный (новый цикл)
        trafficLight.nextSignal();
        System.out.println("Текущий сигнал: " + trafficLight.getCurrentColor());
    }
}

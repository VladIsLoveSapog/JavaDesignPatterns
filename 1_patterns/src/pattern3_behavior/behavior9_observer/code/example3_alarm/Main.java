package pattern3_behavior.behavior9_observer.code.example3_alarm;

/**
 * Демонстрация паттерна «Наблюдатель» (Observer) на примере системы мониторинга температуры.
 *
 * <p>Программа моделирует работу температурного датчика ({@link TemperatureSensor}),
 * к которому подключены несколько наблюдателей: два дисплея ({@link Display})
 * и сигнализация ({@link Alarm}). При каждом изменении температуры все
 * подписчики автоматически получают уведомления.
 *
 * <p><b>Порядок выполнения:</b>
 * <ol>
 *   <li>Создаётся объект-издатель {@link TemperatureSensor}</li>
 *   <li>Создаются наблюдатели: {@code Display("1")}, {@code Display("2")} и {@code Alarm}</li>
 *   <li>Наблюдатели подписываются на издателя через {@link Subject#addObserver(Observer)}</li>
 *   <li>Температура последовательно меняется: {@code 25.0°C}, {@code 28.5°C}, {@code 32.0°C}</li>
 *   <li>При каждом изменении все наблюдатели получают уведомление</li>
 *   <li>{@link Alarm} срабатывает только при {@code 32.0°C} (порог — {@code 30.0°C})</li>
 * </ol>
 *
 * <p><b>Ожидаемый вывод:</b>
 * <pre>
 * Дисплей 1 отображает температуру: 25.0°C
 * Дисплей 2 отображает температуру: 25.0°C
 * Дисплей 1 отображает температуру: 28.5°C
 * Дисплей 2 отображает температуру: 28.5°C
 * Дисплей 1 отображает температуру: 32.0°C
 * Дисплей 2 отображает температуру: 32.0°C
 * Сигнал тревоги! Высокая температура: 32.0°C
 * </pre>
 *
 * @see TemperatureSensor
 * @see Observer
 * @see Display
 * @see Alarm
 */
public class Main {
    public static void main(String[] args) {
        // Создаем наблюдаемый объект
        TemperatureSensor sensor = new TemperatureSensor();

        // Создаем наблюдателей
        Display display1 = new Display("1");
        Display display2 = new Display("2");
        Alarm alarm = new Alarm();

        // Подписываем наблюдателей на объект
        sensor.addObserver(display1);
        sensor.addObserver(display2);
        sensor.addObserver(alarm);

        // Изменяем состояние объекта
        sensor.setTemperature(25.0f);
        sensor.setTemperature(28.5f);
        sensor.setTemperature(32.0f);
    }
}

package pattern3_behavior.behavior9_observer.code.example3_alarm;

/**
 * Конкретный наблюдатель (ConcreteObserver) — дисплей для отображения температуры.
 *
 * <p>Реализует интерфейс {@link Observer} и выводит текущее значение температуры
 * на консоль при каждом получении уведомления от издателя ({@link TemperatureSensor}).
 * Каждый экземпляр имеет уникальное имя ({@link #name}), позволяющее различать
 * несколько дисплеев в системе.
 *
 * <p><b>Роль в паттерне Observer:</b><br>
 * Это <i>ConcreteObserver</i> — конкретный наблюдатель, который <b>безусловно</b>
 * реагирует на каждое уведомление, отображая полученное значение температуры.
 * В отличие от {@link Alarm}, не содержит условной логики фильтрации.
 *
 * <p><b>Пример вывода:</b>
 * <pre>
 *     Дисплей 1 отображает температуру: 25.0°C
 * </pre>
 *
 * @see Observer
 * @see Subject
 * @see TemperatureSensor
 * @see Alarm
 */
class Display implements Observer {

    /** Имя (идентификатор) дисплея, используемое при выводе сообщений. */
    private String name;

    /**
     * Создаёт дисплей с указанным именем.
     *
     * @param name имя дисплея (например, {@code "1"} или {@code "2"}),
     *             отображаемое в сообщениях на консоли
     */
    public Display(String name){
        this.name = name;
    }

    /**
     * Получает уведомление об изменении температуры и выводит его на консоль.
     *
     * <p>Формат вывода:
     * <pre>
     *     Дисплей {name} отображает температуру: {temperature}°C
     * </pre>
     *
     * @param temperature текущая температура в градусах Цельсия,
     *                    полученная от {@link TemperatureSensor}
     */
    @Override
    public void update(float temperature){
        System.out.println("Дисплей " + name + " отображает температуру: " + temperature + "°C");
    }
}

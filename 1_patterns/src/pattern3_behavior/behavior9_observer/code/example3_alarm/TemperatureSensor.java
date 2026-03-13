package pattern3_behavior.behavior9_observer.code.example3_alarm;

import java.util.ArrayList;
import java.util.List;

/**
 * Конкретный издатель (ConcreteSubject) — датчик температуры.
 *
 * <p>Реализует интерфейс {@link Subject} и хранит текущее значение температуры.
 * При каждом изменении температуры через {@link #setTemperature(float)} автоматически
 * оповещает всех зарегистрированных наблюдателей ({@link Observer}), используя
 * паттерн <b>«изменение состояния → уведомление»</b> (state-change-then-notify).
 *
 * <p><b>Роль в паттерне Observer:</b><br>
 * Это <i>ConcreteSubject</i> — конкретный издатель, который:
 * <ol>
 *   <li>Хранит список подписчиков в коллекции {@code observers}</li>
 *   <li>Предоставляет методы подписки/отписки ({@link #addObserver(Observer)},
 *       {@link #removeObserver(Observer)})</li>
 *   <li>Уведомляет подписчиков при изменении температуры ({@link #notifyObservers()})</li>
 * </ol>
 *
 * <p><b>Пример использования:</b>
 * <pre>
 *     TemperatureSensor sensor = new TemperatureSensor();
 *     sensor.addObserver(new Display("1"));
 *     sensor.addObserver(new Alarm());
 *     sensor.setTemperature(35.0f); // уведомит всех наблюдателей
 * </pre>
 *
 * @see Subject
 * @see Observer
 * @see Display
 * @see Alarm
 */
class TemperatureSensor implements Subject {

    /** Текущее значение температуры, измеренное датчиком. */
    private float temperature;

    /** Список зарегистрированных наблюдателей. */
    private List<Observer> observers;

    /**
     * Создаёт новый датчик температуры с пустым списком наблюдателей.
     *
     * <p>Начальное значение температуры равно {@code 0.0f} (значение по умолчанию
     * для типа {@code float}).
     */
    public TemperatureSensor(){
        observers = new ArrayList<>();
    }

    /**
     * Устанавливает новое значение температуры и оповещает всех наблюдателей.
     *
     * <p>Реализует паттерн <b>state-change-then-notify</b>:
     * <ol>
     *   <li>Сохраняет новое значение температуры в поле {@code temperature}</li>
     *   <li>Вызывает {@link #notifyObservers()} для рассылки уведомлений</li>
     * </ol>
     *
     * @param temperature новое значение температуры в градусах Цельсия
     */
    public void setTemperature(float temperature){
        this.temperature = temperature;
        notifyObservers();
    }

    /**
     * Возвращает текущее значение температуры.
     *
     * @return текущая температура в градусах Цельсия
     */
    public float getTemperature(){
        return temperature;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Добавляет наблюдателя в конец внутреннего списка {@code observers}.
     *
     * @param observer наблюдатель для регистрации
     */
    @Override
    public void addObserver(Observer observer){
        observers.add(observer);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Удаляет первое вхождение указанного наблюдателя из списка {@code observers}.
     *
     * @param observer наблюдатель для удаления
     */
    @Override
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Итерирует по списку {@code observers} и вызывает
     * {@link Observer#update(float)} у каждого наблюдателя, передавая
     * текущее значение {@link #temperature}.
     */
    @Override
    public void notifyObservers(){
        for(Observer observer : observers){
            observer.update(temperature);
        }
    }
}

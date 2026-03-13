package pattern3_behavior.behavior4_state.code.example3_traffic_light;

/**
 * Интерфейс состояния светофора (State).
 *
 * <p>Определяет контракт для всех конкретных состояний светофора.
 * Каждое состояние знает, как переключить светофор на следующий сигнал
 * и какой цвет оно представляет.
 *
 * <p>В паттерне Состояние данный интерфейс играет роль <b>State</b> —
 * объявляет методы, которые контекст ({@link TrafficLight}) делегирует
 * текущему объекту-состоянию.
 *
 * <p><b>Таблица переходов:</b>
 * <ul>
 *   <li>Красный ({@link RedLight}) → Зелёный ({@link GreenLight})</li>
 *   <li>Зелёный ({@link GreenLight}) → Жёлтый ({@link YellowLight})</li>
 *   <li>Жёлтый ({@link YellowLight}) → Красный ({@link RedLight})</li>
 * </ul>
 */
public interface TrafficLightState {

    /**
     * Переключает светофор на следующий сигнал.
     *
     * <p>Каждое конкретное состояние самостоятельно решает, какое
     * состояние станет следующим, и вызывает
     * {@link TrafficLight#setState(TrafficLightState)} для смены.
     *
     * @param trafficLight контекст-светофор, состояние которого нужно изменить
     */
    void switchLight(TrafficLight trafficLight);

    /**
     * Возвращает название цвета текущего сигнала.
     *
     * @return строковое представление цвета (например, "Красный", "Зелёный", "Жёлтый")
     */
    String getColor();
}

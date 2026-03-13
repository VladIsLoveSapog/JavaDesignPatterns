package pattern3_behavior.behavior4_state.code.example3_traffic_light;

/**
 * Состояние «Красный свет» (ConcreteState).
 *
 * <p>Представляет красный сигнал светофора — движение запрещено.
 * При переключении светофор переходит в состояние {@link GreenLight}.
 *
 * <p>В реальном светофоре красный свет означает полную остановку.
 * Переход напрямую на жёлтый невозможен — только на зелёный,
 * что отражает циклическую природу переходов: Красный → Зелёный → Жёлтый → Красный.
 */
public class RedLight implements TrafficLightState {

    /**
     * Переключает светофор с красного на зелёный.
     *
     * @param trafficLight контекст-светофор, состояние которого нужно изменить
     */
    @Override
    public void switchLight(TrafficLight trafficLight) {
        System.out.println("Красный → Зелёный: движение разрешено.");
        trafficLight.setState(new GreenLight());    // переход к зелёному сигналу
    }

    /**
     * Возвращает название цвета — «Красный».
     *
     * @return строка "Красный"
     */
    @Override
    public String getColor() {
        return "Красный";
    }
}

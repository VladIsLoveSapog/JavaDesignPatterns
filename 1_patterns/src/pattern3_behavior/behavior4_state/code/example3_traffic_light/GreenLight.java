package pattern3_behavior.behavior4_state.code.example3_traffic_light;

/**
 * Состояние «Зелёный свет» (ConcreteState).
 *
 * <p>Представляет зелёный сигнал светофора — движение разрешено.
 * При переключении светофор переходит в состояние {@link YellowLight}.
 *
 * <p>Зелёный свет не может перейти напрямую в красный — сначала
 * загорается жёлтый, предупреждая участников движения о скорой смене сигнала.
 */
public class GreenLight implements TrafficLightState {

    /**
     * Переключает светофор с зелёного на жёлтый.
     *
     * @param trafficLight контекст-светофор, состояние которого нужно изменить
     */
    @Override
    public void switchLight(TrafficLight trafficLight) {
        System.out.println("Зелёный → Жёлтый: внимание, скоро смена сигнала.");
        trafficLight.setState(new YellowLight());   // переход к жёлтому сигналу
    }

    /**
     * Возвращает название цвета — «Зелёный».
     *
     * @return строка "Зелёный"
     */
    @Override
    public String getColor() {
        return "Зелёный";
    }
}

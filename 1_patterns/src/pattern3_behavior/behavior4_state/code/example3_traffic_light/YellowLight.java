package pattern3_behavior.behavior4_state.code.example3_traffic_light;

/**
 * Состояние «Жёлтый свет» (ConcreteState).
 *
 * <p>Представляет жёлтый (предупреждающий) сигнал светофора.
 * При переключении светофор переходит в состояние {@link RedLight},
 * замыкая цикл: Красный → Зелёный → Жёлтый → Красный.
 *
 * <p>Жёлтый свет является промежуточным состоянием, предупреждающим
 * о скорой смене на красный. Переход на зелёный из жёлтого невозможен.
 */
public class YellowLight implements TrafficLightState {

    /**
     * Переключает светофор с жёлтого на красный.
     *
     * @param trafficLight контекст-светофор, состояние которого нужно изменить
     */
    @Override
    public void switchLight(TrafficLight trafficLight) {
        System.out.println("Жёлтый → Красный: движение запрещено.");
        trafficLight.setState(new RedLight());   // переход к красному сигналу
    }

    /**
     * Возвращает название цвета — «Жёлтый».
     *
     * @return строка "Жёлтый"
     */
    @Override
    public String getColor() {
        return "Жёлтый";
    }
}

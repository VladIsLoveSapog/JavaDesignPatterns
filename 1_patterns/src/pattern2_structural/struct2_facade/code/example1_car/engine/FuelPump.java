package pattern2_structural.struct2_facade.code.example1_car.engine;

/**
 * Топливный насос.
 * <p>
 * Нагнетает топливо из бака в инжектор. Используется
 * {@link FuelInjector инжектором} при впрыске топлива.
 * <p>
 * Является частью подсистемы двигателя, скрытой за фасадом.
 */
public class FuelPump {

    /**
     * Нагнетает топливо.
     */
    public void pump() {
        System.out.println("FuelPump pump");
    }
}

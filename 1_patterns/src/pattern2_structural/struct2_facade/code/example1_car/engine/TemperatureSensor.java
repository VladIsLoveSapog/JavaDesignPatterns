package pattern2_structural.struct2_facade.code.example1_car.engine;

/**
 * Датчик температуры двигателя.
 * <p>
 * Измеряет текущую температуру двигателя. Показания используются
 * {@link CoolingController контроллером охлаждения} для управления
 * температурным режимом.
 * <p>
 * Является частью подсистемы двигателя, скрытой за фасадом.
 */
public class TemperatureSensor {

    /**
     * Снимает текущие показания температуры двигателя.
     */
    public void getTemperature() {
        System.out.println("Temperature Sensor get temperature");
    }
}

package pattern2_structural.struct2_facade.code.example1_car.engine;

/**
 * Датчик расхода воздуха (Mass Air Flow sensor).
 * <p>
 * Измеряет объём воздуха, поступающего в двигатель. Показания используются
 * {@link AirFlowController контроллером воздушного потока} для корректного
 * управления подачей воздуха.
 * <p>
 * Является частью подсистемы двигателя, скрытой за фасадом.
 */
public class AirFlowMeter {

    /**
     * Снимает текущие показания расхода воздуха.
     */
    public void getMeasurement() {
        System.out.println("AirFlowMeter get measurement");
    }
}

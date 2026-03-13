package pattern2_structural.struct2_facade.code.example1_car.engine;

/**
 * Контроллер воздушного потока двигателя.
 * <p>
 * Управляет забором воздуха, необходимого для процесса сгорания топлива.
 * Использует {@link AirFlowMeter} для измерения объёма поступающего воздуха.
 * <p>
 * Является частью подсистемы двигателя, скрытой за фасадом
 * {@link pattern2_structural.struct2_facade.code.example1_car.CarEngineFacade}.
 */
public class AirFlowController {

    /** Датчик расхода воздуха для измерения объёма входящего потока. */
    private AirFlowMeter airFlowMeter = new AirFlowMeter();

    /**
     * Выполняет забор воздуха и снимает показания с датчика расхода.
     */
    public void takeAir() {
        System.out.println("AirFlowController take air.");
        airFlowMeter.getMeasurement();
    }

    /**
     * Отключает контроллер воздушного потока.
     */
    public void off() {
        System.out.println("AirFlowController off.");
    }
}

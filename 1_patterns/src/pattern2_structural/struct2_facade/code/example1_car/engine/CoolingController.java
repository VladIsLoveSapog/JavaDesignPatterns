package pattern2_structural.struct2_facade.code.example1_car.engine;

/**
 * Контроллер системы охлаждения двигателя.
 * <p>
 * Управляет температурным режимом двигателя, используя {@link Radiator} для
 * отвода тепла и {@link TemperatureSensor} для мониторинга температуры.
 * <p>
 * Является частью подсистемы двигателя, скрытой за фасадом.
 */
public class CoolingController {

    /** Радиатор для отвода тепла. */
    private Radiator radiator = new Radiator();

    /** Датчик температуры для мониторинга. */
    private TemperatureSensor temperatureSensor = new TemperatureSensor();

    /**
     * Устанавливает верхний предел допустимой температуры и снимает текущие показания.
     *
     * @param temp верхний предел температуры (°C)
     */
    public void setTemperatureUpperLimit(Integer temp) {
        System.out.println("Cooling Controller set temperature upper limit " + temp);
        temperatureSensor.getTemperature();
    }

    /**
     * Запускает систему охлаждения и включает радиатор.
     */
    public void run() {
        System.out.println("Cooling Controller run.");
        radiator.on();
    }

    /**
     * Охлаждает двигатель до указанной температуры.
     *
     * @param cool целевая температура охлаждения (°C)
     */
    public void cool(Integer cool) {
        System.out.println("Cooling Controller cool" + cool);
    }

    /**
     * Останавливает систему охлаждения.
     */
    public void stop() {
        System.out.println("Cooling Controller stop.");
    }
}

package pattern2_structural.struct2_facade.code.example1_car;

import pattern2_structural.struct2_facade.code.example1_car.engine.*;

/**
 * Фасад для управления двигателем автомобиля.
 * <p>
 * Скрывает сложную последовательность действий при запуске и останове двигателя,
 * предоставляя клиенту простой интерфейс из двух методов: {@link #startEngine()} и {@link #stopEngine()}.
 * <p>
 * Внутри фасад координирует работу пяти компонентов подсистемы:
 * {@link FuelInjector}, {@link AirFlowController}, {@link Starter},
 * {@link CoolingController} и {@link CatalyticConverter}.
 */
public class CarEngineFacade {

    /** Температура по умолчанию для верхнего предела системы охлаждения (°C). */
    private static int DEFAULT_COOLING_TEMP = 90;

    /** Максимальная допустимая температура при останове двигателя (°C). */
    private static int MAX_ALLOWED_TEMP = 50;

    /** Топливный инжектор — управляет подачей топлива в двигатель. */
    private FuelInjector fuelInjector = new FuelInjector();

    /** Контроллер воздушного потока — управляет забором воздуха. */
    private AirFlowController airFlowController = new AirFlowController();

    /** Стартер — отвечает за запуск двигателя. */
    private Starter starter = new Starter();

    /** Контроллер охлаждения — управляет температурным режимом двигателя. */
    private CoolingController coolingController = new CoolingController();

    /** Каталитический конвертер — очищает выхлопные газы. */
    private CatalyticConverter catalyticConverter = new CatalyticConverter();

    /**
     * Запускает двигатель автомобиля.
     * <p>
     * Выполняет правильную последовательность действий:
     * включение инжектора, забор воздуха, впрыск топлива,
     * запуск стартера, настройка охлаждения, включение катализатора.
     */
    public void startEngine() {
        fuelInjector.on();
        airFlowController.takeAir();
        fuelInjector.inject();
        starter.start();
        coolingController.setTemperatureUpperLimit(DEFAULT_COOLING_TEMP);
        coolingController.run();
        catalyticConverter.on();
    }

    /**
     * Останавливает двигатель автомобиля.
     * <p>
     * Выполняет правильную последовательность действий:
     * отключение инжектора, отключение катализатора,
     * охлаждение до безопасной температуры, останов системы охлаждения,
     * отключение воздушного потока.
     */
    public void stopEngine() {
        fuelInjector.off();
        catalyticConverter.off();
        coolingController.cool(MAX_ALLOWED_TEMP);
        coolingController.stop();
        airFlowController.off();
    }
}

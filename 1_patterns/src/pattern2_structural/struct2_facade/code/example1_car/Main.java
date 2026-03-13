package pattern2_structural.struct2_facade.code.example1_car;

import pattern2_structural.struct2_facade.code.example1_car.engine.*;

/**
 * Демонстрация паттерна Фасад на примере запуска двигателя автомобиля.
 * <p>
 * Эмулируем работу двигателя. Все компоненты двигателя находятся в пакете
 * {@code engine} и являются частью сложной подсистемы.
 * <p>
 * <b>Часть 1 (без фасада):</b> клиент вынужден самостоятельно знать и соблюдать
 * правильную последовательность вызовов компонентов подсистемы.
 * <p>
 * <b>Часть 2 (с фасадом):</b> клиент использует {@link CarEngineFacade},
 * который инкапсулирует всю сложность и предоставляет простые методы
 * {@code startEngine()} / {@code stopEngine()}.
 */
public class Main {
    public static void main(String[] args) {
        int DEFAULT_COOLING_TEMP = 90;
        int MAX_ALLOWED_TEMP = 50;
        FuelInjector fuelInjector = new FuelInjector();
        AirFlowController airFlowController = new AirFlowController();
        Starter starter = new Starter();
        CoolingController coolingController = new CoolingController();
        CatalyticConverter catalyticConverter = new CatalyticConverter();

        // Часть 1: без фасада — клиент знает правильную последовательность действий для включения двигателя
        airFlowController.takeAir();
        fuelInjector.on();
        fuelInjector.inject();
        starter.start();
        coolingController.setTemperatureUpperLimit(DEFAULT_COOLING_TEMP);
        coolingController.run();
        catalyticConverter.on();

        // Правильная последовательность действий для выключения двигателя
        fuelInjector.off();
        catalyticConverter.off();
        coolingController.cool(MAX_ALLOWED_TEMP);
        coolingController.stop();
        airFlowController.off();

        System.out.println("-------------------------");

        // Часть 2: с фасадом — пользователю не нужно знать внутреннюю логику двигателя.
        // Он просто нажимает кнопку «вкл/выкл».
        CarEngineFacade carEngineFacade = new CarEngineFacade();

        // Нажали «вкл»
        carEngineFacade.startEngine();

        // Нажали «выкл»
        carEngineFacade.stopEngine();

        // Если добавятся новые компоненты или изменится последовательность —
        // у пользователя по-прежнему останутся только кнопки вкл/выкл.
    }
}

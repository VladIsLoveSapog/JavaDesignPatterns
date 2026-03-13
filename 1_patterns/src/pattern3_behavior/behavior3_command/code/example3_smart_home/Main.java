package pattern3_behavior.behavior3_command.code.example3_smart_home;

/**
 * Демонстрация паттерна Команда на примере умного дома.
 *
 * <p>Классический пример GoF: пульт ({@link RemoteControl}) управляет
 * разнородными устройствами ({@link Light}, {@link AirConditioner})
 * через единый интерфейс {@link Command}. Все операции поддерживают отмену.
 *
 * <p><b>Учебные моменты:</b>
 * <ul>
 *   <li>Несколько Receiver-ов — один Invoker управляет всеми через Command</li>
 *   <li>{@link AirConditionerSetTempCommand} хранит {@code previousTemperature} для undo</li>
 *   <li>Пульт не знает деталей устройств — полная развязка через интерфейс</li>
 * </ul>
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Умный дом ===\n");

        // Receiver-ы — устройства
        Light livingRoomLight = new Light("Гостиная");
        Light bedroomLight = new Light("Спальня");
        AirConditioner ac = new AirConditioner("Гостиная");

        // Invoker — пульт управления
        RemoteControl remote = new RemoteControl();

        // Включаем свет в гостиной и спальне
        remote.pressButton(new LightOnCommand(livingRoomLight));
        remote.pressButton(new LightOnCommand(bedroomLight));

        // Включаем кондиционер и устанавливаем температуру
        remote.pressButton(new AirConditionerOnCommand(ac));
        remote.pressButton(new AirConditionerSetTempCommand(ac, 20));

        // Меняем температуру ещё раз
        remote.pressButton(new AirConditionerSetTempCommand(ac, 18));

        System.out.println("\n--- Отмена последних операций ---\n");

        // Отмена: температура возвращается с 18 на 20
        remote.pressUndo();

        // Отмена: температура возвращается с 20 на 24 (значение по умолчанию)
        remote.pressUndo();

        // Отмена: выключение кондиционера
        remote.pressUndo();

        // Отмена: выключение света в спальне
        remote.pressUndo();

        // Отмена: выключение света в гостиной
        remote.pressUndo();

        // Попытка отмены при пустой истории
        remote.pressUndo();
    }
}

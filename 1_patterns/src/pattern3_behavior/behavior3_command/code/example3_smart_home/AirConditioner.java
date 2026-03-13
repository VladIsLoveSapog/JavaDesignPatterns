package pattern3_behavior.behavior3_command.code.example3_smart_home;

/**
 * Кондиционер — получатель команд (роль <b>Receiver</b>).
 *
 * <p>Содержит бизнес-логику управления кондиционером: включение, выключение
 * и установка температуры. Конкретные команды ({@link AirConditionerOnCommand},
 * {@link AirConditionerOffCommand}, {@link AirConditionerSetTempCommand})
 * делегируют выполнение этому классу.
 *
 * <p><b>Зачем второй Receiver:</b> наличие нескольких получателей
 * ({@code Light} и {@code AirConditioner}) демонстрирует ключевое
 * преимущество паттерна Command — один Invoker ({@link RemoteControl})
 * управляет разнородными устройствами через единый интерфейс {@link Command}.
 */
public class AirConditioner {

    /** Местоположение (комната), в которой установлен кондиционер. */
    private String location;

    /** Текущее состояние: {@code true} — включён, {@code false} — выключен. */
    private boolean on;

    /** Текущая установленная температура (в градусах Цельсия). */
    private int temperature;

    /**
     * Создаёт кондиционер для указанного местоположения.
     * По умолчанию выключен, температура 24 °C.
     *
     * @param location название комнаты или зоны
     */
    public AirConditioner(String location) {
        this.location = location;
        this.on = false;
        this.temperature = 24;
    }

    /** Включает кондиционер. */
    public void turnOn() {
        on = true;
        System.out.println(location + ": кондиционер включён (температура " + temperature + " °C)");
    }

    /** Выключает кондиционер. */
    public void turnOff() {
        on = false;
        System.out.println(location + ": кондиционер выключен");
    }

    /**
     * Устанавливает целевую температуру.
     *
     * @param temperature новая температура в градусах Цельсия
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println(location + ": температура установлена на " + temperature + " °C");
    }

    /**
     * Возвращает текущую установленную температуру.
     *
     * @return температура в градусах Цельсия
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * Проверяет, включён ли кондиционер.
     *
     * @return {@code true}, если кондиционер включён
     */
    public boolean isOn() {
        return on;
    }
}

package pattern3_behavior.behavior3_command.code.example3_smart_home;

/**
 * Свет — получатель команд (роль <b>Receiver</b>).
 *
 * <p>Содержит бизнес-логику управления освещением: включение и выключение.
 * Конкретные команды ({@link LightOnCommand}, {@link LightOffCommand})
 * делегируют выполнение этому классу.
 *
 * <p><b>Когда использовать:</b> когда логика управления устройством
 * должна быть отделена от механизма вызова (пульт, расписание, голосовой
 * помощник) через паттерн Command.
 */
public class Light {

    /** Местоположение (комната), в которой находится свет. */
    private String location;

    /** Текущее состояние: {@code true} — включён, {@code false} — выключен. */
    private boolean on;

    /**
     * Создаёт свет для указанного местоположения (по умолчанию выключен).
     *
     * @param location название комнаты или зоны
     */
    public Light(String location) {
        this.location = location;
        this.on = false;
    }

    /** Включает свет. */
    public void turnOn() {
        on = true;
        System.out.println(location + ": свет включён");
    }

    /** Выключает свет. */
    public void turnOff() {
        on = false;
        System.out.println(location + ": свет выключен");
    }

    /**
     * Проверяет, включён ли свет.
     *
     * @return {@code true}, если свет включён
     */
    public boolean isOn() {
        return on;
    }
}

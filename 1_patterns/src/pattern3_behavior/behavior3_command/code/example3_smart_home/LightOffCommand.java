package pattern3_behavior.behavior3_command.code.example3_smart_home;

/**
 * Команда выключения света (роль <b>ConcreteCommand</b>).
 *
 * <p>Инкапсулирует вызов {@link Light#turnOff()} в объект-команду.
 * Отмена ({@link #undo()}) включает свет обратно.
 *
 * <p><b>Когда использовать:</b> когда выключение света должно быть
 * представлено как объект — для привязки к кнопке пульта
 * ({@link RemoteControl}) или поддержки отмены.
 */
public class LightOffCommand implements Command {

    /** Получатель — свет, который будет выключен. */
    private Light light;

    /**
     * Создаёт команду выключения для указанного света.
     *
     * @param light получатель команды
     */
    public LightOffCommand(Light light) {
        this.light = light;
    }

    /** Выключает свет. */
    @Override
    public void execute() {
        light.turnOff();
    }

    /** Отменяет выключение — включает свет обратно. */
    @Override
    public void undo() {
        light.turnOn();
    }
}

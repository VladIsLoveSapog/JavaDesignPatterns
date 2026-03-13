package pattern3_behavior.behavior3_command.code.example3_smart_home;

/**
 * Команда включения света (роль <b>ConcreteCommand</b>).
 *
 * <p>Инкапсулирует вызов {@link Light#turnOn()} в объект-команду.
 * Отмена ({@link #undo()}) выключает свет.
 *
 * <p><b>Когда использовать:</b> когда включение света должно быть
 * представлено как объект — для постановки в очередь, привязки к кнопке
 * пульта ({@link RemoteControl}) или поддержки отмены.
 */
public class LightOnCommand implements Command {

    /** Получатель — свет, который будет включён. */
    private Light light;

    /**
     * Создаёт команду включения для указанного света.
     *
     * @param light получатель команды
     */
    public LightOnCommand(Light light) {
        this.light = light;
    }

    /** Включает свет. */
    @Override
    public void execute() {
        light.turnOn();
    }

    /** Отменяет включение — выключает свет. */
    @Override
    public void undo() {
        light.turnOff();
    }
}

package pattern3_behavior.behavior3_command.code.example3_smart_home;

/**
 * Команда выключения кондиционера (роль <b>ConcreteCommand</b>).
 *
 * <p>Инкапсулирует вызов {@link AirConditioner#turnOff()} в объект-команду.
 * Отмена ({@link #undo()}) включает кондиционер обратно.
 *
 * <p><b>Когда использовать:</b> когда выключение кондиционера должно быть
 * представлено как объект для привязки к кнопке пульта
 * ({@link RemoteControl}) или поддержки отмены.
 */
public class AirConditionerOffCommand implements Command {

    /** Получатель — кондиционер, который будет выключен. */
    private AirConditioner airConditioner;

    /**
     * Создаёт команду выключения для указанного кондиционера.
     *
     * @param airConditioner получатель команды
     */
    public AirConditionerOffCommand(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    /** Выключает кондиционер. */
    @Override
    public void execute() {
        airConditioner.turnOff();
    }

    /** Отменяет выключение — включает кондиционер обратно. */
    @Override
    public void undo() {
        airConditioner.turnOn();
    }
}

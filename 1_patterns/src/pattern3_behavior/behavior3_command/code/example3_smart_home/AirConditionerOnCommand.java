package pattern3_behavior.behavior3_command.code.example3_smart_home;

/**
 * Команда включения кондиционера (роль <b>ConcreteCommand</b>).
 *
 * <p>Инкапсулирует вызов {@link AirConditioner#turnOn()} в объект-команду.
 * Отмена ({@link #undo()}) выключает кондиционер.
 *
 * <p><b>Когда использовать:</b> когда включение кондиционера должно быть
 * представлено как объект для привязки к кнопке пульта
 * ({@link RemoteControl}) или поддержки отмены.
 */
public class AirConditionerOnCommand implements Command {

    /** Получатель — кондиционер, который будет включён. */
    private AirConditioner airConditioner;

    /**
     * Создаёт команду включения для указанного кондиционера.
     *
     * @param airConditioner получатель команды
     */
    public AirConditionerOnCommand(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    /** Включает кондиционер. */
    @Override
    public void execute() {
        airConditioner.turnOn();
    }

    /** Отменяет включение — выключает кондиционер. */
    @Override
    public void undo() {
        airConditioner.turnOff();
    }
}

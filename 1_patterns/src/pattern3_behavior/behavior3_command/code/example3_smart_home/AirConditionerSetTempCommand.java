package pattern3_behavior.behavior3_command.code.example3_smart_home;

/**
 * Команда установки температуры кондиционера (роль <b>ConcreteCommand</b>).
 *
 * <p>Инкапсулирует вызов {@link AirConditioner#setTemperature(int)} в объект-команду.
 *
 * <p><b>Ключевой учебный момент — сохранение предыдущего состояния для undo:</b>
 * перед установкой новой температуры команда запоминает текущую температуру
 * в поле {@code previousTemperature}. При отмене ({@link #undo()}) температура
 * возвращается к сохранённому значению. Это аналог снимка (Memento) на уровне
 * одного поля — тот же принцип, что и {@code removedText} в
 * {@link pattern3_behavior.behavior3_command.code.example2_text_editor.DeleteCommand}.
 *
 * <p><b>Когда использовать:</b> когда команда изменяет числовое или иное
 * состояние получателя и должна уметь восстановить предыдущее значение.
 */
public class AirConditionerSetTempCommand implements Command {

    /** Получатель — кондиционер, на котором устанавливается температура. */
    private AirConditioner airConditioner;

    /** Новая целевая температура. */
    private int newTemperature;

    /**
     * Температура, которая была установлена до выполнения команды.
     * Сохраняется в {@link #execute()} для возможности отмены.
     */
    private int previousTemperature;

    /**
     * Создаёт команду установки температуры.
     *
     * @param airConditioner получатель команды
     * @param newTemperature новая температура в градусах Цельсия
     */
    public AirConditionerSetTempCommand(AirConditioner airConditioner, int newTemperature) {
        this.airConditioner = airConditioner;
        this.newTemperature = newTemperature;
    }

    /**
     * Сохраняет текущую температуру и устанавливает новую.
     */
    @Override
    public void execute() {
        previousTemperature = airConditioner.getTemperature();
        airConditioner.setTemperature(newTemperature);
    }

    /**
     * Отменяет установку температуры, возвращая предыдущее значение.
     */
    @Override
    public void undo() {
        airConditioner.setTemperature(previousTemperature);
    }
}

package pattern3_behavior.behavior3_command.code.example3_smart_home;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Пульт управления умным домом (роль <b>Invoker</b>).
 *
 * <p>Принимает любые объекты-команды ({@link Command}), выполняет их
 * и сохраняет в стеке для поддержки отмены. Пульт не знает, какое именно
 * устройство стоит за командой — он работает через общий интерфейс
 * {@link Command}, что позволяет управлять разнородными устройствами
 * ({@link Light}, {@link AirConditioner}) единообразно.
 *
 * <p><b>Когда использовать:</b> когда нужен единый объект-инициатор,
 * управляющий выполнением и отменой команд для множества устройств.
 */
public class RemoteControl {

    /** Стек выполненных команд для поддержки undo (LIFO). */
    private Deque<Command> history = new ArrayDeque<>();

    /**
     * Выполняет команду и добавляет её в историю.
     *
     * @param command команда для выполнения
     */
    public void pressButton(Command command) {
        command.execute();
        history.push(command);
    }

    /**
     * Отменяет последнюю выполненную команду.
     * Если история пуста, выводит сообщение в консоль.
     */
    public void pressUndo() {
        if (!history.isEmpty()) {
            Command command = history.pop();
            command.undo();
        } else {
            System.out.println("Нечего отменять.");
        }
    }
}

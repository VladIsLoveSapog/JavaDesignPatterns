package pattern3_behavior.behavior4_state.code.example2_game;

/**
 * Конкретное состояние (ConcreteState) — игрок бежит.
 *
 * <p>Реализует интерфейс {@link State} и описывает поведение персонажа,
 * когда он находится в движении (бежит). В этом состоянии игрок может
 * прыгнуть на бегу или остановиться, но не может «начать бежать повторно».
 *
 * <p><b>Допустимые переходы из этого состояния:</b>
 * <ul>
 *   <li>{@code pressJump} — игрок прыгает на бегу → переход в {@link JumpingState}</li>
 *   <li>{@code pressStop} — игрок останавливается → переход в {@link StandingState}</li>
 * </ul>
 *
 * <p><b>Недопустимые действия:</b>
 * <ul>
 *   <li>{@code pressRun} — игрок уже бежит, переход не выполняется</li>
 * </ul>
 *
 * <p><b>Роль в паттерне State:</b><br>
 * Это один из классов <i>ConcreteState</i>. Инкапсулирует поведение,
 * характерное для состояния бега, и управляет переходами в другие состояния
 * контекста ({@link Player}).
 */
public class RunningState implements State {

    /**
     * Обрабатывает нажатие кнопки «бежать» в состоянии бега.
     *
     * <p>Игрок уже бежит — действие не имеет эффекта, переход не выполняется.
     * Выводится информационное сообщение.
     *
     * @param player контекст (игрок), состояние которого не изменяется
     */
    @Override
    public void pressRun(Player player) {
        // Игрок уже бежит — повторный запуск бега невозможен
        System.out.println("Player is already running.");
    }

    /**
     * Обрабатывает нажатие кнопки «прыгнуть» в состоянии бега.
     *
     * <p>Игрок прыгает на бегу — допустимый переход.
     * Состояние меняется на {@link JumpingState}.
     *
     * @param player контекст (игрок), которому устанавливается новое состояние
     */
    @Override
    public void pressJump(Player player) {
        System.out.println("Player jumps while running.");
        // Переход: бежит → прыгает
        player.setState(new JumpingState());
    }

    /**
     * Обрабатывает нажатие кнопки «остановиться» в состоянии бега.
     *
     * <p>Игрок прекращает бег и останавливается — допустимый переход.
     * Состояние меняется на {@link StandingState}.
     *
     * @param player контекст (игрок), которому устанавливается новое состояние
     */
    @Override
    public void pressStop(Player player) {
        System.out.println("Player stops running.");
        // Переход: бежит → стоит
        player.setState(new StandingState());
    }
}

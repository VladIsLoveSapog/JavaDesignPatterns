package pattern3_behavior.behavior4_state.code.example2_game;

/**
 * Демонстрация паттерна «Состояние» (State) на примере игрового персонажа.
 *
 * <p>Показывает полный цикл переходов между состояниями игрока:
 * <ol>
 *   <li>Начальное состояние — {@link StandingState} (стоит)</li>
 *   <li>Нажатие «бежать» → переход в {@link RunningState} (бежит)</li>
 *   <li>Нажатие «прыгнуть» → переход в {@link JumpingState} (прыгает)</li>
 *   <li>Нажатие «бежать» → действие заблокировано (нельзя бежать в воздухе)</li>
 *   <li>Нажатие «стоп» → приземление, переход в {@link StandingState} (стоит)</li>
 *   <li>Нажатие «стоп» → действие не имеет эффекта (уже стоит)</li>
 * </ol>
 *
 * <p><b>Ожидаемый вывод:</b>
 * <pre>
 * Initial State: StandingState
 * Player starts running.
 * Current State: RunningState
 * Player jumps while running.
 * Current State: JumpingState
 * Player cannot run while jumping.
 * Current State: JumpingState
 * Player lands and stands.
 * Current State: StandingState
 * Player is already standing.
 * </pre>
 *
 * <p><b>Ключевой вывод:</b> паттерн «Состояние» позволяет избавиться от
 * громоздких условных конструкций (if/switch) в контексте ({@link Player}).
 * Каждое состояние самостоятельно определяет допустимые действия и переходы,
 * что делает код расширяемым и легко поддерживаемым.
 */
public class Main {

    /**
     * Точка входа — запускает демонстрационный сценарий переходов состояний.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        // Создаём игрока — начальное состояние: StandingState (стоит)
        Player player = new Player();
        System.out.println("Initial State: " + player.getStateName());

        // Действие: бежать (StandingState → RunningState)
        player.pressRun();
        System.out.println("Current State: " + player.getStateName());

        // Действие: прыгнуть на бегу (RunningState → JumpingState)
        player.pressJump();
        System.out.println("Current State: " + player.getStateName());

        // Действие: попытка бежать в воздухе — заблокировано (остаёмся в JumpingState)
        player.pressRun();
        System.out.println("Current State: " + player.getStateName());

        // Действие: приземлиться (JumpingState → StandingState)
        player.pressStop();
        System.out.println("Current State: " + player.getStateName());

        // Действие: попытка остановиться, когда уже стоишь — без эффекта
        player.pressStop();
    }
}

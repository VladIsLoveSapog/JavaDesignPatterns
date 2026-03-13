package pattern3_behavior.behavior5_memento.code.example2_game_state;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <b>Опекун (Caretaker)</b> — менеджер сохранений игры.
 *
 * <p>Управляет историей снимков состояния {@link Game игры} через стек.
 * Опекун не имеет доступа к содержимому снимков — он лишь хранит их
 * и передаёт обратно Создателю при восстановлении.</p>
 *
 * <p>Роль в паттерне: <b>Caretaker (Опекун)</b> — объект, который запрашивает
 * снимки у Создателя, хранит их и возвращает при необходимости.</p>
 *
 * @see Game Создатель (Originator)
 * @see GameState Снимок (Memento)
 */
public class GameSaveManager {
    /** Стек сохранённых состояний игры. */
    private Deque<GameState> saveStack = new ArrayDeque<>();

    /**
     * Сохраняет текущее состояние игры.
     *
     * <p>Запрашивает снимок у {@link Game} и помещает его на вершину стека.</p>
     *
     * @param game игра, состояние которой необходимо сохранить
     */
    public void saveState(Game game) {
        saveStack.push(game.save());
    }

    /**
     * Восстанавливает последнее сохранённое состояние игры.
     *
     * <p>Извлекает снимок с вершины стека и передаёт его {@link Game}
     * для восстановления. Если история пуста — выводит сообщение.</p>
     *
     * @param game игра, состояние которой необходимо восстановить
     */
    public void restoreState(Game game) {
        if (!saveStack.isEmpty()) {
            GameState state = saveStack.pop();
            game.restore(state);
        } else {
            System.out.println("No saved states to restore.");
        }
    }
}

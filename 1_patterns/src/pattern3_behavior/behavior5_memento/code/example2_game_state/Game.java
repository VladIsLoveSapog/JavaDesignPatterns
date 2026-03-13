package pattern3_behavior.behavior5_memento.code.example2_game_state;

/**
 * <b>Создатель (Originator)</b> — игра.
 *
 * <p>Объект, чьё внутреннее состояние (позиция игрока и счёт) мы хотим сохранять
 * и восстанавливать. Создатель умеет создавать снимки своего текущего состояния
 * ({@link #save()}) и восстанавливаться из ранее сохранённого снимка
 * ({@link #restore(GameState)}).</p>
 *
 * <p>Роль в паттерне: <b>Originator (Создатель)</b> — объект, состояние которого
 * мы сохраняем и восстанавливаем.</p>
 *
 * @see GameState Снимок (Memento)
 * @see GameSaveManager Опекун (Caretaker)
 */
public class Game {
    /** Текущая позиция игрока. */
    private int playerPosition;

    /** Текущий счёт. */
    private int score;

    /**
     * Перемещает игрока на новую позицию.
     *
     * @param newPosition новая позиция игрока
     */
    public void movePlayer(int newPosition) {
        playerPosition = newPosition;
        System.out.println("Player moved to position: " + playerPosition);
    }

    /**
     * Добавляет очки к текущему счёту.
     *
     * @param points количество очков для начисления
     */
    public void addScore(int points) {
        score += points;
        System.out.println("Score increased by " + points + ". Current score: " + score);
    }

    /**
     * Возвращает текущую позицию игрока.
     *
     * @return позиция игрока
     */
    public int getPlayerPosition() {
        return playerPosition;
    }

    /**
     * Возвращает текущий счёт.
     *
     * @return счёт
     */
    public int getScore() {
        return score;
    }

    /**
     * Создаёт снимок текущего состояния игры.
     *
     * @return снимок с текущей позицией и счётом
     */
    public GameState save() {
        System.out.println("Saving game state: Position=" + playerPosition + ", Score=" + score);
        return new GameState(playerPosition, score);
    }

    /**
     * Восстанавливает состояние игры из снимка.
     *
     * @param state снимок, к которому необходимо вернуться
     */
    public void restore(GameState state) {
        playerPosition = state.getPlayerPosition();
        score = state.getScore();
        System.out.println("Game state restored: Position=" + playerPosition + ", Score=" + score);
    }
}

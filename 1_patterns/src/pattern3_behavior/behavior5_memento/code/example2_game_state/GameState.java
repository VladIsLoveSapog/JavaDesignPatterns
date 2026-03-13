package pattern3_behavior.behavior5_memento.code.example2_game_state;

/**
 * <b>Снимок (Memento)</b> состояния игры.
 *
 * <p>Хранит неизменяемую копию игровых параметров (позиция игрока и счёт)
 * на момент сохранения. Все поля объявлены как {@code final}, что делает
 * снимок полностью иммутабельным.</p>
 *
 * <p>Роль в паттерне: <b>Memento (Снимок)</b> — объект, инкапсулирующий
 * внутреннее состояние {@link Game Создателя}.</p>
 *
 * @see Game Создатель (Originator)
 * @see GameSaveManager Опекун (Caretaker)
 */
public class GameState {
    /** Позиция игрока на момент сохранения. */
    private final int playerPosition;

    /** Счёт на момент сохранения. */
    private final int score;

    /**
     * Создаёт снимок с указанными параметрами.
     *
     * @param playerPosition позиция игрока
     * @param score текущий счёт
     */
    public GameState(int playerPosition, int score) {
        this.playerPosition = playerPosition;
        this.score = score;
    }

    /**
     * Возвращает сохранённую позицию игрока.
     *
     * @return позиция игрока на момент создания снимка
     */
    public int getPlayerPosition() {
        return playerPosition;
    }

    /**
     * Возвращает сохранённый счёт.
     *
     * @return счёт на момент создания снимка
     */
    public int getScore() {
        return score;
    }
}

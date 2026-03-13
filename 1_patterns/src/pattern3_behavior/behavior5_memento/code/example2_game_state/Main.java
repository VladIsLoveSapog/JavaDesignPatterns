package pattern3_behavior.behavior5_memento.code.example2_game_state;

/**
 * Демонстрация паттерна <b>Memento (Снимок)</b> на примере сохранения состояния игры.
 *
 * <p>Сценарий:</p>
 * <ol>
 *     <li>Игрок перемещается и набирает очки — состояние сохраняется</li>
 *     <li>Игрок продолжает игру — состояние снова сохраняется</li>
 *     <li>Игрок делает неудачные действия</li>
 *     <li>Восстанавливаем последнее сохранение, затем предыдущее</li>
 *     <li>Попытка восстановления при пустой истории</li>
 * </ol>
 *
 * <p>Ожидаемый вывод:</p>
 * <pre>
 * Player moved to position: 10
 * Score increased by 50. Current score: 50
 * Saving game state: Position=10, Score=50
 * Player moved to position: 20
 * Score increased by 30. Current score: 80
 * Saving game state: Position=20, Score=80
 * Player moved to position: 30
 * Score increased by 20. Current score: 100
 * Current Game State: Position=30, Score=100
 *
 * Restoring to last saved state:
 * Game state restored: Position=20, Score=80
 * Game State after restoration: Position=20, Score=80
 *
 * Restoring to previous saved state:
 * Game state restored: Position=10, Score=50
 * Game State after restoration: Position=10, Score=50
 *
 * Attempting to restore beyond history:
 * No saved states to restore.
 * </pre>
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        GameSaveManager saveManager = new GameSaveManager();

        game.movePlayer(10);
        game.addScore(50);
        saveManager.saveState(game);

        game.movePlayer(20);
        game.addScore(30);
        saveManager.saveState(game);

        game.movePlayer(30);
        game.addScore(20);
        System.out.println("Current Game State: Position=" + game.getPlayerPosition() + ", Score=" + game.getScore());

        System.out.println("\nRestoring to last saved state:");
        saveManager.restoreState(game);
        System.out.println("Game State after restoration: Position=" + game.getPlayerPosition() + ", Score=" + game.getScore());

        System.out.println("\nRestoring to previous saved state:");
        saveManager.restoreState(game);
        System.out.println("Game State after restoration: Position=" + game.getPlayerPosition() + ", Score=" + game.getScore());

        System.out.println("\nAttempting to restore beyond history:");
        saveManager.restoreState(game);
    }
}

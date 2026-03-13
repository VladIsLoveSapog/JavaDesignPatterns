package pattern3_behavior.behavior2_strategy.code.example2_sort;

import java.util.List;
import java.util.Objects;

/**
 * Сортировщик — контекст паттерна Стратегия (роль <b>Context</b>).
 *
 * <p>Хранит ссылку на текущую стратегию сортировки ({@link SortStrategy})
 * и делегирует ей выполнение алгоритма. Контекст не знает, какой именно
 * алгоритм сортировки используется — он работает через общий интерфейс.
 *
 * <p><b>Защита от null:</b> если стратегия не установлена, метод {@link #sort(List)}
 * выбрасывает {@link IllegalStateException}, чтобы ошибка была обнаружена
 * немедленно, а не скрыта за {@code NullPointerException}.
 *
 * <p><b>Когда использовать:</b> когда нужно сортировать данные разными
 * способами, переключая алгоритм во время выполнения через
 * {@link #setStrategy(SortStrategy)}.
 */
public class Sorter {

    /**
     * Текущая стратегия сортировки.
     * Может быть заменена в любой момент через {@link #setStrategy(SortStrategy)}.
     */
    private SortStrategy strategy;

    /**
     * Устанавливает стратегию сортировки.
     *
     * @param strategy новая стратегия сортировки
     */
    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Сортирует список, делегируя работу текущей стратегии.
     *
     * @param items список строк для сортировки
     * @throws IllegalStateException если стратегия не была установлена
     */
    public void sort(List<String> items) {
        if (Objects.isNull(strategy)) throw new IllegalStateException("SortStrategy не установлена.");
        strategy.sort(items);
    }
}

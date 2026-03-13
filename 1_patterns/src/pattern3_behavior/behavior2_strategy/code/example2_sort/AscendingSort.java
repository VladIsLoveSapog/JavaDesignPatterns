package pattern3_behavior.behavior2_strategy.code.example2_sort;

import java.util.Collections;
import java.util.List;

/**
 * Сортировка по возрастанию (роль <b>ConcreteStrategy</b>).
 *
 * <p>Использует {@link Collections#sort(List)} — лексикографическую сортировку
 * в естественном порядке (natural ordering). Для строк это означает сравнение
 * посимвольно по значению Unicode.
 *
 * <p><b>Когда использовать:</b> когда требуется стандартная сортировка
 * строк по алфавиту (А → Я, a → z).
 */
public class AscendingSort implements SortStrategy {

    /**
     * Сортирует список строк в лексикографическом порядке по возрастанию.
     *
     * @param items список строк для сортировки
     */
    @Override
    public void sort(List<String> items) {
        Collections.sort(items);
        System.out.println("Сортировка по возрастанию: " + items);
    }
}

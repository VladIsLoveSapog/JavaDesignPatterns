package pattern3_behavior.behavior2_strategy.code.example2_sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Сортировка по убыванию (роль <b>ConcreteStrategy</b>).
 *
 * <p>Использует {@link Collections#sort(List, Comparator)} с компаратором
 * {@link Comparator#reverseOrder()}, который инвертирует естественный
 * (лексикографический) порядок строк.
 *
 * <p><b>Когда использовать:</b> когда нужно вывести строки в обратном
 * алфавитном порядке (Я → А, z → a).
 */
public class DescendingSort implements SortStrategy {

    /**
     * Сортирует список строк в лексикографическом порядке по убыванию.
     *
     * @param items список строк для сортировки
     */
    @Override
    public void sort(List<String> items) {
        Collections.sort(items, Comparator.reverseOrder());
        System.out.println("Сортировка по убыванию: " + items);
    }
}

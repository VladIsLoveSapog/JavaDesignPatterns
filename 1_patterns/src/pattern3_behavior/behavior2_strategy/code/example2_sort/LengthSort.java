package pattern3_behavior.behavior2_strategy.code.example2_sort;

import java.util.Comparator;
import java.util.List;

/**
 * Сортировка по длине строки (роль <b>ConcreteStrategy</b>).
 *
 * <p>Использует {@link Comparator#comparingInt(java.util.function.ToIntFunction)}
 * с функцией {@code String::length}, что даёт компаратор, сравнивающий строки
 * по количеству символов (от коротких к длинным).
 *
 * <p><b>Когда использовать:</b> когда важна длина элементов, а не их
 * лексикографический порядок (например, группировка коротких тегов
 * перед длинными описаниями).
 */
public class LengthSort implements SortStrategy {

    /**
     * Сортирует список строк по длине (от коротких к длинным).
     *
     * @param items список строк для сортировки
     */
    @Override
    public void sort(List<String> items) {
        items.sort(Comparator.comparingInt(String::length));
        System.out.println("Сортировка по длине: " + items);
    }
}

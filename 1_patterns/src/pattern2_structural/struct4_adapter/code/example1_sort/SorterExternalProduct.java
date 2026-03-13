package pattern2_structural.struct4_adapter.code.example1_sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Адаптируемый класс (Adaptee) — сторонний сервис с несовместимым интерфейсом.
 *
 * <p>Этот класс представляет собой внешнюю библиотеку, которую мы не можем изменять.
 * Ключевые ограничения:
 * <ul>
 *   <li>{@code final} — наследоваться от него нельзя, поэтому «адаптер через
 *       наследование» здесь невозможен.</li>
 *   <li>Работает с {@code List<Integer>}, а не с массивом {@code int[]},
 *       что делает его несовместимым с нашим интерфейсом {@link Sorter}.</li>
 *   <li>Мы не можем изменять его исходный код — он «чужой».</li>
 * </ul>
 *
 * <p>Именно из-за таких ситуаций и нужен паттерн Адаптер: полезный функционал
 * есть, но его интерфейс не подходит нашей системе.
 */
public final class SorterExternalProduct {

    /**
     * Сортирует список целых чисел по возрастанию.
     *
     * <p>Метод не изменяет исходный список — создаёт копию и сортирует её.
     * Именно такое поведение (работа со списком, а не с массивом) создаёт
     * несовместимость с нашим интерфейсом {@link Sorter}, который ожидает {@code int[]}.
     *
     * @param numberList список чисел для сортировки
     * @return новый отсортированный список
     */
    List<Integer> sort(List<Integer> numberList) {
        List<Integer> resList = new ArrayList<>(numberList.size());
        resList.addAll(numberList);
        Collections.sort(resList);
        return resList;
    }
}

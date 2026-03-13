package pattern2_structural.struct4_adapter.code.example1_sort;

import java.util.Arrays;

/**
 * Демонстрация работы паттерна Адаптер на примере сортировки.
 *
 * <p>Клиентский код работает исключительно с интерфейсом {@link Sorter}
 * и не подозревает, что за кулисами используется внешний сервис
 * {@link SorterExternalProduct}, работающий со списками.
 */
public class SortAdapterMain {

    public static void main(String[] args) {
        System.out.println("=== Adapter: Сортировка массива через внешний List-сервис ===\n");

        // Клиент работает только с интерфейсом Sorter — ему всё равно, что внутри
        Sorter sorter = new SorterAdapter();

        int[] numbers = {5, 3, 8, 1, 9, 2, 7};
        System.out.println("До сортировки:    " + Arrays.toString(numbers));

        // Вызываем sort() через адаптер — адаптер преобразует int[] → List,
        // передаст внешнему сервису, получит результат и вернёт int[]
        int[] sorted = sorter.sort(numbers);
        System.out.println("После сортировки: " + Arrays.toString(sorted));
    }
}

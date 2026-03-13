package pattern3_behavior.behavior6_iterator.code.example1_custom_array_list;

import java.util.Iterator;

/**
 * Демонстрация паттерна «Итератор» (Iterator) на примере собственной реализации {@link MyArrayList}.
 *
 * <p>Класс {@link MyArrayList} реализует интерфейс {@link Iterable}, что позволяет обходить
 * его элементы двумя способами:</p>
 * <ol>
 *     <li>Через цикл {@code for-each} — компилятор неявно вызывает {@code iterator()};</li>
 *     <li>Через явное получение {@link Iterator} и вызовы {@code hasNext()} / {@code next()}.</li>
 * </ol>
 *
 * <p>Сценарий демонстрации:</p>
 * <ol>
 *     <li>Создаётся {@link MyArrayList} и наполняется тремя строковыми элементами;</li>
 *     <li>Элементы выводятся через {@code for-each};</li>
 *     <li>Элементы выводятся через явный {@link Iterator}.</li>
 * </ol>
 *
 * <p>Ожидаемый вывод:</p>
 * <pre>
 *     Яблоко
 *     Банан
 *     Апельсин
 *     Из итератора: Яблоко
 *     Из итератора: Банан
 *     Из итератора: Апельсин
 * </pre>
 *
 * @see MyArrayList
 * @see Iterator
 * @see Iterable
 */
public class Main {
    public static void main(String[] args) {
        MyArrayList<String> customList = new MyArrayList<>();
        customList.add("Яблоко");
        customList.add("Банан");
        customList.add("Апельсин");

        // Используем for-each для перебора элементов, благодаря реализации Iterable
        for (String fruit : customList) {
            System.out.println(fruit);
        }

        // Используем явный итератор для перебора элементов
        Iterator<String> iterator = customList.iterator();
        while (iterator.hasNext()) {
            System.out.println("Из итератора: " + iterator.next());
        }
    }
}

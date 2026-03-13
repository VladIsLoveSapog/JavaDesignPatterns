package pattern3_behavior.behavior6_iterator.code.example2_number_range;

import java.util.Iterator;

/**
 * Демонстрация паттерна «Итератор» (Iterator) с ленивой генерацией элементов.
 *
 * <p>{@link NumberRange} реализует {@link Iterable}, но не хранит элементы в памяти —
 * каждое значение вычисляется при вызове {@code next()}. Это показывает, что паттерн
 * Итератор применим не только к коллекциям с заранее известными элементами,
 * но и к вычисляемым последовательностям.</p>
 *
 * <p>Сценарий демонстрации:</p>
 * <ol>
 *     <li>Создаётся {@link NumberRange}(1, 10, 2) — нечётные числа от 1 до 9;</li>
 *     <li>Элементы выводятся через {@code for-each};</li>
 *     <li>Создаётся {@link NumberRange}(0, 100, 10) — десятки от 0 до 90;</li>
 *     <li>Элементы выводятся через явный {@link Iterator}.</li>
 * </ol>
 *
 * <p>Ожидаемый вывод:</p>
 * <pre>
 *     1
 *     3
 *     5
 *     7
 *     9
 *     Десятки: 0
 *     Десятки: 10
 *     Десятки: 20
 *     Десятки: 30
 *     Десятки: 40
 *     Десятки: 50
 *     Десятки: 60
 *     Десятки: 70
 *     Десятки: 80
 *     Десятки: 90
 * </pre>
 *
 * @see NumberRange
 * @see Iterator
 * @see Iterable
 */
public class Main {
    public static void main(String[] args) {
        // Пример 1: нечётные числа от 1 до 9 через for-each
        NumberRange oddNumbers = new NumberRange(1, 10, 2);
        for (int number : oddNumbers) {
            System.out.println(number);
        }

        // Пример 2: десятки от 0 до 90 через явный Iterator
        NumberRange tens = new NumberRange(0, 100, 10);
        Iterator<Integer> iterator = tens.iterator();
        while (iterator.hasNext()) {
            System.out.println("Десятки: " + iterator.next());
        }
    }
}

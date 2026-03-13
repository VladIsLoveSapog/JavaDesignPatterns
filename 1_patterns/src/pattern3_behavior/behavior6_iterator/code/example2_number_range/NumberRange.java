package pattern3_behavior.behavior6_iterator.code.example2_number_range;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Диапазон целых чисел с ленивой генерацией элементов через паттерн «Итератор» (Iterator).
 *
 * <p>Роль в паттерне: <b>ConcreteAggregate</b> — реализует {@link Iterable}, но, в отличие
 * от классической коллекции, <em>не хранит</em> элементы в памяти. Каждый элемент вычисляется
 * «на лету» при вызове {@link Iterator#next()}, что делает итератор ленивым.</p>
 *
 * <p>Объект описывает арифметическую прогрессию {@code [start, start + step, start + 2*step, …)}
 * с верхней границей {@code end} (не включительно).</p>
 *
 * @see Iterator
 * @see Iterable
 * @see Main
 */
public class NumberRange implements Iterable<Integer> {
    private final int start;
    private final int end;
    private final int step;

    /**
     * Создаёт диапазон чисел от {@code start} (включительно) до {@code end} (не включительно)
     * с заданным шагом {@code step}.
     *
     * @param start начальное значение (включительно)
     * @param end   верхняя граница (не включительно)
     * @param step  шаг приращения (должен быть положительным)
     * @throws IllegalArgumentException если {@code step <= 0}
     */
    public NumberRange(int start, int end, int step) {
        if (step <= 0) throw new IllegalArgumentException("Шаг должен быть положительным: " + step);
        this.start = start;
        this.end = end;
        this.step = step;
    }

    /**
     * Возвращает итератор, который лениво генерирует числа диапазона.
     *
     * <p>Каждый вызов {@code next()} увеличивает внутренний счётчик на {@code step},
     * не храня промежуточные значения в памяти.</p>
     *
     * @return итератор по числам диапазона
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int current = start;

            @Override
            public boolean hasNext() {
                return current < end;
            }

            @Override
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException("Диапазон исчерпан");
                int value = current;
                current += step;
                return value;
            }
        };
    }
}

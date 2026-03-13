package pattern3_behavior.behavior6_iterator.code.example1_custom_array_list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Собственная реализация списка на основе массива, демонстрирующая паттерн «Итератор» (Iterator).
 *
 * <p>Роль в паттерне: <b>ConcreteAggregate</b> (конкретная коллекция) — реализует интерфейс
 * {@link Iterable}, предоставляя фабричный метод {@link #iterator()}, который возвращает
 * анонимную реализацию {@link Iterator}.</p>
 *
 * <p>Внутренне элементы хранятся в массиве фиксированного размера, который автоматически
 * увеличивается вдвое при нехватке места (аналогично {@code java.util.ArrayList}).</p>
 *
 * @param <T> тип элементов списка
 * @see Iterator
 * @see Iterable
 * @see Main
 */
public class MyArrayList<T> implements Iterable<T> {
    private T[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Создаёт пустой список с начальной ёмкостью {@value #DEFAULT_CAPACITY}.
     */
    public MyArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Добавляет элемент в конец списка. Если текущая ёмкость исчерпана,
     * внутренний массив автоматически увеличивается вдвое.
     *
     * @param element элемент, который будет добавлен
     */
    public void add(T element) {
        if (size == elements.length) resize();
        elements[size++] = element;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента (от 0 до {@code size() - 1})
     * @return элемент, расположенный по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за допустимый диапазон
     */
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона: " + index);
        return elements[index];
    }

    /**
     * Возвращает текущее количество элементов в списке.
     *
     * @return размер списка
     */
    public int size() {
        return size;
    }

    /**
     * Увеличивает ёмкость внутреннего массива вдвое.
     */
    private void resize() {
        T[] newArray = (T[]) new Object[elements.length * 2];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    /**
     * Возвращает итератор для последовательного обхода элементов списка.
     *
     * <p>Итератор реализован через анонимный класс. Метод {@code remove()} не поддерживается
     * и выбрасывает {@link UnsupportedOperationException}.</p>
     *
     * @return итератор по элементам списка
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            /**
             * Проверяет, существует ли следующий элемент.
             *
             * @return {@code true}, если ещё есть элементы для обхода
             */
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            /**
             * Возвращает текущий элемент и перемещает курсор к следующему.
             *
             * @return текущий элемент списка
             * @throws NoSuchElementException если элементов больше нет
             */
            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException("Больше нет элементов");
                return elements[currentIndex++];
            }

            /**
             * Операция удаления не поддерживается данным итератором.
             *
             * @throws UnsupportedOperationException всегда
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException("Удаление не поддерживается");
            }
        };
    }
}

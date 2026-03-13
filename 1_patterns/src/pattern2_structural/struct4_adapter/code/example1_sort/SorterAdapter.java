package pattern2_structural.struct4_adapter.code.example1_sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Адаптер (Adapter) — мост между несовместимыми интерфейсами.
 *
 * <p>Реализует целевой интерфейс {@link Sorter} (работает с {@code int[]}),
 * но внутри делегирует работу стороннему сервису {@link SorterExternalProduct}
 * (работает с {@code List<Integer>}).
 *
 * <p><b>Суть адаптации:</b> метод {@code sort(int...)} преобразует входные
 * данные из формата клиента (массив) в формат сервиса (список), вызывает
 * сервис, а затем преобразует результат обратно в формат клиента.
 *
 * <p><b>Обратите внимание:</b> адаптер использует композицию (хранит ссылку
 * на {@code SorterExternalProduct}), а не наследование. Это предпочтительный
 * подход, поскольку {@code SorterExternalProduct} объявлен как {@code final}.
 */
public class SorterAdapter implements Sorter {

    /**
     * Ссылка на адаптируемый объект (Adaptee).
     * Адаптер не содержит собственной логики сортировки — он лишь
     * преобразует данные и делегирует работу этому объекту.
     */
    private final SorterExternalProduct externalProduct = new SorterExternalProduct();

    /**
     * Сортирует массив, делегируя работу внешнему сервису.
     *
     * <p>Алгоритм работы адаптера:
     * <ol>
     *   <li>Преобразование {@code int[]} → {@code List<Integer>} (формат сервиса)</li>
     *   <li>Вызов метода сортировки внешнего сервиса</li>
     *   <li>Преобразование {@code List<Integer>} → {@code int[]} (формат клиента)</li>
     * </ol>
     *
     * @param numbers массив целых чисел для сортировки
     * @return отсортированный массив
     */
    @Override
    public int[] sort(int... numbers) {
        // Шаг 1: преобразуем массив примитивов в List<Integer> (формат внешнего API)
        List<Integer> numberList = new ArrayList<>();
        for (var number : numbers) {
            numberList.add(number);  // автоупаковка int → Integer
        }

        // Шаг 2: делегируем сортировку внешнему сервису
        var sortedList = externalProduct.sort(numberList);

        // Шаг 3: записываем результат обратно в массив примитивов (формат клиента)
        for (var i = 0; i < sortedList.size(); i++) {
            numbers[i] = sortedList.get(i);  // автораспаковка Integer → int
        }

        return numbers;
    }
}

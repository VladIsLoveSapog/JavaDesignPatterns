package pattern1_creation.create5_singleton.code.example4_double_check;

import java.util.Objects;

/**
 * Double-Checked Locking Singleton (Двойная проверка блокировки).
 *
 * <p>Оптимизированная версия потокобезопасного синглтона: блокировка
 * ({@code synchronized}) берётся только при первом создании экземпляра,
 * а не при каждом вызове {@code getInstance()}.
 *
 * <p><b>Почему две проверки:</b>
 * <ol>
 *   <li><b>Первая проверка (без блокировки)</b> — быстрый путь: если экземпляр
 *       уже создан, просто возвращаем его без синхронизации.</li>
 *   <li><b>Вторая проверка (внутри synchronized)</b> — защита от гонки: пока
 *       текущий поток ждал блокировку, другой поток мог успеть создать экземпляр.</li>
 * </ol>
 *
 * <p><b>Почему {@code volatile}:</b> без volatile возможна ситуация, когда поток
 * видит частично сконструированный объект из-за переупорядочивания инструкций
 * процессором (out-of-order execution). Volatile запрещает такие оптимизации
 * и гарантирует видимость полностью инициализированного объекта.
 *
 * <p><b>Когда использовать:</b> многопоточная среда с частыми вызовами
 * {@code getInstance()}, где важна производительность.
 */
public class DoubleCheckedLockingSingleton {

    /**
     * volatile — обеспечивает два свойства:
     * 1. Видимость (visibility): запись в это поле одним потоком
     *    немедленно видна всем остальным потокам.
     * 2. Запрет переупорядочивания (happens-before): все операции
     *    до записи в volatile-поле завершатся до того, как другой
     *    поток прочитает новое значение.
     */
    private static volatile DoubleCheckedLockingSingleton instance;

    /** Приватный конструктор — запрещает создание экземпляров извне. */
    private DoubleCheckedLockingSingleton() {}

    /**
     * Возвращает единственный экземпляр с минимальными накладными расходами.
     *
     * @return единственный экземпляр {@code DoubleCheckedLockingSingleton}
     */
    public static DoubleCheckedLockingSingleton getInstance() {
        if (Objects.isNull(instance)) {                                  // 1-я проверка (без блокировки)
            synchronized (DoubleCheckedLockingSingleton.class) {         // блокировка на уровне класса
                if (Objects.isNull(instance)) {                          // 2-я проверка (под блокировкой)
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}

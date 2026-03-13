package pattern1_creation.create5_singleton.code.example5_bill_pugh;

/**
 * Bill Pugh Singleton (Holder-идиома / Initialization-on-demand Holder).
 *
 * <p>Использует внутренний статический класс {@link SingletonHelper} для
 * хранения экземпляра. JVM загружает внутренний класс только при первом
 * обращении к нему (т.е. при вызове {@link #getInstance()}), что даёт
 * ленивую инициализацию «бесплатно».
 *
 * <p><b>Почему это потокобезопасно без synchronized:</b><br>
 * JVM гарантирует, что инициализация класса ({@code <clinit>}) выполняется
 * ровно один раз и атомарно (JLS §12.4.2). Если два потока одновременно
 * вызовут {@code getInstance()}, JVM сама выполнит синхронизацию при
 * загрузке {@code SingletonHelper} — один поток инициализирует, второй
 * дождётся завершения.
 *
 * <p><b>Когда использовать:</b> рекомендуемый подход для большинства случаев —
 * сочетает ленивую инициализацию, потокобезопасность и отсутствие
 * накладных расходов на синхронизацию.
 */
public class BillPughSingleton {

    /**
     * Приватный конструктор — запрещает создание экземпляров извне.
     */
    private BillPughSingleton() {}

    /**
     * Внутренний статический класс-холдер.
     *
     * <p>Не загружается JVM до тех пор, пока к нему не обратятся.
     * При первом обращении JVM выполнит инициализацию поля {@code INSTANCE}
     * атомарно и потокобезопасно.
     */
    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    /**
     * Возвращает единственный экземпляр.
     *
     * @return единственный экземпляр {@code BillPughSingleton}
     */
    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}

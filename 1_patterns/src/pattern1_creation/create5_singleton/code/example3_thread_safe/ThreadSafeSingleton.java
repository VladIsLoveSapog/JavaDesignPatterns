package pattern1_creation.create5_singleton.code.example3_thread_safe;

import java.util.Objects;

/**
 * Thread-Safe Singleton (Потокобезопасный синглтон с synchronized-методом).
 *
 * <p>Ключевое слово {@code synchronized} на методе {@link #getInstance()}
 * гарантирует, что только один поток в один момент времени может выполнять
 * этот метод. Это устраняет гонку потоков (race condition).
 *
 * <p><b>Цена безопасности:</b> каждый вызов {@code getInstance()} захватывает
 * монитор класса, даже когда экземпляр уже создан. В высоконагруженном
 * приложении это может стать узким местом (bottleneck).
 *
 * <p><b>Когда использовать:</b> многопоточная среда, где {@code getInstance()}
 * вызывается редко и простота важнее производительности.
 */
public class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    /** Приватный конструктор — запрещает создание экземпляров извне. */
    private ThreadSafeSingleton() {}

    /**
     * Возвращает единственный экземпляр.
     *
     * <p>{@code synchronized} блокирует доступ к методу для всех потоков,
     * кроме одного. Это решает проблему гонки, но снижает производительность.
     *
     * @return единственный экземпляр {@code ThreadSafeSingleton}
     */
    public static synchronized ThreadSafeSingleton getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}

package pattern1_creation.create5_singleton.code.example2_lazy_init;

import java.util.Objects;

/**
 * Lazy Initialization Singleton (Ленивая инициализация).
 *
 * <p>Экземпляр создаётся только при первом вызове {@link #getInstance()},
 * а не при загрузке класса. Это экономит ресурсы, если объект тяжёлый
 * и может вообще не понадобиться.
 *
 * <p><b>⚠ Внимание: НЕ потокобезопасен!</b><br>
 * Если два потока одновременно войдут в {@code getInstance()} и оба увидят
 * {@code instance == null}, каждый создаст свой экземпляр.
 * Подробнее — см. example7_thread_problem.
 *
 * <p><b>Когда использовать:</b> учебные проекты, однопоточные приложения,
 * ситуации, где многопоточность гарантированно отсутствует.
 */
public class LazySingleton {

    /**
     * Поле НЕ final — инициализируется позже, при первом вызове getInstance().
     * Поле НЕ volatile — в однопоточной среде это не нужно,
     * но именно поэтому этот вариант небезопасен при многопоточности.
     */
    private static LazySingleton instance;

    /**
     * Приватный конструктор — запрещает создание экземпляров извне.
     */
    private LazySingleton() {}

    /**
     * Возвращает единственный экземпляр, создавая его при первом обращении.
     *
     * @return единственный экземпляр {@code LazySingleton}
     */
    public static LazySingleton getInstance() {
        if (Objects.isNull(instance)) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

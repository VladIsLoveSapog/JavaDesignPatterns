package pattern3_behavior.behavior9_observer.code.example1_church;

/**
 * <b>Observer</b> (Наблюдатель / Подписчик) — интерфейс подписчика в паттерне <i>Observer</i>.
 *
 * <p>Определяет единственный метод {@link #update(String)}, который вызывается
 * издателем ({@link Observable}) при возникновении изменений.
 *
 * <p>Конкретная реализация — {@link Parishioner}.
 *
 * @see Observable
 * @see Parishioner
 */
public interface Observer {
    /**
     * Метод реакции на изменение состояния издателя.
     *
     * <p>Вызывается объектом {@link Observable} при появлении новых новостей.
     * Каждый конкретный подписчик самостоятельно решает, как обработать полученные данные.
     *
     * @param news новость, полученная от издателя.
     */
    void update(String news);
}

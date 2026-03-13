package pattern3_behavior.behavior9_observer.code.example4_stock_market;

/**
 * Конкретный наблюдатель (ConcreteObserver) — инвестор с порогом покупки.
 *
 * <p>Представляет инвестора, который следит за ценой акций и принимает
 * решение о покупке, когда цена опускается ниже заданного порога
 * ({@code buyThreshold}). Это пример <b>условной реакции</b> наблюдателя —
 * не каждое уведомление приводит к действию.
 *
 * <p><b>Роль в паттерне Observer:</b><br>
 * Это <i>ConcreteObserver</i> — конкретная реализация {@link StockObserver}.
 * Демонстрирует stateful-наблюдателя, который хранит собственное состояние
 * (имя и порог покупки) и использует его для принятия решений при получении
 * уведомлений.
 *
 * @see StockObserver
 * @see Stock
 */
class Investor implements StockObserver {

    /** Имя инвестора */
    private final String name;

    /** Пороговая цена, ниже которой инвестор готов купить акцию */
    private final double buyThreshold;

    /**
     * Создаёт инвестора с указанным именем и порогом покупки.
     *
     * @param name         имя инвестора
     * @param buyThreshold максимальная цена, при которой инвестор готов купить
     */
    public Investor(String name, double buyThreshold) {
        this.name = name;
        this.buyThreshold = buyThreshold;
    }

    /**
     * Реагирует на изменение цены акции.
     *
     * <p>Если цена опускается ниже или равна порогу {@code buyThreshold},
     * инвестор выводит сообщение о готовности к покупке. В противном случае
     * инвестор отмечает, что цена слишком высока.
     *
     * @param stockName название акции
     * @param price     текущая цена акции
     */
    @Override
    public void update(String stockName, double price) {
        if (price <= buyThreshold) {
            System.out.println(name + ": акция " + stockName + " стоит " + price
                    + " — покупаю! (порог: " + buyThreshold + ")");
        } else {
            System.out.println(name + ": акция " + stockName + " стоит " + price
                    + " — слишком дорого (порог: " + buyThreshold + ")");
        }
    }
}

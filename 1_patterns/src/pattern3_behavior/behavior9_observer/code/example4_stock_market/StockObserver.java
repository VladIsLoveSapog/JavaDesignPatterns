package pattern3_behavior.behavior9_observer.code.example4_stock_market;

/**
 * Интерфейс наблюдателя (Observer) в паттерне «Наблюдатель» для биржевой системы.
 *
 * <p>Определяет контракт для всех объектов, которые хотят получать уведомления
 * об изменении цены акций. Использует <b>push-модель</b> передачи данных —
 * название акции и новая цена передаются непосредственно в параметрах метода
 * {@link #update(String, double)}.
 *
 * <p><b>Роль в паттерне Observer:</b><br>
 * Это интерфейс <i>Observer</i> — абстракция для всех конкретных наблюдателей.
 * Реализации: {@link Investor} (инвестор с порогом покупки),
 * {@link PortfolioTracker} (трекер портфеля с историей цен),
 * {@link PriceAlertService} (уведомления при выходе за пороги).
 *
 * @see StockExchange
 * @see Stock
 * @see Investor
 * @see PortfolioTracker
 * @see PriceAlertService
 */
interface StockObserver {

    /**
     * Вызывается издателем ({@link Stock}) при изменении цены акции.
     *
     * <p>Каждый конкретный наблюдатель реализует собственную логику реакции:
     * {@link Investor} — решает о покупке, {@link PortfolioTracker} — записывает
     * цену в историю, {@link PriceAlertService} — проверяет пороговые значения.
     *
     * @param stockName название акции, цена которой изменилась
     * @param price     новая цена акции
     */
    void update(String stockName, double price);
}

package pattern3_behavior.behavior9_observer.code.example4_stock_market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Конкретный наблюдатель (ConcreteObserver) — трекер портфеля с историей цен.
 *
 * <p>Ведёт историю изменений цен для каждой акции в виде {@code Map<String, List<Double>>}.
 * При каждом уведомлении записывает новую цену в соответствующий список,
 * что позволяет анализировать динамику цен.
 *
 * <p><b>Роль в паттерне Observer:</b><br>
 * Это <i>ConcreteObserver</i> — конкретная реализация {@link StockObserver}.
 * Демонстрирует <b>stateful-наблюдателя</b>, который накапливает состояние
 * (историю цен) при каждом вызове {@link #update(String, double)}.
 * В отличие от {@link Investor}, который принимает мгновенное решение,
 * {@code PortfolioTracker} агрегирует данные для последующего анализа.
 *
 * @see StockObserver
 * @see Stock
 */
class PortfolioTracker implements StockObserver {

    /** История цен: ключ — название акции, значение — список зафиксированных цен */
    private final Map<String, List<Double>> priceHistory = new HashMap<>();

    /**
     * Записывает новую цену акции в историю.
     *
     * <p>Если акция встречается впервые, создаётся новый список.
     * Каждая последующая цена добавляется в конец списка, сохраняя
     * хронологический порядок.
     *
     * @param stockName название акции
     * @param price     текущая цена акции
     */
    @Override
    public void update(String stockName, double price) {
        priceHistory.computeIfAbsent(stockName, k -> new ArrayList<>()).add(price);
        System.out.println("[PortfolioTracker] " + stockName + ": записана цена " + price
                + " (всего записей: " + priceHistory.get(stockName).size() + ")");
    }

    /**
     * Возвращает историю цен для указанной акции.
     *
     * @param stockName название акции
     * @return список зафиксированных цен или пустой список, если акция не отслеживалась
     */
    public List<Double> getHistory(String stockName) {
        return priceHistory.getOrDefault(stockName, new ArrayList<>());
    }
}

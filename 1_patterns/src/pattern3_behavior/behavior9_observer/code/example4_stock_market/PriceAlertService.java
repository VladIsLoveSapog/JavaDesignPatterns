package pattern3_behavior.behavior9_observer.code.example4_stock_market;

/**
 * Конкретный наблюдатель (ConcreteObserver) — сервис ценовых уведомлений.
 *
 * <p>Отслеживает цену акции и генерирует предупреждение, когда цена
 * выходит за пределы заданного диапазона [{@code lowerBound}, {@code upperBound}].
 * Это пример <b>диапазонной условной реакции</b> — наблюдатель реагирует
 * только на значения, находящиеся вне нормального коридора.
 *
 * <p><b>Роль в паттерне Observer:</b><br>
 * Это <i>ConcreteObserver</i> — конкретная реализация {@link StockObserver}.
 * В отличие от {@link Alarm} из example3_alarm (один порог), этот наблюдатель
 * использует <b>два порога</b> (верхний и нижний), демонстрируя более сложную
 * условную логику при обработке уведомлений.
 *
 * @see StockObserver
 * @see Stock
 */
class PriceAlertService implements StockObserver {

    /** Нижний порог цены */
    private final double lowerBound;

    /** Верхний порог цены */
    private final double upperBound;

    /**
     * Создаёт сервис уведомлений с заданным ценовым диапазоном.
     *
     * @param lowerBound нижний порог — при падении цены ниже этого значения
     *                   генерируется предупреждение
     * @param upperBound верхний порог — при превышении этого значения
     *                   генерируется предупреждение
     */
    public PriceAlertService(double lowerBound, double upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /**
     * Проверяет, вышла ли цена за пределы допустимого диапазона.
     *
     * <p>Генерирует предупреждение в двух случаях:
     * <ul>
     *   <li>Цена ниже {@code lowerBound} — сигнал о сильном падении</li>
     *   <li>Цена выше {@code upperBound} — сигнал о сильном росте</li>
     * </ul>
     *
     * <p>Если цена находится в пределах диапазона, уведомление не выводится.
     *
     * @param stockName название акции
     * @param price     текущая цена акции
     */
    @Override
    public void update(String stockName, double price) {
        if (price < lowerBound) {
            System.out.println("[ALERT] " + stockName + ": цена " + price
                    + " ниже нижнего порога " + lowerBound + "!");
        } else if (price > upperBound) {
            System.out.println("[ALERT] " + stockName + ": цена " + price
                    + " выше верхнего порога " + upperBound + "!");
        }
    }
}

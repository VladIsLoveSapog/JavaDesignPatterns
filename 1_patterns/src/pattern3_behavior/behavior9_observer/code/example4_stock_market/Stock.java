package pattern3_behavior.behavior9_observer.code.example4_stock_market;

import java.util.ArrayList;
import java.util.List;

/**
 * Конкретный издатель (ConcreteSubject) — акция на бирже.
 *
 * <p>Представляет отдельную акцию с названием и текущей ценой. При изменении
 * цены через {@link #setPrice(double)} автоматически оповещает всех
 * зарегистрированных наблюдателей ({@link StockObserver}).
 *
 * <p><b>Роль в паттерне Observer:</b><br>
 * Это <i>ConcreteSubject</i> — конкретная реализация {@link StockExchange}.
 * Хранит список наблюдателей и текущее состояние (цену). Каждая акция
 * является независимым издателем, что позволяет наблюдателям подписываться
 * на разные акции по отдельности.
 *
 * <p><b>Шаблон «изменение-затем-оповещение»:</b> метод {@link #setPrice(double)}
 * сначала обновляет внутреннее состояние ({@code price}), а затем вызывает
 * {@link #notifyObservers()} — это гарантирует, что наблюдатели получают
 * актуальное значение цены.
 *
 * @see StockExchange
 * @see StockObserver
 */
class Stock implements StockExchange {

    /** Название акции (например, "AAPL", "GOOGL") */
    private final String name;

    /** Текущая цена акции */
    private double price;

    /** Список зарегистрированных наблюдателей */
    private final List<StockObserver> observers;

    /**
     * Создаёт акцию с указанным названием и начальной ценой.
     *
     * @param name  название акции
     * @param price начальная цена акции
     */
    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
        this.observers = new ArrayList<>();
    }

    /**
     * Устанавливает новую цену акции и оповещает всех наблюдателей.
     *
     * <p>Реализует шаблон <b>state-change-then-notify</b>: сначала
     * обновляется цена, затем вызывается {@link #notifyObservers()}.
     * Это гарантирует, что при получении уведомления наблюдатели
     * видят уже обновлённое значение.
     *
     * @param price новая цена акции
     */
    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    /**
     * Возвращает название акции.
     *
     * @return название акции
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает текущую цену акции.
     *
     * @return текущая цена
     */
    public double getPrice() {
        return price;
    }

    @Override
    public void registerObserver(StockObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(StockObserver observer) {
        observers.remove(observer);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Передаёт каждому наблюдателю название акции и текущую цену
     * через метод {@link StockObserver#update(String, double)}.
     */
    @Override
    public void notifyObservers() {
        for (StockObserver observer : observers) {
            observer.update(name, price);
        }
    }
}

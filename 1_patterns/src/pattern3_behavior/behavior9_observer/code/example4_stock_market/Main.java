package pattern3_behavior.behavior9_observer.code.example4_stock_market;

/**
 * Демонстрация паттерна «Наблюдатель» (Observer) на примере биржевой системы.
 *
 * <p>Показывает работу с множественными издателями (акциями) и разными типами
 * наблюдателей, включая подписку, отписку и условную реакцию:
 * <ol>
 *   <li>Создаются 2 акции: AAPL (150.0) и GOOGL (2800.0)</li>
 *   <li>Создаются 4 наблюдателя: 2 инвестора, трекер портфеля, сервис уведомлений</li>
 *   <li>Наблюдатели подписываются на акции</li>
 *   <li>Цена AAPL падает до 140.0 — инвестор Алиса покупает (порог 145), Боб ждёт</li>
 *   <li>Цена AAPL падает до 130.0 — оба инвестора покупают, сервис сигнализирует о падении</li>
 *   <li>Боб отписывается от AAPL</li>
 *   <li>Цена AAPL растёт до 160.0 — Боб больше не получает уведомлений</li>
 *   <li>Цена GOOGL растёт до 3100.0 — сервис сигнализирует о росте</li>
 *   <li>Выводится история цен из трекера портфеля</li>
 * </ol>
 *
 * <p><b>Ожидаемый вывод:</b>
 * <pre>
 * === Цена AAPL изменилась на 140.0 ===
 * Алиса: акция AAPL стоит 140.0 — покупаю! (порог: 145.0)
 * Боб: акция AAPL стоит 140.0 — слишком дорого (порог: 135.0)
 * [PortfolioTracker] AAPL: записана цена 140.0 (всего записей: 1)
 *
 * === Цена AAPL изменилась на 130.0 ===
 * Алиса: акция AAPL стоит 130.0 — покупаю! (порог: 145.0)
 * Боб: акция AAPL стоит 130.0 — покупаю! (порог: 135.0)
 * [PortfolioTracker] AAPL: записана цена 130.0 (всего записей: 2)
 * [ALERT] AAPL: цена 130.0 ниже нижнего порога 135.0!
 *
 * === Боб отписался от AAPL ===
 *
 * === Цена AAPL изменилась на 160.0 ===
 * Алиса: акция AAPL стоит 160.0 — слишком дорого (порог: 145.0)
 * [PortfolioTracker] AAPL: записана цена 160.0 (всего записей: 3)
 * [ALERT] AAPL: цена 160.0 выше верхнего порога 155.0!
 *
 * === Цена GOOGL изменилась на 3100.0 ===
 * [PortfolioTracker] GOOGL: записана цена 3100.0 (всего записей: 1)
 * [ALERT] GOOGL: цена 3100.0 выше верхнего порога 3000.0!
 *
 * === История цен AAPL: [140.0, 130.0, 160.0] ===
 * </pre>
 *
 * <p><b>Ключевые демонстрации:</b>
 * <ul>
 *   <li>Множественные Subject'ы — каждая акция является независимым издателем</li>
 *   <li>Отписка — {@link Investor} Боб перестаёт получать уведомления после отписки</li>
 *   <li>Stateful observer — {@link PortfolioTracker} накапливает историю цен</li>
 *   <li>Диапазонная условная реакция — {@link PriceAlertService} проверяет два порога</li>
 * </ul>
 */
public class Main {

    /**
     * Точка входа — запускает демонстрационный сценарий биржевой системы.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        // Создаём акции (ConcreteSubject)
        Stock apple = new Stock("AAPL", 150.0);
        Stock google = new Stock("GOOGL", 2800.0);

        // Создаём наблюдателей (ConcreteObserver)
        Investor alice = new Investor("Алиса", 145.0);
        Investor bob = new Investor("Боб", 135.0);
        PortfolioTracker tracker = new PortfolioTracker();
        PriceAlertService alertService = new PriceAlertService(135.0, 155.0);

        // Подписываем наблюдателей на акцию AAPL
        apple.registerObserver(alice);
        apple.registerObserver(bob);
        apple.registerObserver(tracker);
        apple.registerObserver(alertService);

        // Подписываем трекер и сервис уведомлений на GOOGL
        google.registerObserver(tracker);
        google.registerObserver(alertService);

        // Изменяем цену AAPL — все наблюдатели получают уведомление
        System.out.println("=== Цена AAPL изменилась на 140.0 ===");
        apple.setPrice(140.0);

        System.out.println();
        System.out.println("=== Цена AAPL изменилась на 130.0 ===");
        apple.setPrice(130.0);

        // Боб отписывается от AAPL
        System.out.println();
        apple.removeObserver(bob);
        System.out.println("=== Боб отписался от AAPL ===");

        // Изменяем цену — Боб больше не получает уведомлений
        System.out.println();
        System.out.println("=== Цена AAPL изменилась на 160.0 ===");
        apple.setPrice(160.0);

        // Изменяем цену GOOGL — уведомления получают только подписчики GOOGL
        System.out.println();
        System.out.println("=== Цена GOOGL изменилась на 3100.0 ===");
        google.setPrice(3100.0);

        // Выводим историю цен из трекера портфеля
        System.out.println();
        System.out.println("=== История цен AAPL: " + tracker.getHistory("AAPL") + " ===");
    }
}

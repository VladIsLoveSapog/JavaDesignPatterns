package pattern3_behavior.behavior8_mediator.code.example3_airport;

/**
 * Абстрактный коллега (Colleague) — самолёт в зоне аэропорта.
 *
 * <p>Каждый самолёт имеет позывной ({@code callSign}) и ссылку на
 * диспетчерскую башню ({@link ControlTower}). Все запросы на посадку
 * и взлёт делегируются башне — самолёты не координируются между собой.
 *
 * <p><b>Почему {@code abstract}:</b> разные типы самолётов (пассажирский,
 * грузовой) по-разному обрабатывают уведомления от башни, поэтому
 * метод {@link #receiveNotification(String)} — абстрактный.
 *
 * <p><b>Роль в паттерне:</b> Colleague (абстрактный коллега).
 */
public abstract class Aircraft {

    /** Позывной самолёта (например, "SU-100", "FX-200"). */
    private String callSign;

    /** Ссылка на диспетчерскую башню — единственный канал связи. */
    private ControlTower tower;

    /**
     * @param callSign позывной самолёта
     */
    public Aircraft(String callSign) {
        this.callSign = callSign;
    }

    /**
     * Устанавливает ссылку на диспетчерскую башню.
     * Вызывается посредником при регистрации самолёта.
     *
     * @param tower диспетчерская башня аэропорта
     */
    public void setTower(ControlTower tower) {
        this.tower = tower;
    }

    public String getCallSign() {
        return callSign;
    }

    /**
     * Запрашивает разрешение на посадку у диспетчерской башни.
     */
    public void requestLanding() {
        System.out.println(callSign + " запрашивает посадку...");
        // Делегируем решение башне — она знает о состоянии ВПП
        tower.requestLanding(this);
    }

    /**
     * Запрашивает разрешение на взлёт у диспетчерской башни.
     */
    public void requestTakeoff() {
        System.out.println(callSign + " запрашивает взлёт...");
        // Делегируем решение башне — она уведомит остальные самолёты
        tower.requestTakeoff(this);
    }

    /**
     * Получает уведомление от диспетчерской башни.
     * Реализация зависит от типа самолёта.
     *
     * @param message текст уведомления
     */
    public abstract void receiveNotification(String message);
}

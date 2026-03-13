package pattern3_behavior.behavior8_mediator.code.example3_airport;

import java.util.ArrayList;
import java.util.List;

/**
 * Конкретный посредник (Concrete Mediator) — аэропорт с диспетчерской башней.
 *
 * <p>Управляет состоянием взлётно-посадочной полосы (ВПП) и координирует
 * все запросы самолётов на посадку и взлёт. Самолёты не знают друг о друге —
 * вся координация проходит через аэропорт.
 *
 * <p><b>Роль в паттерне:</b> Concrete Mediator — инкапсулирует логику
 * управления воздушным движением, предотвращая столкновения на ВПП.
 */
public class Airport implements ControlTower {

    /** Список всех зарегистрированных самолётов в зоне аэропорта. */
    private List<Aircraft> aircraftList = new ArrayList<>();

    /**
     * Состояние ВПП: {@code true} — свободна, {@code false} — занята.
     * Только один самолёт может использовать ВПП одновременно.
     */
    private boolean runwayFree = true;

    /**
     * Регистрирует самолёт в системе аэропорта и устанавливает
     * ссылку на диспетчерскую башню.
     *
     * @param aircraft самолёт для регистрации
     */
    @Override
    public void registerAircraft(Aircraft aircraft) {
        aircraftList.add(aircraft);
        // Устанавливаем ссылку на башню — самолёт теперь может отправлять запросы
        aircraft.setTower(this);
        System.out.println("Башня: " + aircraft.getCallSign() + " зарегистрирован в аэропорту.");
    }

    /**
     * Обрабатывает запрос на посадку.
     * <p>Если ВПП свободна — разрешает посадку и блокирует полосу.
     * Если занята — отклоняет запрос и уведомляет самолёт.
     *
     * @param aircraft самолёт, запрашивающий посадку
     */
    @Override
    public void requestLanding(Aircraft aircraft) {
        if (runwayFree) {
            // ВПП свободна — разрешаем посадку
            runwayFree = false;
            System.out.println("Башня: " + aircraft.getCallSign()
                    + ", посадка разрешена. ВПП занята.");
            // Уведомляем остальные самолёты о том, что ВПП теперь занята
            notifyAll("ВПП занята, " + aircraft.getCallSign() + " совершает посадку.", aircraft);
        } else {
            // ВПП занята — отклоняем запрос
            System.out.println("Башня: " + aircraft.getCallSign()
                    + ", посадка отклонена! ВПП занята. Ожидайте.");
        }
    }

    /**
     * Обрабатывает запрос на взлёт.
     * <p>После взлёта ВПП освобождается, и все самолёты получают уведомление.
     *
     * @param aircraft самолёт, выполняющий взлёт
     */
    @Override
    public void requestTakeoff(Aircraft aircraft) {
        // Освобождаем ВПП после взлёта
        runwayFree = true;
        System.out.println("Башня: " + aircraft.getCallSign()
                + " взлетел. ВПП свободна.");
        // Уведомляем все самолёты — кто-то из них может запросить посадку
        notifyAll("ВПП свободна, " + aircraft.getCallSign() + " взлетел.", aircraft);
    }

    /**
     * Рассылает уведомление всем зарегистрированным самолётам, кроме отправителя.
     *
     * @param message текст уведомления
     * @param sender  самолёт-инициатор (не получает своё же уведомление)
     */
    private void notifyAll(String message, Aircraft sender) {
        for (Aircraft aircraft : aircraftList) {
            // Пропускаем самолёт-инициатор — ему не нужно уведомление о своём действии
            if (aircraft != sender) {
                aircraft.receiveNotification(message);
            }
        }
    }
}

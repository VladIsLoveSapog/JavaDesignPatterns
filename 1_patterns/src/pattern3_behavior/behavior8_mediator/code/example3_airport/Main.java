package pattern3_behavior.behavior8_mediator.code.example3_airport;

/**
 * Клиентский код — демонстрация паттерна Посредник (Mediator)
 * на примере диспетчерской башни аэропорта.
 *
 * <p>Самолёты не взаимодействуют друг с другом напрямую — все запросы
 * на посадку и взлёт проходят через диспетчерскую башню ({@link Airport}),
 * которая координирует использование взлётно-посадочной полосы.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Посредник (Mediator): Диспетчерская башня аэропорта ===\n");

        // 1. Создаём посредника — аэропорт с диспетчерской башней
        ControlTower airport = new Airport();

        // 2. Создаём самолёты разных типов
        Aircraft plane1 = new PassengerPlane("SU-100");
        Aircraft plane2 = new PassengerPlane("BA-200");
        Aircraft plane3 = new CargoPlane("FX-300");

        // 3. Регистрируем самолёты в аэропорту
        airport.registerAircraft(plane1);
        airport.registerAircraft(plane2);
        airport.registerAircraft(plane3);

        System.out.println();

        // 4. SU-100 запрашивает посадку → ВПП свободна → разрешение
        plane1.requestLanding();

        System.out.println();

        // 5. BA-200 запрашивает посадку → ВПП занята → отказ
        plane2.requestLanding();

        System.out.println();

        // 6. SU-100 взлетает → ВПП освобождается → все уведомлены
        plane1.requestTakeoff();

        System.out.println();

        // 7. BA-200 снова запрашивает посадку → ВПП свободна → разрешение
        plane2.requestLanding();
    }
}

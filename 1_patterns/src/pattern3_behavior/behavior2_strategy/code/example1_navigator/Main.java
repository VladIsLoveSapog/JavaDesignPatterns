package pattern3_behavior.behavior2_strategy.code.example1_navigator;

/**
 * Демонстрация паттерна Стратегия на примере навигатора.
 *
 * <p>Создаём {@link Navigator} и переключаем стратегии построения маршрута
 * (автомобиль → общественный транспорт → пешком) без изменения кода навигатора.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Навигатор: стратегии маршрутов ===\n");

        // навигатор со стратегией автомобильного маршрута
        Navigator navigator = new Navigator(new RoadStrategy());
        navigator.buildRoute("A", "B");

        // сменили стратегию на общественный транспорт
        navigator.setRouteStrategy(new PublicTransportStrategy());
        navigator.buildRoute("A", "B");

        // сменили стратегию на пеший ход
        navigator.setRouteStrategy(new WalkingStrategy());
        navigator.buildRoute("A", "B");
    }
}

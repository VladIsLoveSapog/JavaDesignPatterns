package pattern3_behavior.behavior2_strategy.code.example1_navigator;

/**
 * Навигатор — контекст паттерна Стратегия (роль <b>Context</b>).
 *
 * <p>Хранит ссылку на текущую стратегию ({@link RouteStrategy}) и делегирует
 * ей построение маршрута. Навигатор не знает, какой именно алгоритм
 * используется — он работает с любой стратегией через общий интерфейс.
 *
 * <p><b>Когда использовать:</b> когда объекту нужно переключать алгоритм
 * на лету. Клиентский код передаёт нужную стратегию в навигатор,
 * а навигатор просто делегирует ей работу.
 */
public class Navigator {

    /**
     * Текущая стратегия построения маршрута.
     * Может быть заменена в любой момент через {@link #setRouteStrategy(RouteStrategy)},
     * что позволяет переключать алгоритм без изменения кода навигатора.
     */
    private RouteStrategy routeStrategy;

    /**
     * Создаёт навигатор с указанной стратегией маршрута.
     *
     * @param routeStrategy начальная стратегия построения маршрута
     */
    public Navigator(RouteStrategy routeStrategy) {
        this.routeStrategy = routeStrategy;
    }

    /**
     * Возвращает текущую стратегию маршрута.
     *
     * @return текущий объект {@link RouteStrategy}
     */
    public RouteStrategy getRouteStrategy() {
        return routeStrategy;
    }

    /**
     * Заменяет текущую стратегию маршрута на новую.
     * Позволяет переключать алгоритм во время выполнения программы.
     *
     * @param routeStrategy новая стратегия построения маршрута
     */
    public void setRouteStrategy(RouteStrategy routeStrategy) {
        this.routeStrategy = routeStrategy;
    }

    /**
     * Строит маршрут, делегируя работу текущей стратегии.
     *
     * @param start начальная точка маршрута
     * @param end   конечная точка маршрута
     */
    public void buildRoute(String start, String end) {
        System.out.println(routeStrategy.buildRoute(start, end));
    }
}

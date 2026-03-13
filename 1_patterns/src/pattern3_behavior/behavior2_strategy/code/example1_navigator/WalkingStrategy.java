package pattern3_behavior.behavior2_strategy.code.example1_navigator;

/**
 * Стратегия пешего маршрута (роль <b>ConcreteStrategy</b>).
 *
 * <p>Реализует алгоритм построения пешеходного маршрута.
 * В реальном приложении алгоритм строил бы путь по тротуарам,
 * пешеходным дорожкам и переходам, минимизируя расстояние или время.
 *
 * <p><b>Когда использовать:</b> пользователь выбирает пешую прогулку —
 * {@link Navigator} переключается на эту стратегию.
 */
public class WalkingStrategy implements RouteStrategy {

    /**
     * Строит пеший маршрут между двумя точками.
     *
     * @param start начальная точка маршрута
     * @param end   конечная точка маршрута
     * @return описание построенного пешего маршрута
     */
    @Override
    public String buildRoute(String start, String end) {
        return "От точки " + start + " построен пеший маршрут до точки " + end;
    }
}

package pattern2_structural.struct5_decorator.code.example1_pizza;

/**
 * Конкретный компонент (Concrete Component) — базовая пицца «Пепперони».
 *
 * <p>Это объект, поведение которого мы будем расширять с помощью декораторов.
 * Он реализует интерфейс {@link Pizza} и содержит базовую бизнес-логику:
 * описание ингредиентов и стоимость.
 *
 * <p>Сам по себе конкретный компонент ничего не знает о декораторах —
 * он просто выполняет свою работу.
 */
public class PepperoniPizza implements Pizza {

    /**
     * Возвращает базовое описание пиццы: пепперони, перец, моцарелла.
     *
     * @return описание ингредиентов
     */
    @Override
    public String getDescription() {
        return "Pepperoni, Pepper, mozzarella";
    }

    /**
     * Возвращает базовую стоимость пиццы — 10.0.
     * Декораторы могут добавлять или вычитать из этой суммы.
     *
     * @return базовая стоимость
     */
    @Override
    public Double getCost() {
        return 10.0;
    }
}

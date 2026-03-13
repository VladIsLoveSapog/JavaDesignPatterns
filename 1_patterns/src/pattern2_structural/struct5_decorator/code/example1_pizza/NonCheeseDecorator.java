package pattern2_structural.struct5_decorator.code.example1_pizza;

/**
 * Конкретный декоратор (Concrete Decorator) — убирает сыр из пиццы.
 *
 * <p>Демонстрирует важный момент: декоратор может не только <b>добавлять</b>
 * функциональность, но и <b>убирать/уменьшать</b> её. В данном случае
 * убираем сыр из описания и уменьшаем цену.
 *
 * <p>Этот декоратор имеет смысл применять после {@link CheesePizzaDecorator},
 * чтобы «отменить» добавление сыра. Это показывает гибкость композиции
 * декораторов — их можно комбинировать в любом порядке.
 */
public class NonCheeseDecorator extends PizzaDecorator {

    /**
     * @param pizza пицца, из которой убираем сыр
     */
    public NonCheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    /**
     * Убираем упоминание сыра из описания пиццы.
     * Если сыр не был добавлен — описание останется без изменений.
     *
     * @return описание пиццы без сыра
     */
    @Override
    public String getDescription() {
        return pizza.getDescription().replace(", Cheese", "");
    }

    /**
     * Уменьшаем стоимость на 2.00 — компенсируем цену сыра.
     *
     * @return стоимость пиццы без сыра
     */
    @Override
    public Double getCost() {
        return pizza.getCost() - 2.00;
    }
}

package pattern2_structural.struct5_decorator.code.example1_pizza;

/**
 * Конкретный декоратор (Concrete Decorator) — добавляет сыр к пицце.
 *
 * <p>Наследуется от {@link PizzaDecorator} и переопределяет оба метода,
 * расширяя поведение: добавляет «, Cheese» к описанию и +2.00 к стоимости.
 *
 * <p>Обратите внимание: декоратор не заменяет поведение оригинального объекта,
 * а <b>дополняет</b> его — сначала вызывает метод обёрнутого объекта,
 * затем добавляет своё.
 */
public class CheesePizzaDecorator extends PizzaDecorator {

    /**
     * Передаём пиццу в конструктор базового декоратора.
     *
     * @param pizza пицца, к которой добавляем сыр
     */
    public CheesePizzaDecorator(Pizza pizza) {
        super(pizza);
    }

    /**
     * Расширяем описание: берём описание обёрнутой пиццы и дописываем «, Cheese».
     *
     * @return описание пиццы с добавленным сыром
     */
    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Cheese";
    }

    /**
     * Расширяем стоимость: берём цену обёрнутой пиццы и прибавляем 2.00.
     * С сыром пицца дороже :(
     *
     * @return стоимость пиццы с учётом сыра
     */
    @Override
    public Double getCost() {
        return pizza.getCost() + 2.00;
    }
}

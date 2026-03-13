package pattern3_behavior.behavior10_template_method.code.example2_drinks;

/**
 * Приготовление чая — <b>ConcreteClass</b> паттерна Template Method.
 *
 * <p>Реализует абстрактные шаги {@link #brew()} и {@link #addCondiments()}
 * для приготовления чая: заваривание чайных листьев и добавление лимона.
 *
 * @see Beverage
 * @see Coffee
 */
class Tea extends Beverage {

    /** Абстрактный шаг — заваривание чая. */
    @Override
    protected void brew() {
        System.out.println("Заваривание чая");
    }

    /** Абстрактный шаг — добавление лимона к чаю. */
    @Override
    protected void addCondiments() {
        System.out.println("Добавление лимона");
    }
}

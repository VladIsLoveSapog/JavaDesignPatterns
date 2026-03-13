package pattern3_behavior.behavior10_template_method.code.example2_drinks;

/**
 * Приготовление кофе — <b>ConcreteClass</b> паттерна Template Method.
 *
 * <p>Реализует абстрактные шаги {@link #brew()} и {@link #addCondiments()}
 * для приготовления кофе: варка кофейных зёрен и добавление сахара с молоком.
 *
 * @see Beverage
 * @see Tea
 */
class Coffee extends Beverage {

    /** Абстрактный шаг — заваривание кофе. */
    @Override
    protected void brew() {
        System.out.println("Заваривание кофе");
    }

    /** Абстрактный шаг — добавление сахара и молока к кофе. */
    @Override
    protected void addCondiments() {
        System.out.println("Добавление сахара и молока");
    }
}

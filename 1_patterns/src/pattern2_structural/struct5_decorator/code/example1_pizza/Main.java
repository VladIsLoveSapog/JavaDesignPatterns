package pattern2_structural.struct5_decorator.code.example1_pizza;

/**
 * Демонстрация паттерна Декоратор на примере пиццы.
 *
 * <p>Показывает, как декораторы оборачивают объект слой за слоем,
 * добавляя или убирая функциональность без изменения исходного класса.
 *
 * <p><b>Ожидаемый вывод:</b>
 * <pre>
 * Пицца Пепперони:
 * Pepperoni, Pepper, mozzarella
 * 10.0
 * ------------------------
 * Пепперони с сыром:
 * Pepperoni, Pepper, mozzarella, Cheese
 * 12.0
 * ------------------------
 * Из пепперони с сыром вытащили сыр:
 * Pepperoni, Pepper, mozzarella
 * 10.0
 * </pre>
 */
public class Main {

    public static void main(String[] args) {
        // 1. Создаём базовый компонент — пиццу Пепперони
        System.out.println("Пицца Пепперони: ");
        Pizza pepperoniPizza = new PepperoniPizza();
        System.out.println(pepperoniPizza.getDescription());
        System.out.println(pepperoniPizza.getCost());

        System.out.println("------------------------");

        // 2. Оборачиваем пиццу декоратором — добавляем сыр.
        //    Переменная pepperoniPizza теперь указывает на декоратор,
        //    но тип по-прежнему Pizza — клиентский код ничего не заметил.
        System.out.println("Пепперони с сыром: ");
        pepperoniPizza = new CheesePizzaDecorator(pepperoniPizza);
        System.out.println(pepperoniPizza.getDescription());
        System.out.println(pepperoniPizza.getCost());

        System.out.println("------------------------");

        // 3. Оборачиваем ещё раз — теперь убираем сыр.
        //    Обратите внимание: мы кладём декоратор в декоратор!
        //    Это возможно именно потому, что все они реализуют один интерфейс Pizza.
        System.out.println("Из пепперони с сыром вытащили сыр: ");
        pepperoniPizza = new NonCheeseDecorator(pepperoniPizza);
        System.out.println(pepperoniPizza.getDescription());
        System.out.println(pepperoniPizza.getCost());
    }
}

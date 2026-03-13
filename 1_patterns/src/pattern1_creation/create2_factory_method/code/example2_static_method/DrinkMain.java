package pattern1_creation.create2_factory_method.code.example2_static_method;

/**
 * Демонстрация варианта «статический фабричный метод».
 *
 * Напитки создаются через статические методы самого класса Drink —
 * аналогично тому, как работают List.of(), Optional.of() и т.д.
 * Без вложенных классов, без наследования — один класс Drink.
 */
public class DrinkMain {
    public static void main(String[] args) {
        System.out.println("=== Используем статические фабричные методы Drink ===");
        System.out.println();

        // Создаём напитки через статические методы класса Drink.
        // Обратите внимание на синтаксис: Drink.createXxx(...)
        Drink espresso = Drink.createCoffee(200, "Эспрессо");
        Drink cappuccino = Drink.createCoffee(350, "Капучино");
        Drink greenTea = Drink.createTea("Зелёный");
        Drink blackTea = Drink.createTea("Чёрный");

        System.out.println();
        System.out.println("=== Описание заказанных напитков ===");

        // Работаем с объектами Drink — конструктор скрыт, создание только через фабричные методы
        espresso.describe();
        cappuccino.describe();
        greenTea.describe();
        blackTea.describe();
    }
}

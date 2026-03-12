package pattern1_creation.create2_factory_method.code.example2_static_nested;

/**
 * Демонстрация варианта «вложенный статический фабричный метод».
 *
 * Фабрика живёт внутри класса продукта (Drink.Factory).
 * Все конкретные продукты тоже вложены (Drink.Coffee, Drink.Tea).
 * Это удобно, когда хочется держать всё связанное в одном месте.
 */
public class DrinkMain {
    public static void main(String[] args) {
        System.out.println("=== Используем вложенную фабрику Drink.Factory ===");
        System.out.println();

        // Создаём напитки через статические методы вложенной фабрики.
        // Обратите внимание на синтаксис: Drink.Factory.createXxx(...)
        Drink espresso = Drink.Factory.createCoffee(200, "Эспрессо");
        Drink cappuccino = Drink.Factory.createCoffee(350, "Капучино");
        Drink greenTea = Drink.Factory.createTea("Зелёный");
        Drink blackTea = Drink.Factory.createTea("Чёрный");

        System.out.println();
        System.out.println("=== Описание заказанных напитков ===");

        // Работаем через общий тип Drink — не знаем конкретных классов
        espresso.describe();
        cappuccino.describe();
        greenTea.describe();
        blackTea.describe();
    }
}

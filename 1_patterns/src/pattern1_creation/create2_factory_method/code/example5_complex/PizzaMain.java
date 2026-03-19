package pattern1_creation.create2_factory_method.code.example5_complex;

/**
 * Демонстрация сложной фабрики — полноценный объект с состоянием и логикой.
 *
 * Создаём два экземпляра PizzaFactory с разными конфигурациями:
 * одна — итальянская кухня с логированием, другая — стандартная без него.
 * Обе создают пиццу, но ведут себя по-разному благодаря состоянию фабрики.
 */
public class PizzaMain {
    public static void main(String[] args) {
        System.out.println("=== Сложная фабрика: объект с состоянием и логикой ===");
        System.out.println();

        // Фабрика 1: итальянская кухня, логирование включено, наценка +50%
        PizzaFactory italianFactory = new PizzaFactory("Марио Конти", true, 1.5);

        // Фабрика 2: стандартная пиццерия, логирование выключено, наценки нет
        PizzaFactory standardFactory = new PizzaFactory("Иван Петров", false, 1.0);

        System.out.println("--- Заказ из итальянской кухни (шеф: " + italianFactory.getChefName() + ") ---");

        // createPizza вызывает внутри: validateSize → validatePizzaType →
        // selectIngredients → calculatePrice → logCreation
        Pizza margherita = italianFactory.createPizza("Маргарита", PizzaSize.LARGE);
        margherita.describe();

        System.out.println();

        Pizza pepperoni = italianFactory.createPizza("Пепперони", PizzaSize.MEDIUM);
        pepperoni.describe();

        System.out.println();
        System.out.println("--- Заказ из стандартной пиццерии (шеф: " + standardFactory.getChefName() + ") ---");
        // Логирование выключено — никаких [Лог] сообщений не будет
        Pizza quattroCheese = standardFactory.createPizza("Четыре сыра", PizzaSize.SMALL);
        quattroCheese.describe();

        System.out.println();

        // Демонстрация валидации
        System.out.println("--- Попытка заказать несуществующую пиццу ---");
        try {
            italianFactory.createPizza("Гавайская", PizzaSize.MEDIUM);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}

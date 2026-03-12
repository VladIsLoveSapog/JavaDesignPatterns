package pattern1_creation.create2_factory_method.code.example5_complex;

import java.util.Arrays;
import java.util.List;

/**
 * Сложная внешняя фабрика — полноценный объект с состоянием и логикой.
 *
 * Ключевое отличие от примера 3 (ShapeFactory): здесь фабрика — не утилитный класс
 * со статическими методами, а полноценный объект. У неё есть:
 *   - поля (chefName, loggingEnabled) — состояние, которое влияет на поведение
 *   - публичный фабричный метод createPizza(...)
 *   - несколько приватных вспомогательных методов (validateSize, calculatePrice, logCreation)
 *
 * Это позволяет создавать несколько фабрик с разными конфигурациями,
 * например: итальянская кухня с логированием и детская кухня без него.
 */
public class PizzaFactory {

    /** Имя шеф-повара этой фабрики (влияет на логирование и стиль) */
    private final String chefName;

    /**
     * Включено ли логирование процесса приготовления.
     * Если true — фабрика печатает подробные сообщения при создании пиццы.
     */
    private final boolean loggingEnabled;

    /**
     * Коэффициент наценки в зависимости от "уровня" ресторана.
     * Например, 1.0 — стандартно, 1.5 — премиум-сегмент.
     */
    private final double markupCoefficient;

    /**
     * Конструктор фабрики.
     *
     * @param chefName          имя шефа, управляющего этой фабрикой
     * @param loggingEnabled    включить ли подробное логирование
     * @param markupCoefficient коэффициент наценки (>=1.0)
     */
    public PizzaFactory(String chefName, boolean loggingEnabled, double markupCoefficient) {
        this.chefName = chefName;
        this.loggingEnabled = loggingEnabled;
        this.markupCoefficient = markupCoefficient;
    }

    /**
     * Фабричный метод — создать пиццу указанного вида и размера.
     *
     * Координирует весь процесс: валидацию, подбор ингредиентов,
     * расчёт цены и логирование. Клиентскому коду не нужно знать детали.
     *
     * @param pizzaType название типа пиццы ("Маргарита", "Пепперони", "Четыре сыра")
     * @param size      размер пиццы
     * @return готовый объект Pizza
     */
    public Pizza createPizza(String pizzaType, PizzaSize size) {
        // Шаг 1: валидация входных данных
        validateSize(size);
        validatePizzaType(pizzaType);

        // Шаг 2: подбор ингредиентов по типу пиццы
        List<String> ingredients = selectIngredients(pizzaType);

        // Шаг 3: расчёт итоговой цены с учётом размера и наценки фабрики
        double price = calculatePrice(pizzaType, size);

        // Шаг 4: создаём объект-продукт
        Pizza pizza = new Pizza(pizzaType, ingredients, size, price);

        // Шаг 5: логируем создание (если включено)
        logCreation(pizza);

        return pizza;
    }

    // =========================================================================
    // Приватные вспомогательные методы
    // =========================================================================

    /**
     * Проверяет корректность размера.
     * Генерирует исключение, если размер null.
     *
     * @param size размер для проверки
     */
    private void validateSize(PizzaSize size) {
        if (size == null) {
            throw new IllegalArgumentException("Размер пиццы не может быть null.");
        }
    }

    /**
     * Проверяет, что тип пиццы поддерживается этой фабрикой.
     *
     * @param pizzaType название типа пиццы
     */
    private void validatePizzaType(String pizzaType) {
        List<String> supported = Arrays.asList("Маргарита", "Пепперони", "Четыре сыра");
        if (!supported.contains(pizzaType)) {
            throw new IllegalArgumentException(
                    "Неизвестный тип пиццы: '" + pizzaType + "'. "
                    + "Шеф " + chefName + " готовит только: " + supported);
        }
    }

    /**
     * Подбирает ингредиенты в зависимости от типа пиццы.
     *
     * В реальном приложении здесь мог бы быть запрос к базе данных
     * или чтение из конфигурационного файла рецептур.
     *
     * @param pizzaType тип пиццы
     * @return список ингредиентов
     */
    private List<String> selectIngredients(String pizzaType) {
        switch (pizzaType) {
            case "Маргарита":
                return Arrays.asList("тесто", "томатный соус", "моцарелла", "базилик");
            case "Пепперони":
                return Arrays.asList("тесто", "томатный соус", "моцарелла", "пепперони");
            case "Четыре сыра":
                return Arrays.asList("тесто", "сливочный соус", "моцарелла", "пармезан", "горгонзола", "чеддер");
            default:
                // Сюда не попадём — validatePizzaType уже проверила тип
                throw new IllegalStateException("Неизвестный тип: " + pizzaType);
        }
    }

    /**
     * Рассчитывает цену пиццы с учётом её типа, размера и наценки фабрики.
     *
     * Базовая цена зависит от типа, размер добавляет коэффициент,
     * и всё умножается на наценку этой конкретной фабрики.
     *
     * @param pizzaType тип пиццы
     * @param size      размер
     * @return итоговая цена в рублях
     */
    private double calculatePrice(String pizzaType, PizzaSize size) {
        // Базовые цены по типам пиццы
        double basePrice;
        switch (pizzaType) {
            case "Маргарита":    basePrice = 350; break;
            case "Пепперони":    basePrice = 450; break;
            case "Четыре сыра": basePrice = 500; break;
            default:             basePrice = 400;
        }

        // Коэффициент размера
        double sizeCoefficient;
        switch (size) {
            case SMALL:  sizeCoefficient = 0.8; break;
            case MEDIUM: sizeCoefficient = 1.0; break;
            case LARGE:  sizeCoefficient = 1.3; break;
            default:     sizeCoefficient = 1.0;
        }

        // Итоговая цена = базовая * размер * наценка фабрики
        return basePrice * sizeCoefficient * markupCoefficient;
    }

    /**
     * Логирует создание пиццы в консоль (если логирование включено).
     *
     * @param pizza созданная пицца
     */
    private void logCreation(Pizza pizza) {
        if (loggingEnabled) {
            System.out.println("[Лог | Шеф " + chefName + "] Приготовлена пицца '"
                    + pizza.getName() + "' (" + pizza.getSize().getDisplayName()
                    + ") за " + String.format("%.0f", pizza.getPrice()) + " руб.");
        }
    }

    /**
     * Вернуть имя шефа этой фабрики.
     *
     * @return имя шефа
     */
    public String getChefName() {
        return chefName;
    }
}

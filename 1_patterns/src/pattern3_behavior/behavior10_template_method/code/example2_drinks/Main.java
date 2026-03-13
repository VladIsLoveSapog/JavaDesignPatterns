package pattern3_behavior.behavior10_template_method.code.example2_drinks;

/**
 * Клиентский код — демонстрация паттерна <b>Template Method</b>
 * на примере приготовления напитков.
 *
 * <p>Создаются два напитка: {@link Tea} и {@link Coffee}.
 * Оба используют один и тот же шаблонный метод
 * {@link Beverage#prepareRecipe()}, но отличаются реализацией
 * абстрактных шагов {@code brew()} и {@code addCondiments()}.
 */
public class Main {
    public static void main(String[] args) {
        // Приготовление чая: brew() → заваривание, addCondiments() → лимон
        Beverage tea = new Tea();
        System.out.println("Приготовление чая:");
        tea.prepareRecipe();

        System.out.println("\nПриготовление кофе:");
        // Приготовление кофе: brew() → заваривание, addCondiments() → сахар и молоко
        Beverage coffee = new Coffee();
        coffee.prepareRecipe();
    }
}

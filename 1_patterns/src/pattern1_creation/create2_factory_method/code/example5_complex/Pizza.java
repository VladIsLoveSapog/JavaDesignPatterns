package pattern1_creation.create2_factory_method.code.example5_complex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Продукт — пицца.
 *
 * Простой класс-продукт, который создаётся фабрикой PizzaFactory.
 * Содержит информацию о названии, ингредиентах, размере и цене.
 */
public class Pizza {

    /** Название пиццы (например, "Маргарита") */
    private final String name;

    /** Список ингредиентов */
    private final List<String> ingredients;

    /** Размер пиццы */
    private final PizzaSize size;

    /** Итоговая цена */
    private final double price;

    /**
     * Конструктор пиццы. Создаётся только фабрикой.
     *
     * @param name        название
     * @param ingredients список ингредиентов
     * @param size        размер
     * @param price       цена
     */
    public Pizza(String name, List<String> ingredients, PizzaSize size, double price) {
        this.name = name;
        // Защитная копия списка — чтобы внешний код не мог изменить состав
        this.ingredients = new ArrayList<>(ingredients);
        this.size = size;
        this.price = price;
    }

    /**
     * Вывести полное описание пиццы.
     */
    public void describe() {
        System.out.println("Пицца: " + name);
        System.out.println("  Размер: " + size.getDisplayName() + " (" + size.getDiameterCm() + " см)");
        System.out.println("  Ингредиенты: " + String.join(", ", ingredients));
        System.out.printf("  Цена: %.0f руб.%n", price);
    }

    /**
     * Вернуть название пиццы.
     */
    public String getName() {
        return name;
    }

    /**
     * Вернуть список ингредиентов (неизменяемая копия).
     */
    public List<String> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    /**
     * Вернуть размер пиццы.
     */
    public PizzaSize getSize() {
        return size;
    }

    /**
     * Вернуть цену пиццы.
     */
    public double getPrice() {
        return price;
    }
}

package pattern3_behavior.behavior7_visitor.code.example3_shopping_cart;

import java.util.List;

/**
 * Демонстрация паттерна Посетитель на примере корзины покупок.
 *
 * <p>Корзина содержит товары разных типов ({@link Book}, {@link Electronics},
 * {@link Food}). К ней применяются два посетителя:
 * <ul>
 *     <li>{@link TaxCalculatorVisitor} — рассчитывает налог для каждого товара</li>
 *     <li>{@link DiscountVisitor} — рассчитывает скидку для каждого товара</li>
 * </ul>
 *
 * <p><b>Главное преимущество:</b> обе операции (налог и скидка) добавлены
 * без изменения классов товаров. Если понадобится новая операция
 * (например, расчёт доставки), достаточно создать нового посетителя.
 */
public class Main {
    public static void main(String[] args) {
        // Формируем корзину из товаров разных типов
        List<Product> cart = List.of(
                new Book("Чистый код", 1500.0),
                new Electronics("Наушники", 5000.0, 24),
                new Food("Рис", 300.0, 5.0),
                new Book("Паттерны проектирования", 2000.0),
                new Electronics("Мышь", 800.0, 6),
                new Food("Шоколад", 150.0, 0.5)
        );

        // --- Применяем первого посетителя: расчёт налога ---
        System.out.println("=== Расчёт налога ===");
        TaxCalculatorVisitor taxVisitor = new TaxCalculatorVisitor();
        for (Product product : cart) {
            product.accept(taxVisitor);
        }
        System.out.printf("Итого налог: %.2f%n%n", taxVisitor.getTotalTax());

        // --- Применяем второго посетителя: расчёт скидки ---
        System.out.println("=== Расчёт скидки ===");
        DiscountVisitor discountVisitor = new DiscountVisitor();
        for (Product product : cart) {
            product.accept(discountVisitor);
        }
        System.out.printf("Итого скидка: %.2f%n", discountVisitor.getTotalDiscount());
    }
}

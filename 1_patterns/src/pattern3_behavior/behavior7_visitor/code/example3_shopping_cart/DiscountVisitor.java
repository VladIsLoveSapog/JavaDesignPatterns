package pattern3_behavior.behavior7_visitor.code.example3_shopping_cart;

/**
 * Конкретный посетитель — расчёт скидки на товары.
 *
 * <p>Для каждого типа товара применяет свои правила скидки:
 * <ul>
 *     <li>Книги — 10% скидка (промо-акция)</li>
 *     <li>Электроника — 5% скидка, если гарантия более 12 месяцев</li>
 *     <li>Продукты питания — 15% скидка при весе более 2 кг (опт)</li>
 * </ul>
 *
 * <p><b>Роль в паттерне:</b> второй конкретный посетитель (ConcreteVisitor).
 * Демонстрирует главное преимущество паттерна — добавление новой операции
 * (расчёт скидки) без изменения классов товаров.
 */
public class DiscountVisitor implements ProductVisitor {

    /** Накопленная сумма скидки по всем товарам. */
    private double totalDiscount = 0;

    /**
     * Рассчитывает скидку на книгу (10%).
     *
     * @param book книга для расчёта скидки
     */
    @Override
    public void visitBook(Book book) {
        double discount = book.getPrice() * 0.10;
        totalDiscount += discount;
        System.out.printf("  Книга \"%s\": скидка 10%% = %.2f%n",
                book.getName(), discount);
    }

    /**
     * Рассчитывает скидку на электронику (5% при гарантии > 12 месяцев).
     *
     * @param electronics электроника для расчёта скидки
     */
    @Override
    public void visitElectronics(Electronics electronics) {
        // Скидка даётся только на товары с расширенной гарантией
        if (electronics.getWarrantyMonths() > 12) {
            double discount = electronics.getPrice() * 0.05;
            totalDiscount += discount;
            System.out.printf("  Электроника \"%s\" (гарантия %d мес.): скидка 5%% = %.2f%n",
                    electronics.getName(), electronics.getWarrantyMonths(), discount);
        } else {
            System.out.printf("  Электроника \"%s\" (гарантия %d мес.): скидка не применяется%n",
                    electronics.getName(), electronics.getWarrantyMonths());
        }
    }

    /**
     * Рассчитывает скидку на продукт питания (15% при весе > 2 кг).
     *
     * @param food продукт питания для расчёта скидки
     */
    @Override
    public void visitFood(Food food) {
        // Оптовая скидка применяется только при большом весе
        if (food.getWeightKg() > 2.0) {
            double discount = food.getPrice() * 0.15;
            totalDiscount += discount;
            System.out.printf("  Продукт \"%s\" (%.1f кг): оптовая скидка 15%% = %.2f%n",
                    food.getName(), food.getWeightKg(), discount);
        } else {
            System.out.printf("  Продукт \"%s\" (%.1f кг): скидка не применяется%n",
                    food.getName(), food.getWeightKg());
        }
    }

    /**
     * @return общая сумма скидки по всем посещённым товарам
     */
    public double getTotalDiscount() {
        return totalDiscount;
    }
}

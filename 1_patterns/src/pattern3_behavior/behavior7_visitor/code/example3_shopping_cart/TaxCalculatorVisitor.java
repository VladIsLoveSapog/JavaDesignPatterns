package pattern3_behavior.behavior7_visitor.code.example3_shopping_cart;

/**
 * Конкретный посетитель — расчёт налога на товары.
 *
 * <p>Для каждого типа товара применяет свою ставку налога:
 * <ul>
 *     <li>Книги — 5% (льготная ставка)</li>
 *     <li>Электроника — 20% (стандартная ставка)</li>
 *     <li>Продукты питания — 10%</li>
 * </ul>
 *
 * <p><b>Роль в паттерне:</b> конкретный посетитель (ConcreteVisitor).
 * Накапливает общую сумму налога при обходе всех товаров корзины.
 */
public class TaxCalculatorVisitor implements ProductVisitor {

    /** Накопленная сумма налога по всем товарам. */
    private double totalTax = 0;

    /**
     * Рассчитывает налог на книгу (5%).
     *
     * @param book книга для расчёта налога
     */
    @Override
    public void visitBook(Book book) {
        double tax = book.getPrice() * 0.05;
        totalTax += tax;
        System.out.printf("  Книга \"%s\": цена %.2f, налог 5%% = %.2f%n",
                book.getName(), book.getPrice(), tax);
    }

    /**
     * Рассчитывает налог на электронику (20%).
     *
     * @param electronics электроника для расчёта налога
     */
    @Override
    public void visitElectronics(Electronics electronics) {
        double tax = electronics.getPrice() * 0.20;
        totalTax += tax;
        System.out.printf("  Электроника \"%s\": цена %.2f, налог 20%% = %.2f%n",
                electronics.getName(), electronics.getPrice(), tax);
    }

    /**
     * Рассчитывает налог на продукт питания (10%).
     *
     * @param food продукт питания для расчёта налога
     */
    @Override
    public void visitFood(Food food) {
        double tax = food.getPrice() * 0.10;
        totalTax += tax;
        System.out.printf("  Продукт \"%s\": цена %.2f, налог 10%% = %.2f%n",
                food.getName(), food.getPrice(), tax);
    }

    /**
     * @return общая сумма налога по всем посещённым товарам
     */
    public double getTotalTax() {
        return totalTax;
    }
}

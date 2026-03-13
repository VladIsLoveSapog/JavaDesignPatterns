package pattern1_creation.create3_abstract_factory.code.example1_furniture;

/**
 * Абстрактный продукт — кресло.
 *
 * <p>Определяет общий контракт для всех реализаций кресел в различных стилях.
 * Клиентский код работает именно с этим интерфейсом, не завися
 * от конкретных реализаций ({@code BaroqueChair}, {@code GoticChair}, {@code ModernChair}).</p>
 *
 * <p>В учебном примере интерфейс пуст — в реальном коде здесь
 * были бы методы вроде {@code getStyle()} или {@code describe()}.</p>
 *
 * @see AbstractFurnitureFactory
 */
public interface Chair {
}

package pattern1_creation.create2_factory_method.code.example1_regular;

/**
 * Конкретный продукт — кнопка в стиле macOS.
 *
 * Реализует интерфейс Button с поведением, характерным для macOS UI.
 * Создаётся конкретным создателем MacDialog.
 */
public class MacButton implements Button {

    /**
     * Отрисовывает кнопку в стиле macOS (закруглённые края, эффект стекла).
     */
    @Override
    public void render() {
        System.out.println("[macOS] Отрисовка кнопки с закруглёнными краями и эффектом стекла.");
    }

    /**
     * Обрабатывает нажатие в стиле macOS (плавная анимация нажатия).
     */
    @Override
    public void click() {
        System.out.println("[macOS] Клик! Плавная анимация нажатия в стиле macOS.");
    }
}

package pattern1_creation.create2_factory_method.code.example1_regular;

/**
 * Конкретный продукт — кнопка в стиле Windows.
 *
 * Реализует интерфейс Button с поведением, характерным для Windows UI.
 * Создаётся конкретным создателем WindowsDialog.
 */
public class WindowsButton implements Button {

    /**
     * Отрисовывает кнопку в стиле Windows (прямоугольная, с тенью).
     */
    @Override
    public void render() {
        System.out.println("[Windows] Отрисовка прямоугольной кнопки с тенью.");
    }

    /**
     * Обрабатывает нажатие в стиле Windows (воспроизводит системный звук клика).
     */
    @Override
    public void click() {
        System.out.println("[Windows] Клик! Воспроизведение системного звука Windows.");
    }
}

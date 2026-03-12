package pattern1_creation.create2_factory_method.code.example1_regular;

/**
 * Конкретный создатель — диалог для платформы Windows.
 *
 * Переопределяет фабричный метод createButton() и возвращает кнопку
 * в стиле Windows. Вся остальная логика наследуется от Dialog.
 */
public class WindowsDialog extends Dialog {

    /**
     * Переопределённый фабричный метод.
     * Создаёт и возвращает кнопку в стиле Windows.
     *
     * @return новый объект WindowsButton
     */
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}

package pattern1_creation.create2_factory_method.code.example1_regular;

/**
 * Конкретный создатель — диалог для платформы macOS.
 *
 * Переопределяет фабричный метод createButton() и возвращает кнопку
 * в стиле macOS. Вся остальная логика наследуется от Dialog.
 */
public class MacDialog extends Dialog {

    /**
     * Переопределённый фабричный метод.
     * Создаёт и возвращает кнопку в стиле macOS.
     *
     * @return новый объект MacButton
     */
    @Override
    public Button createButton() {
        return new MacButton();
    }
}

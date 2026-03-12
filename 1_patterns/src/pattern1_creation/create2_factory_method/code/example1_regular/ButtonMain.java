package pattern1_creation.create2_factory_method.code.example1_regular;

/**
 * Демонстрация классического паттерна Фабричный метод (GoF).
 *
 * Ключевой момент: клиентский код работает с типом Dialog (абстракция),
 * а не с WindowsDialog или MacDialog напрямую. Это позволяет легко
 * менять поведение всей программы, просто изменив один объект-создатель.
 */
public class ButtonMain {

    public static void main(String[] args) {
        // Выбираем нужную платформу — создаём конкретный создатель
        // В реальном приложении здесь мог бы быть os.getName() или конфиг
        Dialog dialog;

        String os = "Windows"; // Имитируем определение операционной системы

        if (os.equals("Windows")) {
            dialog = new WindowsDialog(); // Создатель для Windows
        } else {
            dialog = new MacDialog();     // Создатель для macOS
        }

        // Дальше работаем только через абстрактный тип Dialog.
        // Не важно, какой именно диалог: метод render() один и тот же,
        // но внутри он создаёт нужный тип кнопки через фабричный метод.
        System.out.println("=== Отрисовка диалогового окна ===");
        dialog.render();

        System.out.println();

        System.out.println("=== Обработка нажатия ===");
        dialog.handleInput();

        System.out.println();

        // Меняем «платформу» — просто подменяем создатель
        System.out.println("=== Переключаемся на macOS диалог ===");
        dialog = new MacDialog();
        dialog.render();
        dialog.handleInput();
    }
}

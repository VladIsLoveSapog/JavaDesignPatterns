package pattern2_structural.struct1_composite.code.example2_file_system;

/**
 * Демонстрация паттерна Composite на примере файловой системы.
 *
 * <p>Пример показывает, как с помощью паттерна Компоновщик можно
 * единообразно работать с файлами и директориями: вычислять суммарный
 * размер и отображать древовидную структуру.
 *
 * <p><b>Структура дерева в данном примере:</b>
 * <pre>
 *   root/                              total: 12800 bytes
 *   ├── documents/                     total: 10240 bytes
 *   │   ├── report.docx               5120 bytes
 *   │   └── notes.txt                 5120 bytes
 *   ├── photos/                        total: 2048 bytes
 *   │   ├── vacation.jpg              1024 bytes
 *   │   └── portrait.png             1024 bytes
 *   └── readme.txt                    512 bytes
 * </pre>
 *
 * @see FileSystemComponent
 * @see FileSystemContainer
 * @see Directory
 * @see File
 */
public class Main {
    public static void main(String[] args) {
        // Создаём корневую директорию
        Directory root = new Directory("root");

        // Создаём поддиректорию documents с файлами
        Directory documents = new Directory("documents");
        documents.add(new File("report.docx", 5120));
        documents.add(new File("notes.txt", 5120));

        // Создаём поддиректорию photos с файлами
        Directory photos = new Directory("photos");
        photos.add(new File("vacation.jpg", 1024));
        photos.add(new File("portrait.png", 1024));

        // Добавляем поддиректории и файл в корень
        root.add(documents);
        root.add(photos);
        root.add(new File("readme.txt", 512));

        // Отображаем структуру всего дерева
        System.out.println("=== Структура файловой системы ===");
        root.showStructure("");

        // Вычисляем размеры отдельных элементов
        System.out.println("\n=== Размеры ===");
        System.out.println("Размер documents/: " + documents.getSize() + " bytes");
        System.out.println("Размер photos/: " + photos.getSize() + " bytes");
        System.out.println("Размер всего root/: " + root.getSize() + " bytes");
    }
}

package pattern1_creation.create4_prototype.code.example2_shallow_problem;

public class DocumentMain {
    /**
     * Демонстрация проблемы поверхностного копирования:
     * при shallow-копировании изменение копии затрагивает оригинал.
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        //Создаём оригинальный документ с двумя абзацами
        Document original = new Document("Курсовая работа");
        original.addParagraph(new Paragraph("Введение"));
        original.addParagraph(new Paragraph("Основная часть"));

        System.out.println("=== Поверхностное копирование (ПРОБЛЕМА) ===");
        Document shallowCopy = original.shallowClone();

        //Добавим абзац в копию
        shallowCopy.addParagraph(new Paragraph("Заключение"));
        shallowCopy.setTitle("Копия курсовой");

        //String title скопировался нормально (String неизменяемый)
        //Но список абзацев — общий!
        System.out.println("Оригинал:  " + original);
        System.out.println("Копия:     " + shallowCopy);
        System.out.println("Абзацев в оригинале: " + original.getParagraphs().size()
                + " (ожидали 2, получили 3!)");
        System.out.println();

        System.out.println("=== Глубокое копирование (ПРАВИЛЬНО) ===");
        //Создадим заново оригинал
        Document original2 = new Document("Диплом");
        original2.addParagraph(new Paragraph("Глава 1"));
        original2.addParagraph(new Paragraph("Глава 2"));

        Document deepCopy = original2.deepClone();

        //Добавим абзац только в копию
        deepCopy.addParagraph(new Paragraph("Глава 3"));
        deepCopy.setTitle("Копия диплома");

        System.out.println("Оригинал:  " + original2);
        System.out.println("Копия:     " + deepCopy);
        System.out.println("Абзацев в оригинале: " + original2.getParagraphs().size()
                + " (ожидали 2, получили 2 — всё верно!)");
    }
}

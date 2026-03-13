package pattern1_creation.create4_prototype.code.example1_deep_copy;

public class AutoMain {
    public static void main(String[] args) {
        //Конструктор на 10 параметров это сложно, много и страшно
        Auto blackAuto = new Auto(
                "Vasya",
                "Mercedez",
                new Engine(300, 5),
                Gearbox.AUTOMATIC,
                Color.BLACK,
                0,
                4,
                4,
                0,
                6000000L
        );

        //Для создания такого же автомобиля, но красного цвета
        //дабы не использовать гигантский конструктор, в котором еще можно и ошибок наделать
        //воспользуемся копированием
        Auto redAuto = null;
        try {
            redAuto = (Auto) blackAuto.clone();      //скопировали
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        //Проверим контракт метода clone()
        System.out.println("=== Проверка контракта clone() ===");
        System.out.println("Разные ссылки (blackAuto != redAuto): " + (blackAuto != redAuto));
        System.out.println("Одинаковый класс: " + (blackAuto.getClass() == redAuto.getClass()));
        System.out.println("Объекты равны (equals): " + blackAuto.equals(redAuto));

        System.out.println();
        redAuto.setColor(Color.RED);        //в скопированном авто поменяли цвет
        System.out.println("=== После изменения цвета клона ===");
        System.out.println("Оригинал: " + blackAuto);
        System.out.println("Клон:     " + redAuto);

        //Таким образом при помощи прототипа (копирования) мы создали схожие объекты
        //В обход сложных методов конфигурирования.
    }
}

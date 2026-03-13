package pattern1_creation.create4_prototype.code.example3_copy_constructor;

import java.util.ArrayList;
import java.util.Arrays;

public class UserProfileMain {
    /**
     * Демонстрация конструктора копирования и фабричного метода copyOf
     * как альтернативы Cloneable.
     */
    public static void main(String[] args) {
        //Создаём оригинальный профиль
        UserProfile original = new UserProfile(
                "ivan",
                "ivan@mail.ru",
                new Address("Москва", "ул. Пушкина, 10"),
                new ArrayList<>(Arrays.asList("Java", "Spring", "Паттерны"))
        );

        System.out.println("=== Конструктор копирования ===");
        UserProfile copy1 = new UserProfile(original);

        //Изменяем копию — оригинал не затронут
        copy1.setEmail("ivan_copy@mail.ru");
        copy1.getAddress().setCity("Санкт-Петербург");
        copy1.getInterests().add("Kotlin");

        System.out.println("Оригинал: " + original);
        System.out.println("Копия:    " + copy1);
        System.out.println();

        System.out.println("=== Фабричный метод copyOf ===");
        UserProfile copy2 = UserProfile.copyOf(original);

        copy2.setEmail("another@mail.ru");
        copy2.getAddress().setStreet("Невский проспект, 1");

        System.out.println("Оригинал: " + original);
        System.out.println("Копия:    " + copy2);
    }
}

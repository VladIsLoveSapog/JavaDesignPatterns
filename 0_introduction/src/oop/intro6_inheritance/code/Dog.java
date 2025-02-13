package oop.intro6_inheritance.code;

import java.util.Objects;

/**
 * Класс собаки, наследующий животное.
 * Добавляет породу и перегружает метод издать звук.
 */
public class Dog extends Animal {
    private String breed;   //порода собаки

    /**
     * Конструтор с параметрами
     *
     * @param name  от род. класса
     * @param age   от род. класса
     * @param breed порода собаки
     */
    public Dog(String name, int age, String breed) {
        // Вызов конструктора базового класса Animal
        super(name, age);
        this.breed = breed;
    }

    //Геттеры и сеттеры на остальные поля уже есть в родительском классе.
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    /**
     * Перегрузка метода makeSound
     */
    @Override
    public void makeSound() {
        System.out.println("Собака лает.");
    }

    /**
     * Стоит быть осторожным с перегрузкой метода hashCode при наследовании.
     * Корректное переопределение метода hashCode при наследовании.
     *
     * @return hash объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                breed
        );
    }

    /**
     * Корректное переопределение метода equals при наследовании.
     *
     * @param o объект для сравнения
     * @return true если объекты равны. Иначе false.
     */
    @Override
    public boolean equals(Object o) {
        //Если ссылки указывают на один и тот же объект, то объект равен сам себе
        if (this == o) return true;

        //Использование метода equals род.класса где уже определено корректное сравнение n полей
        if (!super.equals(o)) return false;

        //отдельно проверяем поля, которые отсутсвуют в родительском классе
        Dog dog = (Dog) o;
        if (!super.equals(o)) return false;
        return Objects.equals(breed, dog.breed);
    }

    /**
     * Корректно переопределенный метод toString.
     *
     * @return строкове представление объекта.
     */
    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

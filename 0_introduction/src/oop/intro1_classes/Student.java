package oop.intro1_classes;

import java.util.Objects;

/**
 * Класс студента - обычный оопшный класс с полями и методами.
 */
public class Student {
    private int age;             //возраст студента

    private Gender gender;      //пол студента

    private String group;       //группа студента

    private String department;  //кафедра студента

    /**
     * Геттер для поля age
     *
     * @return возраст студента
     */
    public int getAge() {
        return age;
    }

    /**
     * Сеттер для поля age
     *
     * @param age возраст котоорый присваиивается студенту
     */
    public void setAge(int age) {
        if (age < 16) throw new IllegalArgumentException("Недопустимый возраст студента");
        this.age = age;
    }

    /**
     * Геттер для поля gender
     *
     * @return пол студента
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Сеттер для поля gender
     *
     * @param gender пол, который присваивается студенту
     */
    public void setGender(Gender gender) {
        if (Objects.isNull(gender)) throw new IllegalArgumentException("Пол не может быть пустым");
        this.gender = gender;
    }

    /**
     * Геттер для поля group
     *
     * @return группу студента
     */
    public String getGroup() {
        return group;
    }

    /**
     * Сеттер для поля group
     *
     * @param group группа, которая присваивается студенту
     */
    public void setGroup(String group) {
        if (Objects.isNull(group) || group.trim().isEmpty())
            throw new IllegalArgumentException("Группа не может быть пустой");
        this.group = group;
    }

    /**
     * Геттер для поля department
     *
     * @return кафедру студента
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Сеттер для поля department
     *
     * @param department кафедра, которая присваивается студенту
     */
    public void setDepartment(String department) {
        if (Objects.isNull(group) || group.trim().isEmpty())
            throw new IllegalArgumentException("Кафедра не может быть пустой");
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", gender=" + gender +
                ", group='" + group + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}

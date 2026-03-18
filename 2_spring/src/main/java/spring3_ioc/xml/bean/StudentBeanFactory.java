package spring3_ioc.xml.bean;

/**
 * Фабрика студентов
 */
public class StudentBeanFactory {
    /**
     * Фабричный метод создания студента
     */
    public static Student createStudent(String name, Integer age, String group, String creationType) {
        return new Student(name, age, group, creationType);
    }
}

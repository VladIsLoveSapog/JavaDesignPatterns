package spring7_scope.singleton;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SingletonMain {
    public static void main(String[] args) {
        //Получение контекста из XML файла (resources/singletonContext.xml)
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("singletonContext.xml");

        //Получение объеектов
        var firstPerson = context.getBean(SingletonPerson.class);
        var secondPerson = context.getBean(SingletonPerson.class);

        System.out.println(firstPerson);
        System.out.println(secondPerson);

        //Ссылки ссылаются на один и тот же объект
        //объект индетичен сам себе
        System.out.println(firstPerson == secondPerson);
        System.out.println(firstPerson.equals(secondPerson));
    }
}

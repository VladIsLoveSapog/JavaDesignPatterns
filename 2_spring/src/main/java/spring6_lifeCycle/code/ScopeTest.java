package spring6_lifeCycle.code;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScopeTest {
    public static void main(String[] args) {
        //Считывание конфигурационного класса
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationScanConfig.class);

        //Поскольку бины Person и Cat имеют scope singleton, то в выводе мы увидим, что
        //у них отработают конструкторы и init методы без явного получения бина через getBean()
        //Помимо того cat внедряется в person

        //Dog имеет scope prototype
        //Конструкторы и init-методы сработают только при явном обращении к Spring через
        //getBean(), потому объекты будут созданы только тогда
        System.out.println("\nСоздание объектов типа Dog с scope prototype: ");
        Dog myDog = context.getBean("dogBean", Dog.class);
        Dog yourDog = context.getBean("dogBean", Dog.class);
        System.out.println();

        //У Person и Cat отработают destroy-методы, т.к. они singleton
        //У не одного объекта типа Dog destroy метод не отработает
        context.close();
    }
}

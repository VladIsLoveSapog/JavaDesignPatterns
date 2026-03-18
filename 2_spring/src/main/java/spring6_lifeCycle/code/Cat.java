package spring6_lifeCycle.code;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

/**
 * Бин кота
 * Singleton
 * Бин singleton создается при запуске Spring контейнера.
 * При запуске примера вы увидете, что вывод в конструкторе сработает
 * без явного получения бина при помощи getBean()
 */
@Component("catBean")
public class Cat implements Pet {

    public Cat() {
        System.out.println("Cat bean is created");
    }

    @Override
    public void say() {
        System.out.println("Meow-Meow!");
    }

    @PostConstruct
    private void init() {
        System.out.println("Class cat: init method");
    }

    //Метод destroy не работает для prototype
    @PreDestroy
    private void destroy() {
        System.out.println("Class cat: destroy method");
    }
}

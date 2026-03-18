package spring6_lifeCycle.code;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Бин собаки
 * Prototype
 * Бин prototype создается при явном обращении к Spring контейнеру
 * при помощи getBean()
 * Init-method сработает для каждого созданного бина, однако destroy для данной
 * области видимости не работает.
 */
@Component("dogBean")
@Scope("prototype")
public class Dog implements Pet {

    public Dog() {
        System.out.println("Dog bean is created");
    }

    @Override
    public void say(){
        System.out.println("Bow-Wow!");
    }

    //Для аннотаций @PostConstruct и @PreDestroy необходимо добавить
    //maven зависимости javax.annotation-api
    //Данные анотации нужны вместо прописывания init и destroy в xml файле
    @PostConstruct
    private void init() {
        System.out.println("Class dog: init method");
    }

    //Метод destroy не работает для prototype
    @PreDestroy
    private void destroy() {
        System.out.println("Class dog: destroy method");
    }
}

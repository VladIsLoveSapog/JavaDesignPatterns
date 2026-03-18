package spring7_scope.prototype;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PrototypeMain {
    public static void main(String[] args) {
        //Получение контекста из конфигурационного класса
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationScanConfig.class);

        //Получение объектов прототипов
        var firstService = context.getBean(PrototypeService.class);
        var secondService = context.getBean(PrototypeService.class);

        System.out.println(firstService);
        System.out.println(secondService);

        //Проверяем, что ссылки ссылаются на разные объекты - false
        System.out.println(firstService == secondService);

        //Проверяем что объекты одинаковы по содержанию - true
        System.out.println(firstService.equals(secondService));

        //Меняем значение в одном из прототипов
        secondService.setPort("5432");

        System.out.println(firstService);
        System.out.println(secondService);

        //Теперь они не равны
        System.out.println(firstService == secondService);
        System.out.println(firstService.equals(secondService));
    }
}

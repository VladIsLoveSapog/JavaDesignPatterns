package spring3_ioc.mix;

import spring3_ioc.mix.bean.ComponentByConstructor;
import spring3_ioc.mix.bean.ComponentBySetters;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MixContextMain {
    public static void main(String[] args) {
        //Чтение контекста из xml файла с автосканированием пакетов
        //В файле указаны пакеты для сканирования
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("autoScanContext.xml");

        //Чтение компонента, инициализирующегося через конструктор
        var componentByConstructor = context.getBean("componentByConstructor", ComponentByConstructor.class);
        System.out.println(componentByConstructor);

        //Чтение компонента, инициализирующегося через сеттеры
        var componentBySetters = context.getBean("bySetters", ComponentBySetters.class);
        System.out.println(componentBySetters);

        //Контекст необходимо закрывать для корректного освобождения ресурсов.
        context.close();
    }
}

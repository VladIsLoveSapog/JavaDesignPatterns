package spring4_di.xml;

import spring4_di.xml.bean.AnotherBean;
import spring4_di.xml.bean.DifficultBean;
import spring4_di.xml.bean.ExampleBean;
import spring4_di.xml.bean.FlexibleBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiXMLMain {
    public static void main(String[] args) {
        //Считавание XML-файла инициализации бинов (resources/dependencyInjectionContext.xml)
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dependencyInjectionContext.xml");

        //Получения бина AnotherBean
        var anotherBean = context.getBean(AnotherBean.class);
        System.out.println(anotherBean);
        System.out.println(anotherBean.getDescription());

        //Получения бина ExampleBean
        var exampleBean = context.getBean(ExampleBean.class);
        System.out.println(exampleBean);
        System.out.println(exampleBean.getDescription());

        //Получени бина DifficultBean, внутрь которого внедряется бин,
        //id которого передается в качестве параметра конструктора в XML-файле.
        //Демонстрация: внедрение через конструктор (constructor-arg ref)
        var difficultBean = context.getBean(DifficultBean.class);
        System.out.println(difficultBean);
        System.out.println(difficultBean.getExampleBean().getDescription());

        System.out.println("----------------------------------------");

        //Получение бина FlexibleBean, в который зависимость внедряется
        //через сеттер при помощи <property ref="..."/> в XML-файле.
        //Демонстрация: внедрение через сеттер (property ref)
        var flexibleBean = context.getBean(FlexibleBean.class);
        System.out.println(flexibleBean);
        System.out.println(flexibleBean.getExampleBean().getDescription());
    }
}
package spring3_ioc.code.components;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainB {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScanComponentConfig.class);
        var component = context.getBean(ComponentB.class);
        System.out.println(component);
        context.close();
    }
}

package spring3_ioc.code.beanfactory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainA {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScanBeanConfig.class);
        var bean = context.getBean(ComponentA.class);
        System.out.println(bean);
        context.close();
    }
}

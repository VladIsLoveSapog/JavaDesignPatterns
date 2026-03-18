package spring5_profiles.code;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProfileMain {
    public static void main(String[] args) {
        //Создание контекста с профилем "dev"
        var devContext = new AnnotationConfigApplicationContext();
        devContext.getEnvironment().setActiveProfiles("dev");
        devContext.register(ProfileConfig.class);
        devContext.refresh();

        var devDataSource = devContext.getBean(IDataSource.class);
        System.out.println("Активный профиль: dev");
        System.out.println(devDataSource);
        System.out.println("URL: " + devDataSource.getUrl());
        System.out.println("Driver: " + devDataSource.getDriverClassName());

        devContext.close();

        System.out.println("----------------------------------------");

        //Создание контекста с профилем "prod"
        var prodContext = new AnnotationConfigApplicationContext();
        prodContext.getEnvironment().setActiveProfiles("prod");
        prodContext.register(ProfileConfig.class);
        prodContext.refresh();

        var prodDataSource = prodContext.getBean(IDataSource.class);
        System.out.println("Активный профиль: prod");
        System.out.println(prodDataSource);
        System.out.println("URL: " + prodDataSource.getUrl());
        System.out.println("Driver: " + prodDataSource.getDriverClassName());

        prodContext.close();
    }
}

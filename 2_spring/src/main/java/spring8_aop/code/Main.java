package spring8_aop.code;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Вообще аспекты это целый отдельный и удивительный мир...
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        //Получение бинов библиотек
        ILibrary uniLib = context.getBean(UniLibrary.class);
        ILibrary schoolLib = context.getBean(SchoolLibrary.class);

        //Вызываем методы взятия книги из разных библиотек
        //На каждый метод сработает LoggingAspect.beforeGetBookAdvice()
        uniLib.getBook();
        schoolLib.getBook();

        System.out.println("--------------------------------------");

        //Вызваем метод возвращения книги в разные библиотеки
        //На каждый метод срабатывает свой after returning advice
        uniLib.returnBook();
        schoolLib.returnBook();

        System.out.println("--------------------------------------");

        //Вызваем метод вручения книги человеку
        //У этого метоад есть Advice типа around
        uniLib.giveBookToPerson(
                "Учебник по ШППО",
                new Person("Ivan", "Ivanov", 21));

        System.out.println("--------------------------------------");

        //Успешный поиск книги — сработает @After
        String result = uniLib.findBook("Война и мир");
        System.out.println("Результат поиска: " + result);

        System.out.println("--------------------------------------");

        //Неуспешный поиск книги — сработают @AfterThrowing + @After
        try {
            uniLib.findBook("Несуществующая книга");
        } catch (RuntimeException e) {
            System.out.println("Поймано исключение в main: " + e.getMessage());
        }

        //Закрытие контекста
        context.close();
    }
}

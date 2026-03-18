package spring8_aop.code.aspects;

import spring8_aop.code.Person;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Класс аспект должен быть компонентом.
 * <p>
 * Aspect указывает на то что класс является аспектом.
 * Работа Spring с аспектами отличается от обычных компонентов.
 */
@Component
@Aspect
public class LoggingAspect {

    /**
     * Pointcut-декларация для методов returnBook().
     * Вместо дублирования выражения в каждом Advice, можно объявить его один раз
     * и ссылаться по имени метода.
     */
    @Pointcut("execution(public void returnBook())")
    private void returnBookPointcut() {}

    /**
     * Pointcut-декларация для методов findBook(String).
     */
    @Pointcut("execution(public String findBook(String))")
    private void findBookPointcut() {}

    /**
     * Advice, помеченный @Before срабатывает до выполнения основного метода.
     * В аннотации @Before описывается Pointcut.
     * Pointcut - выражение, описывающее где должен быть применен Advice.
     * <p>
     * Название Advice-ов обычно формируется из <advice_type>+<methodName>
     * <p>
     * Spring AOP использует AspectJ Pointcut expression language. Т.е.
     * определённые правила в написании выражений для создания
     * Pointcut.
     * <p>
     * В данном Pointcut-е не указан declaring-type-pattern? поэтому он подойдет под любой
     * метод public void getBook() любого класса.
     * В данном случае он сработает и для UniLibrary.getBook() и SchoolLibrary.getBook()
     * <p>
     * ВАЖНО!!! Название самого Advice не играет никакой роли! Важен его Pointcut!!!
     * <p>
     * JoinPoint дает доступ к информации о сигнатуре и параметрах перехваченного метода.
     */
    @Before("execution(public void getBook())")
    public void beforeGetBookAdvice(JoinPoint joinPoint) {
        System.out.println("beforeGetBookAdvice: попытка получить книгу. Метод: "
                + joinPoint.getSignature().getName());
    }

    /**
     * Advice, помеченный @AfterReturning выполняется только после нормального
     * окончания метода с основной логикой.
     * <p>
     * ВАЖНО!!! Название самого Advice не играет никакой роли! Важен его Pointcut!!!
     * execution(public void returnBook()) будет работать для обоих библиотек!!!
     * Для разрешения данного конфликта комбинируем именованный pointcut с declaring-type-pattern
     * через оператор &&
     */
    @AfterReturning("returnBookPointcut() && execution(* spring8_aop.code.UniLibrary.*(..))")
    public void afterUniReturnBookAdvice() {
        System.out.println("afterUniReturnBookAdvice: книга возвращена в UniLibrary");
    }

    /**
     * Аналогичный Advice для SchoolLibrary. Используем именованный pointcut returnBookPointcut()
     * в комбинации с declaring-type-pattern для SchoolLibrary.
     */
    @AfterReturning("returnBookPointcut() && execution(* spring8_aop.code.SchoolLibrary.*(..))")
    public void afterSchoolReturnBookAdvice() {
        System.out.println("afterSchoolReturnBookAdvice: книга возвращена в SchoolLibrary");
    }

    /**
     * Как не трудно догадаться Advice, помеченый @Around выполняется "вокруг метода",
     * т.е. до и после его отрабаывания.
     * <p>
     * Очень сложный и сильный тип Advice, потому что в нем можно:
     * 1. Произвести какие-либо действия до работы target метода (даже перехватить
     * входные параметры).
     * 2. Произвести какие-либо действия после работы target метода (даже перехватить
     * результаты работы метода).
     * 3. Предпринять какие-либо действия, если из target метода выбрасывается исключение.
     *      исключения также можно перехватывать и обрабатывать.
     */
    @Around("execution(public void giveBookToPerson(String, spring8_aop.code..Person))")
    public Object AroundDiveBookToPersonAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //Действия до выполнения метода!
        //Перехват параметров, переданных в основной метод.
        Object[] args = proceedingJoinPoint.getArgs();

        //Преобразование параметров по нужным типам
        String bookName = object2String(args[0]).orElse("Неизвестный");
        Person person = object2Person(args[1]).orElseThrow();

        System.out.println(person + " пытается получить книгу " + bookName);

        //Запуск работы основного метода и получение его результатов работы
        //Здесь мы также можем что-то изменить в результатах работы метода
        Object targetMethodResult = proceedingJoinPoint.proceed();

        //Действия после выполнения метода!
        System.out.println(person + " получил книгу " + bookName + " без проблем.");

        //К сожелению и параметры и возвращаемый тип перехватывается в виде типа Object
        //из-за чего приходить городить проверки и преобразования...

        //Менять что-то в предаваемых и возвращаемых параметрах не следует ни в коем случае!!!
        //Данные механизмы нужны больше для проверки и логирования результатов.
        //Не портите жизнь ни себе, ни другим :)

        //Возвращаем ранее полученне результаты работы метода
        //Это обязательное действие, иначе результаты потеряются
        //и скорее всего у вас где-то что-то упадет, выкинет exception и тд и тп...
        return targetMethodResult;
    }

    /**
     * Advice, помеченный @AfterThrowing срабатывает только если метод
     * с основной логикой выбросил исключение.
     * <p>
     * Используем именованный pointcut findBookPointcut().
     * В параметре throwing указываем имя переменной для перехвата исключения.
     */
    @AfterThrowing(pointcut = "findBookPointcut()", throwing = "exception")
    public void afterThrowingFindBookAdvice(JoinPoint joinPoint, Throwable exception) {
        System.out.println("afterThrowingFindBookAdvice: метод " + joinPoint.getSignature().getName()
                + " выбросил исключение: " + exception.getMessage());
    }

    /**
     * Advice, помеченный @After срабатывает всегда после окончания метода
     * с основной логикой — и при нормальном завершении, и при исключении.
     * <p>
     * Используем именованный pointcut findBookPointcut().
     */
    @After("findBookPointcut()")
    public void afterFindBookAdvice(JoinPoint joinPoint) {
        System.out.println("afterFindBookAdvice: метод " + joinPoint.getSignature().getName()
                + " завершил работу (всегда срабатывает).");
    }

    /**
     * Вспомогательный метод приведения объекта к строке
     */
    public Optional<String> object2String(Object obj) {
        if (obj instanceof String) return Optional.of((String) obj);
        return Optional.empty();
    }

    /**
     * Вспомогательный метод приведения объекта к человеку
     */
    public Optional<Person> object2Person(Object obj) {
        if (obj instanceof Person) return Optional.of((Person) obj);
        return Optional.empty();
    }
}

package spring8_aop.code;

import org.springframework.stereotype.Component;

/**
 * Класс школьной библиотеки - обычный компонент.
 * ВАЖНО!!! Аспекты работают только на бинах!
 */
@Component
public class SchoolLibrary implements ILibrary {
    /**
     * Простой метод взяти книги, который не содержит сложной и постронней логики
     */
    @Override
    public void getBook() {
        System.out.println("Мы берем книгу из SchoolLibrary");
    }

    @Override
    public void returnBook() {
        System.out.println("Мы возвращаем книгу в SchoolLibrary");
    }

    /**
     * @param bookName имя книги
     * @param person   человек, которому выдается книга
     */
    @Override
    public void giveBookToPerson(String bookName, Person person) {
        System.out.println(person.name() + "получил книгу " + bookName + "в SchoolLibrary");
    }

    @Override
    public String findBook(String bookName) {
        if ("Букварь".equals(bookName)) {
            System.out.println("SchoolLibrary: книга найдена — " + bookName);
            return "Букварь, 2020";
        }
        throw new RuntimeException("Книга \"" + bookName + "\" не найдена в SchoolLibrary!");
    }
}

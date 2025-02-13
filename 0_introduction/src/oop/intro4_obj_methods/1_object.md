# Object

Класс ```Object``` – это базовый класс в Java, от которого неявно наследуются все остальные классы. Он находится в
пакете ```java.lang```, который импортируется по умолчанию, поэтому нет необходимости явно его импортировать.

## Основные возможности класса Object

Класс ```Object``` определяет набор методов, которые доступны во всех объектах Java. Вот некоторые из них:

- ```toString()``` - метод возвращает строковое представление объекта. По умолчанию он выводит имя класса и хэш-код
  объекта, но его часто переопределяют для более информативного вывода.
- ```equals(Object obj)``` - метод сравнения объектов на равенство. По умолчанию сравнение происходит по ссылкам, но его
  обычно переопределяют, если необходимо сравнение содержимого объектов.
- ```hashCode()``` - возвращает числовое значение (хэш-код) объекта, которое используется, например, в коллекциях
  типа ```HashMap``` для быстрого поиска.
- ```getClass()``` - возвращает объект класса Class, который описывает информацию о типе объекта во время выполнения.
- ```clone()``` - позволяет создавать копию объекта. Этот метод имеет свои особенности и требует реализации
  интерфейса ```Cloneable```.
- ```notify()```, ```notifyAll()```, ```wait()``` - Методы, связанные с управлением потоками, используются для
  организации синхронизации.

## Пример неявного наследования Object

```java
public class MyClass {
    private int value;

    public MyClass(int value) {
        this.value = value;
    }

    // Переопределим метод toString() для более понятного вывода объекта
    @Override
    public String toString() {
        return "MyClass { value = " + value + " }";
    }

    public static void main(String[] args) {
        MyClass obj = new MyClass(10);

        // Вызов метода toString(), который наследуется от Object, но переопределён в нашем классе
        System.out.println(obj.toString());

        // Получение информации о классе через метод getClass(), унаследованный от Object
        System.out.println("Класс объекта: " + obj.getClass().getName());
    }
}
```

- Класс MyClass неявно расширяет класс ```Object```, поэтому он унаследует все его методы, такие как ```toString()```
  и ```getClass()```.
- Мы переопределили метод ```toString()```, чтобы выводить более информативное представление объекта.
- Методы ```toString()``` и ```getClass()``` демонстрируют, что даже самый простой класс имеет базовую функциональность,
  предоставляемую ```Object```.

## Нужные методы

- ```toString()```
- ```equals()```
- ```hashCode()```

### toString()

Метод ```toString()``` в Java предназначен для возврата строкового представления объекта. По умолчанию он возвращает
строку вида ```«ИмяКласса@хэшкод»```, что обычно не очень информативно. Именно поэтому его часто переопределяют для
удобства отладки, логирования и отображения данных.

**Основные рекомендации по переопределению ```toString()```:**

1. **Отображение всех значимых полей:**  В строковом представлении следует включить те поля, которые наиболее полно
   описывают состояние объекта.
2. **Учитывайте вложенные объекты:** Если класс содержит другие объекты (например, поля других классов), в toString()
   можно вызвать их toString() для получения их представления. При этом важно проверять, что вложенный объект не
   равен ```null```.
3. **Форматирование:**   Рекомендуется использовать понятный и структурированный формат. Можно использовать как ручное
   форматирование, так и библиотеки (например, Apache Commons Lang с классом ```ToStringBuilder``` или аннотации
   Lombok).
4. **Избегайте рекурсии:**  Если объекты ссылаются друг на друга (циклические зависимости), это может привести к
   бесконечной рекурсии. Необходимо предусмотреть обработку таких ситуаций.

#### Пример с вложенными объектами

Предположим, у нас есть класс ```Person```, содержащий вложенный объект ```Address```:

```java
import java.util.Objects;

public class Address {
    private String city;
    private String street;
    private int houseNumber;

    public Address(String city, String street, int houseNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }
}

public class Person {
    private String name;
    private int age;
    private Address address; // Вложенный объект

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + (Objects.isNull(address) ? "null" : address) +
                '}';
    }

    public static void main(String[] args) {
        Address addr = new Address("Москва", "Ленина", 10);
        Person person = new Person("Иван", 30, addr);
        System.out.println(person);
    }
}
```

- **Класс Address:** Здесь мы переопределили ```toString()```, чтобы вернуть строку, описывающую город, улицу и номер
  дома.
- **Класс Person:** Переопределённый метод ```toString()``` включает поля ```name``` и ```age```, а также
  вызывает ```toString()``` вложенного объекта ```address```. Перед вызовом ```toString()``` для ```address```
  производится проверка на ```null```, чтобы избежать ```NullPointerException```.'
- **Результат:** При вызове ```System.out.println(person)``` будет выведено что-то
  вроде: ```Person{name='Иван', age=30, address=Address{city='Москва', street='Ленина', houseNumber=10}}```

### equals()

Метод ```equals()``` отвечает за сравнение объектов на предмет их _"логического равенства"_ (то есть равенства значений
их полей), а не их тождественности (то есть, что они занимают одно и то же место в памяти). По умолчанию
метод ```equals()``` в классе ```Object``` сравнивает ссылки (то есть,``` this == o```), что зачастую не подходит, если
мы хотим сравнивать объекты по содержимому.

**Основные принципы переопределения ```equals()```:**

- **Рефлексивность:** объект должен быть равен самому себе.
- **Симметричность:** если ```a.equals(b)``` возвращает ```true```, то и ```b.equals(a)``` должно возвращать ```true```.
- **Транзитивность:** если ```a.equals(b)``` и ```b.equals(c)```, то ```a.equals(c)``` должно возвращать ```true```.
- **Последовательность (консистентность):** многократные вызовы ```equals()``` должны возвращать один и тот же
  результат, если состояние объектов не изменилось.
- **Сравнение с ```null```:** для любого объекта a вызов ```a.equals(null)``` должен возвращать ```false```.

#### Шаблон реализации

1. Сравнение ссылок:

```
if (this == o) return true;
```

2. Проверка на ```null``` и сравнение классов (можно использовать ```getClass()``` или ```instanceof``` в зависимости от
   требований):

```
if (o == null || getClass() != o.getClass()) return false;
```

3. Приведение типа:

```
Person person = (Person) o;
```

4. Сравнение значимых полей, используя ```Objects.equals()``` для объектов:

```
return age == person.age &&
       Objects.equals(name, person.name) &&
       Objects.equals(address, person.address);
```

#### Переопределение equals() с вложенными объектами

Если класс содержит вложенные объекты, то для корректного сравнения их состояний необходимо использовать их
метод ```equals()```. При этом важно, чтобы и вложенные классы также корректно переопределяли ```equals()```, иначе
сравнение может дать неверный результат.

**Пример класса Person с вложенным Address:**

```java
public class Address {
    private String city;
    private String street;
    private int houseNumber;

    public Address(String city, String street, int houseNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return houseNumber == address.houseNumber &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, houseNumber);
    }
}

public class Person {
    private String name;
    private int age;
    private Address address; // Вложенный объект

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, address);
    }
}
```

#### Почему необходимо переопределять equals() и hashCode() вместе?

Контракт между ```equals()``` и ```hashCode()``` гласит, что если два объекта равны (то есть ```equals()```
возвращает ```true```), то они должны иметь одинаковый ```hashCode()```. Это особенно важно при работе с коллекциями,
использующими хеширование (например, ```HashMap```, ```HashSet```):
Если ```hashCode()``` не переопределён, два логически равных объекта могут оказаться в разных бакетах, и коллекция не
сможет их правильно обнаружить. Это приведёт к неожиданным результатам при поиске, удалении или проверке наличия
объектов в коллекции.

### hashCode()

Метод ```hashCode()``` генерирует числовое представление объекта, которое используется, например, в
хеш-таблицах (```HashMap```, ```HashSet``` и др.). Его цель – равномерно распределять объекты по «бакетам» для быстрого
поиска. При переопределении ```hashCode()``` необходимо учитывать все значимые поля объекта, включая вложенные объекты,
чтобы два равных объекта (согласно ```equals()```) возвращали одинаковое ```hashCode```.

**Основные моменты переопределения ```hashCode()```:**

1. **Контракт ```hashCode()```:**
    - Если два объекта равны согласно методу ```equals()```, то они должны иметь одинаковый ```hashCode```.
    - Если объекты не равны, их ```hashCode``` желательно различать для уменьшения количества коллизий (но строгое
      требование отсутствует).
    - Результат метода ```hashCode()``` должен оставаться неизменным для объекта в течение его жизни, если его поля не
      изменяются.

2. **Учет вложенных объектов:** Если класс содержит вложенные объекты, то их hashCode также должен учитываться при
   вычислении хеш-кода. При этом вложенные объекты должны корректно переопределять свои методы ```hashCode()```
   и ```equals()```.

```java

@Override
public int hashCode() {
    return Objects.hash(name, age, address);
}
```

3. **Использование стандартных методов:** В ```Java``` удобно применять класс ```Objects```, который предоставляет
   методы для генерации хеш-кода и сравнения объектов. Это упрощает реализацию и снижает вероятность ошибок.

#### Пример переопределения ```hashCode()``` с вложенными объектами

Предположим, у нас есть класс ```Address``` и класс ```Person```, где ```Person``` содержит поле address
типа ```Address```:

```java
public class Address {
    private String city;
    private String street;
    private int houseNumber;

    public Address(String city, String street, int houseNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return houseNumber == address.houseNumber &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, houseNumber);
    }
}

public class Person {
    private String name;
    private int age;
    private Address address; // Вложенный объект

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        // Учитываем все значимые поля, включая вложенный объект address
        return Objects.hash(name, age, address);
    }
}
```

- Метод ```hashCode()``` класса ```Person``` использует ```Objects.hash()``` для объединения хеш-кодов
  полей ```name```, ```age``` и ```address```.
- Если ```address``` равен ```null```, ```Objects.hash()``` корректно обработает этот случай (возвращая значение 0
  для ```null```).
- При этом класс ```Address``` также имеет корректно переопределённые ```equals()``` и ```hashCode()```, что
  гарантирует, что состояние вложенного объекта будет учтено.


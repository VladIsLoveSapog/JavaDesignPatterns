# Static

Ключевое слово ```static``` в Java используется для объявления членов класса (переменных, методов, блоков и вложенных
классов), которые принадлежат самому классу, а не конкретным его экземплярам. Рассмотрим подробнее, что это означает.

## Static переменные

**Static-переменные** создаются один раз при загрузке класса и существуют независимо от количества созданных объектов.
Все экземпляры класса разделяют одно и то же значение **static-переменной**.

```java
public class Counter {
    // Статическая переменная общего счетчика
    public static int count = 0;

    public Counter() {
        count++;  // При создании нового объекта увеличиваем общий счетчик
    }
}

public class Main {
    public static void main(String[] args) {
        new Counter();
        new Counter();
        System.out.println("Количество объектов: " + Counter.count); // Выведет 2
    }
}
```

## Static методы

**Static-методы** принадлежат классу, а не его экземплярам. Их можно вызывать без создания объекта. Внутри *
*static-метода** нельзя напрямую обращаться к нестатическим полям и методам класса, так как они зависят от конкретного
объекта.

```java
public class MathUtils {
    // Статический метод для сложения двух чисел
    public static int add(int a, int b) {
        return a + b;
    }
}

public class Main {
    public static void main(String[] args) {
        int sum = MathUtils.add(5, 3);
        System.out.println("Сумма: " + sum); // Выведет 8
    }
}
```

## Static блоки

**Static-блоки** используются для инициализации **static-переменных** или выполнения одного раза кода при загрузке
класса.

```java
public class Config {
    public static String configValue;

    static {
        // Выполнится при загрузке класса
        configValue = "Default configuration";
        System.out.println("Static блок выполнен");
    }
}
```

## Статические классы

### Топ-уровневые классы

В Java **топ-уровневые классы** не могут быть объявлены как ```static```. Каждый класс верхнего уровня уже является
самостоятельной сущностью, и понятие «статичности» к нему неприменимо.

### Вложенные (внутренние) классы

Однако **вложенные классы** могут быть объявлены как ```static```. Такие классы называются **статическими вложенными
классами** (static nested classes). Они не имеют неявной ссылки на объект внешнего класса и могут использоваться без
создания экземпляра внешнего класса.

```java
public class OuterClass {
    private int instanceValue = 10;

    // Статический вложенный класс
    public static class NestedStaticClass {
        public void display() {
            System.out.println("Это статический вложенный класс.");
            // Обратите внимание: нельзя напрямую обратиться к нестатическим полям внешнего класса
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Создаем объект статического вложенного класса без создания объекта OuterClass
        OuterClass.NestedStaticClass nestedObject = new OuterClass.NestedStaticClass();
        nestedObject.display();
    }
}
```
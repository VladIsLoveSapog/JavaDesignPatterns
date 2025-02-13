# Коллекции

**Коллекции в Java** — структуры данных, которые используются для хранения и обработки данных. Java предоставляет
несколько стандартных коллекций, каждая из которых имеет свои особенности и предназначение. Рассмотрим следующие
основные коллекции:

- ```List``` и его реализации: ```ArrayList```, ```LinkedList```.
- ```Set``` и его реализация: ```HashSet```.
- ```Map``` и его реализация: ```HashMap```.

## List

```List``` — это упорядоченная коллекция, которая позволяет хранить дубликаты элементов и предоставляет доступ к
элементам по индексу. Реализациями интерфейса ```List``` являются такие классы, как ```ArrayList``` и ```LinkedList```.

- **Упорядоченная коллекция**: элементы в List хранятся в порядке их добавления.
- **Доступ по индексу**: можно обращаться к элементам через индексы (например, ```list.get(1)```).
- **Поддержка дубликатов**: ```List``` допускает хранение одинаковых элементов.

```java
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");

        // Перебор элементов через цикл for-each
        for (String fruit : list) {
            System.out.println(fruit);
        }

        // Доступ к элементам по индексу
        System.out.println("Элемент на позиции 1: " + list.get(1));
    }
}
```

### Класс ```ArrayList```

```ArrayList``` — это динамический массив, который реализует интерфейс ```List```. Его основной особенностью является
быстрая работа с элементами по индексу, однако вставка или удаление элементов в середине списка может быть медленной,
так как требуется сдвигать элементы.

- **Динамический массив**: автоматически изменяет свой размер.
- **Быстрый доступ по индексу**: операции чтения по индексу выполняются за время ```O(1)```.
- **Медленная вставка/удаление**: для вставки или удаления элементов в середине списка требуется сдвигать элементы.

### [Пример](list%2FArrayListMain.java)

```java
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        // Вывод элементов через цикл for-each
        for (int number : numbers) System.out.println(number);

        // Удаление элемента
        numbers.remove(1); // Удаление элемента на позиции 1 (значение 20)
        System.out.println("После удаления: " + numbers);
    }
}
```

### Класс ```LinkedList```

```LinkedList``` — это реализация двусвязного списка, также реализует интерфейс ```List```. Основное отличие
от ```ArrayList``` заключается в том, что добавление и удаление элементов в середине списка выполняется быстрее (за
время ```O(1)```), но доступ к элементам по индексу медленнее ```(O(n))```.

- **Двусвязный список**: каждый элемент хранит ссылки на предыдущий и следующий элементы.
- **Быстрая вставка и удаление**: добавление и удаление элементов в начале или конце списка выполняется быстро.
- **Медленный доступ по индексу**: доступ к элементам выполняется за линейное время ```O(n)```.

### [Пример](list%2FLinkedListMain.java)

```java
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        List<String> animals = new LinkedList<>();
        animals.add("Cat");
        animals.add("Dog");
        animals.add("Elephant");

        // Добавление элемента в начало списка
        animals.addFirst("Tiger");

        // Перебор элементов
        for (String animal : animals) System.out.println(animal);

        // Удаление первого элемента
        animals.removeFirst();
        System.out.println("После удаления первого: " + animals);
    }
}
```

## Set

```Set``` — это коллекция, которая не допускает дубликатов. ```Set``` не гарантирует порядок элементов, хотя его
конкретные реализации могут предоставлять упорядоченные коллекции (например, ```TreeSet```).

- **Нет дубликатов**: в ```Set``` хранятся только уникальные элементы.
- **Нет гарантии порядка**: порядок элементов не гарантируется, за исключением специальных реализаций, таких
  как ```TreeSet```.

```java
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Orange");
        set.add("Apple"); // Дубликат не добавится

        // Перебор элементов через цикл for-each
        for (String fruit : set) System.out.println(fruit);
    }
}
```

### Класс ```HashSet```

```HashSet``` — это реализация интерфейса ```Set```, которая использует хеширование для хранения элементов. Элементы не
упорядочены, и порядок может изменяться при добавлении новых элементов.

- **Хеширование**: для хранения элементов используется хеширование, что делает добавление и проверку на наличие
  элементов быстрыми (```O(1)```).
- **Нет порядка**: элементы не упорядочены и могут быть выведены в произвольном порядке.

### Пример[Пример](HashSetMain.java)

```java
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Set<String> colors = new HashSet<>();
        colors.add("Red");
        colors.add("Blue");
        colors.add("Green");
        colors.add("Red"); // Дубликат не добавится

        // Перебор элементов
        for (String color : colors) System.out.println(color);
    }
}
```

## Map

```Map``` — это коллекция для хранения пар ключ-значение. Ключи уникальны, а значения могут повторяться. Типичные
реализации ```Map``` — это ```HashMap```, ```TreeMap``` и другие.

- **Пары ключ-значение**: позволяет хранить и управлять данными в формате ключ-значение.
- **Ключи уникальны**: ключи должны быть уникальными.
- **Быстрый доступ по ключу**: доступ к значениям осуществляется через ключи за время ```O(1)``` для ```HashMap```.

```java
import java.util.Map;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 10);
        map.put("Banana", 15);
        map.put("Orange", 20);

        // Перебор элементов через for-each
        for (Map.Entry<String, Integer> entry : map.entrySet())
            System.out.println(entry.getKey() + ": " + entry.getValue());

        // Получение значения по ключу
        System.out.println("Количество яблок: " + map.get("Apple"));
    }
}
```

### Класс ```HashMap```

```HashMap``` — это реализация интерфейса ```Map```, которая использует хеш-таблицу для хранения пар ключ-значение.
Ключи уникальны, и порядок элементов не гарантируется.

- **Хеширование**: ```HashMap``` использует хеширование для хранения пар ключ-значение.
- **Нет порядка**: элементы не упорядочены.
- **Быстрая производительность**: добавление, удаление и поиск элементов выполняются за время ```O(1)```.

### [Пример](HashMapMain.java)

```java
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> population = new HashMap<>();
        population.put("USA", 331);
        population.put("India", 1391);
        population.put("China", 1441);

        // Перебор элементов
        for (String country : population.keySet()) {
            System.out.println(country + ": " + population.get(country) + " млн");
        }

        // Проверка на наличие ключа
        if (population.containsKey("USA")) {
            System.out.println("Население США: " + population.get("USA") + " млн");
        }
    }
}
```

## Заключение

- ```List```: Упорядоченная коллекция, поддерживает дубликаты, можно обращаться по индексу. Основные
  реализации — ```ArrayList```, ```LinkedList```.
- ```Set```: Коллекция уникальных элементов, не допускает дубликатов. Основная реализация — ```HashSet```.
- ```Map```: Коллекция для хранения пар ключ-значение. Основная реализация — ```HashMap```.

## Дополнительно

Подробнее про [hash-функции](2_hash).
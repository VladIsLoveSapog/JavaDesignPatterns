# Abstract Factory

## Абстрактная фабрика

### Аналогия из жизни

Представьте сети быстрого питания: McDonald's и KFC. Каждая сеть — это «фабрика», которая производит согласованное **семейство продуктов**: бургер + картошка + напиток выдержаны в едином стиле, используют одни соусы, упаковку и рецептуру.

Нельзя заказать «бургер от McDonald's с картошкой от KFC» — это нарушило бы согласованность вкуса, порций и подачи. Аналогично, паттерн Абстрактная Фабрика гарантирует, что клиент всегда получает **совместимое семейство объектов**, не смешивая несовместимые реализации.

---

**Абстрактная фабрика** — это порождающий паттерн проектирования, который позволяет создавать семейства связанных
объектов, не привязываясь к конкретным классам создаваемых объектов.

Зачастую асбтрактная фабрика рождается из фабричного метода, когда необходимо добавить новыый продукт. В хорошей
программе, каждый класс отвечает только за одну вещь. Если класс имеет слишком много фабричных методов, они способны
затуманить его основную функцию. Поэтому имеет смысл вынести всю логику создания продуктов в отдельную иерархию классов,
применив абстрактную фабрику.

Абстрактная фабрика скрывает от клиентского кода подробности того, как и какие конкретно объекты будут созданы. Но при
этом клиентский код может работать со всеми типами создаваемых продуктов, так как их общий интерфейс был заранее
определён.

#### Основная идея

Основная цель паттерна Абстрактная Фабрика — **изолировать создание объектов от их использования**, обеспечивая таким
образом независимость клиентского кода от конкретных классов продуктов. Это позволяет легко добавлять новые семейства
продуктов без изменения существующего кода.

#### Проблема без паттерна

Посмотрим, как выглядит код **без** Абстрактной Фабрики:

```java
// АНТИПАТТЕРН: сервис мебели с if-else по стилю
public class FurnitureService {
    public void furnishRoom(String style) {
        Chair chair;
        Sofa sofa;
        Table table;

        if (style.equals("baroque")) {
            chair = new BaroqueChair();
            sofa  = new BaroqueSofa();
            table = new BaroqueTable();
        } else if (style.equals("gothic")) {
            chair = new GoticChair();
            sofa  = new GoticSofa();
            table = new GoticTable();
        } else if (style.equals("modern")) {
            chair = new ModernChair();
            sofa  = new ModernSofa();
            table = new ModernTable();
        } else {
            throw new IllegalArgumentException("Unknown style: " + style);
        }
        // использование ...
    }
}
```

Проблемы этого подхода:

1. **Добавить новый стиль = менять везде**: каждый сервис, каждый метод с `if-else` надо найти и обновить.
2. **Ничто не мешает смешать стили**: никакой компилятор не запретит написать `chair = new BaroqueChair()` рядом с `sofa = new ModernSofa()`.
3. **Нарушение SRP**: `FurnitureService` одновременно знает бизнес-логику комнаты *и* детали создания каждого стиля мебели.

#### Отличие от Фабричного метода

> **АФ вырастает из ФМ, когда продуктов становится больше одного.**

| Критерий | Фабричный метод | Абстрактная Фабрика |
|---|---|---|
| **Что создаёт** | Один продукт одного типа | Семейство взаимосвязанных продуктов |
| **Основа** | Наследование (подкласс переопределяет метод) | Композиция (клиент использует объект-фабрику) |
| **Типичный пример** | `DocumentFactory.createDocument()` | `UIFactory.createButton()` + `.createCheckbox()` |
| **Когда применять** | Нужно варьировать тип одного создаваемого объекта | Нужно гарантировать совместимость нескольких создаваемых объектов |

#### Применение

Паттерн Абстрактная Фабрика рекомендуется использовать в следующих случаях:

- Когда система должна быть независимой от способа создания, композиции и представления её объектов.
- Когда система должна работать с различными семействами взаимосвязанных объектов, не завися от их конкретных классов.
- Когда необходимо обеспечить согласованность создаваемых объектов (например, все элементы интерфейса принадлежат к
  одному стилю).
- Когда нужно добавить новые семейства продуктов без изменения существующего кода.

### Реализация

1. **Абстрактные продукты** объявляют интерфейсы продуктов, которые связаны друг с другом по смыслу, но выполняют разные
   функции. Именно эти интерфейсы будет использовать клиентский код — он никогда не увидит конкретных классов.
2. **Конкретные продукты** — большой набор классов, которые относятся к различным абстрактным продуктам (кресло/столик), но
   имеют одни и те же вариации (Барокко/Модерн). Каждый конкретный продукт создаётся только своей фабрикой, что
   исключает случайное смешение несовместимых объектов.
3. **Абстрактная фабрика** объявляет методы создания различных абстрактных продуктов (кресло/столик). Наличие всех
   методов в одном интерфейсе — это и есть гарантия согласованности: нельзя получить кресло барокко с диваном модерн.
4. **Конкретные фабрики** относятся каждая к своей вариации продуктов (Барокко/Модерн) и реализуют методы абстрактной
   фабрики, позволяя создавать все продукты определённой вариации. Добавить новый стиль = добавить одну новую
   фабрику, не трогая существующий код.
5. Несмотря на то, что конкретные фабрики порождают конкретные продукты, **сигнатуры их методов должны возвращать
   соответствующие абстрактные продукты**. Это позволит клиентскому коду, использующему фабрику, не привязываться к
   конкретным классам продуктов. Клиент сможет работать с любыми вариациями продуктов через абстрактные интерфейсы.

### Примеры

#### Пример 1: [FurnitureMain](code%2FFurnitureMain.java)

Создадим систему, которая может создавать кресла, столы и диваны в разных стилях: Барокко, Готика, Модерн.

##### AbstractProduct: Интерфейсы для продуктов

Определим интерфейсы производимых продуктов.

```java
/**
 * [AbstractProduct] Общий интерфейс кресел
 */
public interface Chair {
}

/**
 * [AbstractProduct] Общий интерфейс диванов
 */
public interface Sofa {
}

/**
 * [AbstractProduct] Общий интерфейс столов
 */
public interface Table {
}
```

##### ConcreteProduct: Конкретные реализации продуктов для разных стилей

**Барокко**

```java
/**
 * [ConcreteProduct] Кресло в стиле барокко
 */
public record BaroqueChair() implements Chair {
}

/**
 * [ConcreteProduct] Диван в стиле барокко
 */
public record BaroqueSofa() implements Sofa {
}

/**
 * [ConcreteProduct] Стол в стиле барокко
 */
public record BaroqueTable() implements Table {
}
```

**Готика**

```java
/**
 * [ConcreteProduct] Кресло в стиле готика
 */
public record GoticChair() implements Chair {
}

/**
 * [ConcreteProduct] Диван в стиле готика
 */
public record GoticSofa() implements Sofa {
}

/**
 * [ConcreteProduct] Стол в стиле готика
 */
public record GoticTable() implements Table {
}
```

**Модерн**

```java
/**
 * [ConcreteProduct] Кресло в стиле модерн
 */
public record ModernChair() implements Chair {
}

/**
 * [ConcreteProduct] Диван в стиле модерн
 */
public record ModernSofa() implements Sofa {
}

/**
 * [ConcreteProduct] Стол в стиле модерн
 */
public record ModernTable() implements Table {
}
```

##### AbstractFactory: Интерфейс Абстрактной Фабрики

```java
/**
 * [AbstractFactory] Интерфейс абстрактной фабрики мебели.
 * Наличие всех трёх методов в одном интерфейсе = гарантия согласованности семейства.
 * В методах используются общие типы, что позволяет достичь слабой связанности.
 */
public interface AbstractFurnitureFactory {
    Sofa createSofa();
    Chair createChair();
    Table createTable();
}
```

##### ConcreteFactory: Конкретные фабрики для разных стилей

**Барокко**

```java
/**
 * [ConcreteFactory] Фабрика мебели в стиле барокко.
 * Возвращаем конкретные типы, но сигнатура — абстрактные.
 */
public class BaroqueFurnitureFactory implements AbstractFurnitureFactory {
    @Override public Sofa createSofa()   { return new BaroqueSofa(); }
    @Override public Chair createChair() { return new BaroqueChair(); }
    @Override public Table createTable() { return new BaroqueTable(); }
}
```

**Готика**

```java
/**
 * [ConcreteFactory] Фабрика мебели в стиле готика.
 */
public class GoticFurnitureFactory implements AbstractFurnitureFactory {
    @Override public Sofa createSofa()   { return new GoticSofa(); }
    @Override public Chair createChair() { return new GoticChair(); }
    @Override public Table createTable() { return new GoticTable(); }
}
```

**Модерн**

```java
/**
 * [ConcreteFactory] Фабрика мебели в стиле модерн.
 */
public class ModernFurnitureFactory implements AbstractFurnitureFactory {
    @Override public Sofa createSofa()   { return new ModernSofa(); }
    @Override public Chair createChair() { return new ModernChair(); }
    @Override public Table createTable() { return new ModernTable(); }
}
```

##### Класс для тестирования

```java
/**
 * [Client] Переменная типа AbstractFurnitureFactory — можно поменять всё семейство,
 * изменив одну строку.
 */
public class FurnitureMain {
    public static void main(String[] args) {
        //Под тип абстрактной фабрики засунули фабрику мебели в стиле модерн
        AbstractFurnitureFactory factory = new ModernFurnitureFactory();
        Chair moderChair = factory.createChair();
        Sofa modernSofa = factory.createSofa();
        Table modernTable = factory.createTable();
        System.out.println("Мебель в стиле модерн: " + moderChair + modernSofa + modernTable);

        //Под тип абстрактной фабрики засунули фабрику мебели в стиле готика
        factory = new GoticFurnitureFactory();
        Chair goticChair = factory.createChair();
        Sofa goticSofa = factory.createSofa();
        Table goticTable = factory.createTable();
        System.out.println("Мебель в стиле готика: " + goticChair + goticSofa + goticTable);

        //Под тип абстрактной фабрики засунули фабрику мебели в стиле барокко
        factory = new BaroqueFurnitureFactory();
        Chair baroqueChair = factory.createChair();
        Sofa baroqueSofa = factory.createSofa();
        Table baroqueTable = factory.createTable();
        System.out.println("Мебель в стиле барокко: " + baroqueChair + baroqueSofa + baroqueTable);
    }
}
```

---

#### Пример 2: [NotificationMain](code%2Fexample2_notifications%2FNotificationMain.java)

Система уведомлений с поддержкой Email и SMS. Сначала демонстрируется антипаттерн (`if-else`
в `BadNotificationService`), затем — чистый вариант через Абстрактную Фабрику.

```
example2_notifications/
├── BadNotificationService.java   ← антипаттерн: if-else
├── NotificationHeader.java       ← AbstractProduct 1
├── NotificationBody.java         ← AbstractProduct 2
├── AbstractNotificationFactory.java ← AbstractFactory
├── email/
│   ├── EmailHeader.java          ← ConcreteProduct
│   ├── EmailBody.java            ← ConcreteProduct
│   └── EmailFactory.java         ← ConcreteFactory
├── sms/
│   ├── SmsHeader.java            ← ConcreteProduct
│   ├── SmsBody.java              ← ConcreteProduct
│   └── SmsFactory.java           ← ConcreteFactory
└── NotificationMain.java         ← Client
```

Ключевой момент — клиентский метод `sendNotification` не содержит ни одного упоминания `Email` или `SMS`:

```java
private static void sendNotification(AbstractNotificationFactory factory,
                                     String recipient, String message) {
    NotificationHeader header = factory.createHeader(recipient);
    NotificationBody body = factory.createBody(recipient, message);
    System.out.println("  Header: " + header.format());
    System.out.println("  Body:   " + body.format());
}
```

### Плюсы данного паттерна

- **Согласованность семейства продуктов**: Паттерн обеспечивает создание совместимых объектов из одного семейства, что
  предотвращает ошибки, связанные с несовместимостью компонентов.
- **Гибкость и расширяемость**: Позволяет легко добавлять новые семейства продуктов без изменения существующего
  клиентского кода.
- **Снижение связанности**: Клиентский код зависит только от абстракций, а не от конкретных реализаций продуктов, что
  упрощает поддержку и модификацию кода.
- **Изоляция от конкретных классов**: Клиентский код не знает о конкретных классах создаваемых объектов, что позволяет
  изменять реализации без влияния на клиента.

### Недостатки данного паттерна

- **Увеличение количества классов**: Для каждого нового семейства продуктов необходимо создавать новую фабрику, что
  может привести к увеличению числа классов в проекте.
- **Сложность понимания**: Для новичков паттерн может показаться сложным из-за использования абстрактных классов и
  интерфейсов.
- **Необходимость знания всех продуктов**: Все продукты, которые могут быть созданы фабрикой, должны быть известны
  заранее, что ограничивает динамическое добавление новых продуктов.

### Источники

- Design Patterns with Java: Abstract Factory
- Введение в паттерны проектирования: Абстрактная фабрика

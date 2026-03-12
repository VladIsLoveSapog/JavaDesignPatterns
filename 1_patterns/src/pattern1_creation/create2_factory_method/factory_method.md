# Factory Method

## Фабричный метод

**Фабричный метод** — это порождающий паттерн проектирования, который определяет общий интерфейс для создания объектов в
суперклассе, позволяя подклассам изменять тип создаваемых объектов.

Паттерн Фабричный метод предлагает создавать объекты не напрямую, используя оператор new, а через вызов особого
фабричного метода.

На первый взгляд, это может показаться бессмысленным — мы просто переместили вызов из одного конца программы в другой.
Но теперь вы сможете переопределить фабричный метод в подклассе, чтобы изменить тип создаваемого продукта. Чтобы эта
система работала, все возвращаемые объекты должны иметь общий интерфейс. Подклассы смогут производить объекты различных
классов, следующих одному и тому же интерфейсу.

### Аналогия для понимания

Представьте **мебельный завод**. Завод умеет делать мебель, но конкретные модели выпускают разные цеха:
- Цех «Классика» производит деревянные стулья
- Цех «Модерн» производит пластиковые стулья

Менеджер завода (клиентский код) говорит: «Мне нужен стул». Он не знает, какой именно цех будет делать стул — это
решает конкретный цех (подкласс). Менеджер получает готовый стул и работает с ним одинаково, независимо от того, из
дерева он или пластика.

В терминах паттерна:
- **Стул** (Chair) — это _продукт_ (интерфейс)
- **Деревянный стул / Пластиковый стул** — это _конкретные продукты_
- **Завод** (ChairFactory) — это _создатель_ (с фабричным методом createChair())
- **Цех «Классика» / Цех «Модерн»** — это _конкретные создатели_

---

#### Основная идея

Основная цель паттерна Фабричный метод — **делегировать создание объектов подклассам**, позволяя тем самым изменять тип
создаваемых объектов без изменения кода, который использует эти объекты.

#### Применение

Паттерн Фабричный метод рекомендуется использовать в следующих случаях:

- Когда необходимо определить интерфейс для создания объектов, но оставить выбор конкретных классов подклассам.
- Когда класс не должен знать о конкретных классах создаваемых объектов.
- Когда необходимо обеспечить возможность расширения системы новыми типами объектов без изменения существующего кода.
- Когда процесс создания объекта сложен и включает в себя множество шагов или параметров.

---

### Структура паттерна

```
«интерфейс»                    «интерфейс»
  Продукт          ◄──────────   Создатель
  ─────────                      ──────────────────────────
  + операция()                   + createProduct(): Продукт  ← фабричный метод
                                  + someBusinessLogic()       ← использует продукт
      ▲                               ▲
      │ реализует                     │ расширяет
      │                               │
КонкретныйПродукт          КонкретныйСоздатель
─────────────────          ──────────────────────────────────
+ операция()               + createProduct(): Продукт
                             { return new КонкретныйПродукт() }
```

**Роли:**
| Роль | Описание |
|---|---|
| **Продукт** | Общий интерфейс (или абстрактный класс) для всех создаваемых объектов |
| **Конкретный продукт** | Реализация продукта — то, что реально создаётся |
| **Создатель** | Объявляет фабричный метод; может содержать бизнес-логику, использующую продукт |
| **Конкретный создатель** | Переопределяет фабричный метод, возвращая нужный тип продукта |

---

### Реализация

1. Создание общего интерфейса объектов, которые будет создавать производитель и его подкласс.
2. Реализация конкретных продуктов, которые реализуют общий интерфейс.
3. Создание создателя.
4. Создатель объявляет фабричный метод, создающий объекты через общий интерфейс продуктов.
5. Создание конкретных создателей, каждый из которых по-своему реализует фабричный метод, производящий те или иные
   продукты.

---

### Варианты реализации

Фабричный метод можно реализовать несколькими способами — выбор зависит от сложности задачи и архитектурных требований:

| # | Вариант | Когда использовать |
|---|---|---|
| 1 | **Абстрактный класс-создатель** | Классический GoF; нужна общая логика в создателе |
| 2 | **Интерфейс создателя** | Нужна максимальная гибкость; создатели могут наследовать другие классы |
| 3 | **Вложенный статический класс** | Хочется держать фабрику «рядом» с продуктом в одном файле |
| 4 | **Внешний класс со статическими методами** | Простые случаи; фабрика — утилитный класс |
| 5 | **Внешний класс с состоянием** | Сложная логика создания; нужны разные конфигурации фабрики |

---

### Примеры

#### Пример 1: [Кнопки UI](code/example1_regular/ButtonMain.java) — Обычный фабричный метод (абстрактный класс)

Классический GoF-паттерн: абстрактный класс `Dialog` с абстрактным методом `createButton()`.
Подклассы переопределяют его, а `Dialog` использует результат в своей логике (метод `render()`),
не зная конкретного типа кнопки.

**Ключевая идея:** создатель использует фабричный метод внутри собственной бизнес-логики.

##### Интерфейс `Button`:

```java
/**
 * Интерфейс продукта — кнопка.
 * Все типы кнопок реализуют этот контракт.
 */
public interface Button {
    public void render();
    public void click();
}
```

##### Конкретные продукты: `WindowsButton` и `MacButton`:

```java
/** Конкретный продукт — кнопка в стиле Windows. */
public class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("[Windows] Отрисовка прямоугольной кнопки с тенью.");
    }

    @Override
    public void click() {
        System.out.println("[Windows] Клик! Воспроизведение системного звука Windows.");
    }
}
```

```java
/** Конкретный продукт — кнопка в стиле macOS. */
public class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("[macOS] Отрисовка кнопки с закруглёнными краями и эффектом стекла.");
    }

    @Override
    public void click() {
        System.out.println("[macOS] Клик! Плавная анимация нажатия в стиле macOS.");
    }
}
```

##### Абстрактный создатель `Dialog`:

```java
/**
 * Абстрактный создатель — диалоговое окно.
 *
 * Содержит бизнес-логику в методе render(), который использует фабричный метод
 * createButton() внутри себя. Dialog не знает, какой тип кнопки будет создан.
 */
public abstract class Dialog {

    // Фабричный метод — подкласс решает, какую кнопку создать
    public abstract Button createButton();

    // Бизнес-логика: использует фабричный метод, не зная конкретного типа продукта
    public void render() {
        Button button = createButton(); // вызов фабричного метода
        System.out.println("Диалоговое окно открывается...");
        button.render();               // работа через интерфейс
        System.out.println("Диалоговое окно отрисовано. Ожидание нажатия кнопки...");
    }

    public void handleInput() {
        Button button = createButton();
        System.out.println("Пользователь нажал кнопку:");
        button.click();
    }
}
```

##### Конкретные создатели: `WindowsDialog` и `MacDialog`:

```java
/** Конкретный создатель для Windows. */
public class WindowsDialog extends Dialog {
    @Override
    public Button createButton() {
        return new WindowsButton(); // переопределяем — возвращаем нужный тип
    }
}
```

```java
/** Конкретный создатель для macOS. */
public class MacDialog extends Dialog {
    @Override
    public Button createButton() {
        return new MacButton();
    }
}
```

##### Демонстрация:

```java
public class ButtonMain {
    public static void main(String[] args) {
        Dialog dialog;
        String os = "Windows";

        if (os.equals("Windows")) {
            dialog = new WindowsDialog();
        } else {
            dialog = new MacDialog();
        }

        // Работаем через абстракцию Dialog — независимо от конкретного типа
        dialog.render();
        dialog.handleInput();

        // Меняем «платформу» — просто подменяем создатель
        dialog = new MacDialog();
        dialog.render();
    }
}
```

---

#### Пример 2: [Напитки](code/example2_static_nested/DrinkMain.java) — Вложенный статический фабричный метод

Фабрика реализована как `public static class Factory` внутри класса продукта `Drink`.
Конкретные классы `Coffee` и `Tea` тоже вложены.

**Ключевая идея:** фабрика живёт внутри класса продукта → `Drink.Factory.createCoffee(300, "Эспрессо")`.

##### Класс `Drink` с вложенными фабрикой и продуктами:

```java
public abstract class Drink {

    private final String name;
    private final int volumeMl;

    protected Drink(String name, int volumeMl) {
        this.name = name;
        this.volumeMl = volumeMl;
    }

    public abstract void describe();

    // ----- Конкретные продукты — вложенные классы -----

    public static class Coffee extends Drink {
        private final String coffeeType;

        public Coffee(int volumeMl, String coffeeType) {
            super("Кофе (" + coffeeType + ")", volumeMl);
            this.coffeeType = coffeeType;
        }

        @Override
        public void describe() {
            System.out.println("☕ " + getName() + ", объём: " + getVolumeMl() + " мл. "
                    + "Аромат свежемолотого кофе " + coffeeType + "!");
        }
    }

    public static class Tea extends Drink {
        private final String teaSort;

        public Tea(String teaSort) {
            super("Чай (" + teaSort + ")", 250);
            this.teaSort = teaSort;
        }

        @Override
        public void describe() {
            System.out.println("🍵 " + getName() + ", объём: " + getVolumeMl() + " мл. "
                    + teaSort + " чай заварен идеально!");
        }
    }

    // ----- Вложенная статическая фабрика -----

    public static class Factory {

        private Factory() {} // утилитный класс — не создаём экземпляры

        public static Drink createCoffee(int volumeMl, String coffeeType) {
            System.out.println("[Фабрика] Готовим кофе: " + coffeeType + " (" + volumeMl + " мл)");
            return new Coffee(volumeMl, coffeeType);
        }

        public static Drink createTea(String teaSort) {
            System.out.println("[Фабрика] Завариваем чай: " + teaSort);
            return new Tea(teaSort);
        }
    }
}
```

##### Демонстрация:

```java
public class DrinkMain {
    public static void main(String[] args) {
        // Синтаксис: Drink.Factory.createXxx(...)
        Drink espresso  = Drink.Factory.createCoffee(200, "Эспрессо");
        Drink cappuccino = Drink.Factory.createCoffee(350, "Капучино");
        Drink greenTea  = Drink.Factory.createTea("Зелёный");
        Drink blackTea  = Drink.Factory.createTea("Чёрный");

        espresso.describe();
        cappuccino.describe();
        greenTea.describe();
        blackTea.describe();
    }
}
```

---

#### Пример 3: [Фигуры](code/example3_external/ShapeMain.java) — Внешний класс со статическими фабричными методами

Отдельный класс `ShapeFactory` полностью отделён от иерархии продуктов.
Его единственная ответственность — создание объектов с валидацией.

**Ключевая идея:** один класс отвечает только за создание → `ShapeFactory.createCircle(5.0)`.

##### Интерфейс `Shape` и конкретные продукты:

```java
public interface Shape {
    public void draw();
    public double area();
}
```

```java
public class Circle implements Shape {
    private final double radius;

    public Circle(double radius) { this.radius = radius; }

    @Override
    public void draw() { System.out.println("Нарисован круг с радиусом " + radius); }

    @Override
    public double area() { return Math.PI * radius * radius; }
}
```

```java
public class Rectangle implements Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() { System.out.println("Нарисован прямоугольник " + width + " x " + height); }

    @Override
    public double area() { return width * height; }
}
```

##### Внешняя фабрика `ShapeFactory`:

```java
/**
 * Внешняя фабрика — утилитный класс только со статическими методами.
 * Полностью отделён от иерархии продуктов.
 */
public class ShapeFactory {

    private ShapeFactory() {} // запрещаем создание экземпляров

    public static Shape createCircle(double radius) {
        if (radius <= 0) throw new IllegalArgumentException("Радиус должен быть положительным.");
        return new Circle(radius);
    }

    public static Shape createRectangle(double width, double height) {
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("Размеры должны быть положительными.");
        return new Rectangle(width, height);
    }

    // Удобный специализированный метод поверх базового конструктора
    public static Shape createSquare(double side) {
        if (side <= 0) throw new IllegalArgumentException("Сторона должна быть положительной.");
        return new Rectangle(side, side); // квадрат — это прямоугольник с равными сторонами
    }
}
```

##### Демонстрация:

```java
public class ShapeMain {
    public static void main(String[] args) {
        Shape circle    = ShapeFactory.createCircle(5.0);
        Shape rectangle = ShapeFactory.createRectangle(4.0, 6.0);
        Shape square    = ShapeFactory.createSquare(3.0);

        circle.draw();
        System.out.printf("  Площадь: %.2f%n", circle.area());

        rectangle.draw();
        System.out.printf("  Площадь: %.2f%n", rectangle.area());

        square.draw();
        System.out.printf("  Площадь: %.2f%n", square.area());

        // Демонстрация валидации
        try {
            ShapeFactory.createCircle(-1.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
```

---

#### Пример 4: [Уведомления](code/example4_interface/NotificationMain.java) — Фабричный метод через интерфейс создателя

Создатель — **интерфейс** `NotificationFactory` с единственным абстрактным методом `createNotification()`.
Интерфейс также содержит `default`-метод с общей логикой (аналог метода `render()` из примера 1).

**Ключевая идея:** интерфейс вместо абстрактного класса — максимальная гибкость, можно реализовать несколько фабрик.

##### Интерфейс продукта и конкретные продукты:

```java
public interface Notification {
    public void send(String recipient, String message);
}
```

```java
public class EmailNotification implements Notification {
    @Override
    public void send(String recipient, String message) {
        System.out.println("[Email] Отправка письма на адрес: " + recipient);
        System.out.println("  Текст: " + message);
        System.out.println("  Статус: письмо отправлено через SMTP-сервер.");
    }
}
```

```java
public class SmsNotification implements Notification {
    @Override
    public void send(String recipient, String message) {
        System.out.println("[SMS] Отправка сообщения на номер: " + recipient);
        System.out.println("  Текст: " + message);
        System.out.println("  Статус: SMS отправлено через SMS-шлюз.");
    }
}
```

##### Интерфейс создателя с `default`-методом:

```java
public interface NotificationFactory {

    // Фабричный метод — реализуется в конкретных фабриках
    public Notification createNotification();

    // Default-метод с общей логикой — использует фабричный метод
    // (аналог метода render() в абстрактном классе Dialog из примера 1)
    default public void sendNotification(String recipient, String message) {
        Notification notification = createNotification(); // вызов фабричного метода
        System.out.println("Фабрика подготовила уведомление, отправляем...");
        notification.send(recipient, message);
    }
}
```

##### Конкретные создатели:

```java
public class EmailNotificationFactory implements NotificationFactory {
    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}
```

```java
public class SmsNotificationFactory implements NotificationFactory {
    @Override
    public Notification createNotification() {
        return new SmsNotification();
    }
}
```

##### Демонстрация:

```java
public class NotificationMain {
    public static void main(String[] args) {
        NotificationFactory emailFactory = new EmailNotificationFactory();
        NotificationFactory smsFactory   = new SmsNotificationFactory();

        // Вариант 1: создаём уведомление через фабричный метод
        Notification email = emailFactory.createNotification();
        email.send("user@example.com", "Ваш заказ подтверждён!");

        // Вариант 2: используем default-метод — он сам вызовет фабричный метод
        smsFactory.sendNotification("+7-999-123-45-67", "Код подтверждения: 1234");

        // Смена фабрики без изменения клиентского кода
        NotificationFactory factory = emailFactory;
        factory.sendNotification("admin@example.com", "Системное уведомление!");
        factory = smsFactory;
        factory.sendNotification("+7-800-000-00-00", "Системное уведомление!");
    }
}
```

---

#### Пример 5: [Пицца](code/example5_complex/PizzaMain.java) — Внешний класс с полями, методами и сложной логикой

`PizzaFactory` — полноценный объект с конфигурационными полями, валидацией и логированием.
Фабричный метод `createPizza()` координирует несколько приватных вспомогательных методов.
Можно создать несколько фабрик с разными конфигурациями.

**Ключевая идея:** фабрика — полноценный объект с состоянием и сложной логикой создания.

##### Продукт `Pizza` и вспомогательный enum `PizzaSize`:

```java
public enum PizzaSize {
    SMALL("Маленькая", 25),
    MEDIUM("Средняя", 30),
    LARGE("Большая", 35);

    private final String displayName;
    private final int diameterCm;

    PizzaSize(String displayName, int diameterCm) {
        this.displayName = displayName;
        this.diameterCm = diameterCm;
    }

    public String getDisplayName() { return displayName; }
    public int getDiameterCm()     { return diameterCm; }
}
```

```java
public class Pizza {
    private final String name;
    private final List<String> ingredients;
    private final PizzaSize size;
    private final double price;

    public Pizza(String name, List<String> ingredients, PizzaSize size, double price) {
        this.name = name;
        this.ingredients = new ArrayList<>(ingredients); // защитная копия
        this.size = size;
        this.price = price;
    }

    public void describe() {
        System.out.println("Пицца: " + name);
        System.out.println("  Размер: " + size.getDisplayName() + " (" + size.getDiameterCm() + " см)");
        System.out.println("  Ингредиенты: " + String.join(", ", ingredients));
        System.out.printf("  Цена: %.0f руб.%n", price);
    }
    // ... геттеры
}
```

##### Сложная фабрика `PizzaFactory`:

```java
public class PizzaFactory {

    private final String chefName;          // имя шефа — влияет на логирование
    private final boolean loggingEnabled;   // включить ли подробные логи
    private final double markupCoefficient; // коэффициент наценки (1.0 = без наценки)

    public PizzaFactory(String chefName, boolean loggingEnabled, double markupCoefficient) {
        this.chefName = chefName;
        this.loggingEnabled = loggingEnabled;
        this.markupCoefficient = markupCoefficient;
    }

    // Публичный фабричный метод — координирует весь процесс создания
    public Pizza createPizza(String pizzaType, PizzaSize size) {
        validateSize(size);                              // шаг 1: валидация
        validatePizzaType(pizzaType);                    // шаг 2: проверка типа
        List<String> ingredients = selectIngredients(pizzaType); // шаг 3: ингредиенты
        double price = calculatePrice(pizzaType, size);  // шаг 4: цена
        Pizza pizza = new Pizza(pizzaType, ingredients, size, price); // шаг 5: создание
        logCreation(pizza);                              // шаг 6: логирование
        return pizza;
    }

    // Приватные вспомогательные методы
    private void validateSize(PizzaSize size) { ... }
    private void validatePizzaType(String pizzaType) { ... }
    private List<String> selectIngredients(String pizzaType) { ... }
    private double calculatePrice(String pizzaType, PizzaSize size) { ... }
    private void logCreation(Pizza pizza) { ... }
}
```

##### Демонстрация двух фабрик с разными конфигурациями:

```java
public class PizzaMain {
    public static void main(String[] args) {
        // Фабрика 1: итальянская кухня, логирование включено, наценка +50%
        PizzaFactory italianFactory = new PizzaFactory("Марио Конти", true, 1.5);

        // Фабрика 2: стандартная пиццерия, логирование выключено, наценки нет
        PizzaFactory standardFactory = new PizzaFactory("Иван Петров", false, 1.0);

        // Одинаковые вызовы — разное поведение (логи, цены) из-за состояния фабрики
        Pizza p1 = italianFactory.createPizza("Маргарита", PizzaSize.LARGE);
        p1.describe();

        Pizza p2 = standardFactory.createPizza("Четыре сыра", PizzaSize.SMALL);
        p2.describe(); // та же пицца, меньше цена, нет логов

        // Валидация
        try {
            italianFactory.createPizza("Гавайская", PizzaSize.MEDIUM);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
```

---

### Плюсы данного паттерна

- **Гибкость и расширяемость**: Позволяет легко добавлять новые типы продуктов без изменения существующего кода.
- **Снижение связанности**: Клиенты не зависят от конкретных классов продуктов, а работают с абстракциями (Interface или
  Abstract Class).
- **Упрощение кода**: Избавляет от необходимости использовать длинные условные операторы для создания объектов различных
  типов.
- **Соблюдение принципов SOLID**:
    - **Принцип единственной ответственности (Single Responsibility Principle)**: Отделяет процесс создания объектов от
      их использования.
    - **Принцип открытости/закрытости (Open/Closed Principle)**: Классы открыты для расширения, но закрыты для
      модификации.

### Недостатки данного паттерна

- **Увеличение количества классов**: Для каждого типа продукта требуется создать отдельный класс-фабрику, что может
  привести к увеличению числа классов в проекте.
- **Сложность понимания**: Для новичков паттерн может показаться сложным из-за использования абстрактных классов и
  интерфейсов.

### Источники

- Design Patterns with Java: Factory Method
- Введение в паттерны проектирования: Фабричный метод

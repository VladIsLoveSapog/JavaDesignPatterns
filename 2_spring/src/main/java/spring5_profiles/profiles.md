<h1>
    Spring Profiles
</h1>

**Spring Profiles** — механизм для условной регистрации бинов в зависимости от активного профиля. Позволяет определить
несколько реализаций одного интерфейса и переключаться между ними без изменения кода — достаточно сменить активный профиль.

<h5>
    Зачем нужны профили
</h5>

Типичные сценарии использования:
- **dev/prod окружения** — разные базы данных (H2 для разработки, PostgreSQL для продакшена)
- **Логирование** — подробное в dev, минимальное в prod
- **Сервисы уведомлений** — email-заглушки в dev, реальная отправка в prod
- **Тестирование** — моки и in-memory реализации для тестов

Профили логически продолжают тему DI: если ```@Qualifier``` выбирает реализацию по имени бина, то ```@Profile```
выбирает реализацию по **активному окружению**.

<h3 align="center">
    Аннотация @Profile
</h3>

Аннотация ```@Profile``` помечает бин или конфигурационный класс — он будет зарегистрирован в контексте только при
активации указанного профиля.

```java
@Component
@Profile("dev")
public class DevDataSource implements IDataSource {
    // Зарегистрирован только при активном профиле "dev"
}

@Component
@Profile("prod")
public class ProdDataSource implements IDataSource {
    // Зарегистрирован только при активном профиле "prod"
}
```

При внедрении через ```@Autowired``` по типу ```IDataSource``` Spring автоматически подставит ту реализацию,
чей профиль активен. Если активен профиль "dev" — будет внедрён ```DevDataSource```.

<h3 align="center">
    Способы активации профиля
</h3>

<h5 align="center">
    1. Программно через AnnotationConfigApplicationContext
</h5>

```java
var context = new AnnotationConfigApplicationContext();
context.getEnvironment().setActiveProfiles("dev");
context.register(ProfileConfig.class);
context.refresh();
```

Важно: профиль нужно задать **до** вызова ```refresh()```, иначе бины будут уже созданы.

<h5 align="center">
    2. Через системное свойство JVM
</h5>

```
-Dspring.profiles.active=prod
```

Удобно для запуска из командной строки или настройки в IDE.

<h5 align="center">
    3. Через аннотацию @ActiveProfiles (для тестов)
</h5>

```java
@ActiveProfiles("test")
@SpringBootTest
public class MyServiceTest {
    // Тест будет использовать бины с профилем "test"
}
```

<h3 align="center">
    Профиль по умолчанию
</h3>

Если ни один профиль не задан, Spring активирует профиль ```"default"```. Бин с ```@Profile("default")``` будет
зарегистрирован только в этом случае — как fallback-реализация:

```java
@Component
@Profile("default")
public class DefaultDataSource implements IDataSource {
    // Используется, если профиль не задан явно
}
```

<h3 align="center">
    Несколько профилей одновременно
</h3>

Можно активировать несколько профилей одновременно:

```java
context.getEnvironment().setActiveProfiles("dev", "metrics");
```

Или через системное свойство через запятую:

```
-Dspring.profiles.active=dev,metrics
```

Бин с ```@Profile("dev")``` и бин с ```@Profile("metrics")``` будут зарегистрированы одновременно.

Также поддерживаются логические выражения в аннотации:
- ```@Profile("dev | staging")``` — бин активен при любом из профилей
- ```@Profile("prod & metrics")``` — бин активен только если оба профиля активны
- ```@Profile("!dev")``` — бин активен при любом профиле, кроме dev

Пример использования профилей: [code](code)

package pattern3_behavior.behavior9_observer.code.example1_church;

/**
 * <b>Parishioner</b> (Прихожанин) — конкретный подписчик (<i>ConcreteObserver</i>),
 * реализующий интерфейс {@link Observer}.
 *
 * <p>Представляет прихожанина церкви, который получает новости от издателя
 * ({@link Observable}) и выводит их в консоль.
 *
 * <p><b>Самоподписка в конструкторе:</b><br>
 * Конструктор принимает ссылку на {@link Observable} и немедленно регистрирует
 * {@code this} вызовом {@link Observable#registerObserver(Observer)}.
 * Это означает, что создание объекта {@code Parishioner} автоматически подписывает
 * его на обновления — дополнительная регистрация извне не требуется.
 * Такой подход упрощает клиентский код, но создаёт неявную связь:
 * факт подписки скрыт внутри конструктора.
 *
 * @see Observer
 * @see Observable
 * @see CatholicChurch
 */
public class Parishioner implements Observer {
    private String name;

    /**
     * Создаёт прихожанина и автоматически регистрирует его в указанном издателе.
     *
     * <p>Используется паттерн <b>самоподписки</b> — объект подписывается
     * на обновления прямо в конструкторе через {@link Observable#registerObserver(Observer)}.
     *
     * @param name имя прихожанина.
     * @param o    издатель ({@link Observable}), в котором будет зарегистрирован прихожанин.
     */
    public Parishioner(String name, Observable o) {
        this.name = name;
        o.registerObserver(this);
    }

    /**
     * Реакция на новость.
     *
     * <p>Выводит в консоль сообщение вида:
     * <i>«{имя} узнал новость: {news}»</i>.
     *
     * @param news новость, полученная от издателя.
     */
    @Override
    public void update(String news) {
        System.out.println(name + " узнал новость: " + news);
    }
}

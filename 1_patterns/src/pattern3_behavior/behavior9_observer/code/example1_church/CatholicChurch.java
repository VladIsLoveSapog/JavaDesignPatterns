package pattern3_behavior.behavior9_observer.code.example1_church;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>CatholicChurch</b> (Католическая церковь) — конкретный издатель (<i>ConcreteSubject</i>),
 * реализующий интерфейс {@link Observable}.
 *
 * <p>Хранит список прихожан ({@link Observer}) и текущую новость церкви.
 * При изменении новости через {@link #setNewsChurch(String)} автоматически
 * оповещает всех зарегистрированных подписчиков.
 *
 * <p>Подписчики добавляются и удаляются методами
 * {@link #registerObserver(Observer)} и {@link #removeObserver(Observer)}.
 *
 * @see Observable
 * @see Observer
 * @see Parishioner
 */
public class CatholicChurch implements Observable {
    private List<Observer> parishioners;    //список прихожан
    private String newsChurch;              //новость

    public CatholicChurch() {
        parishioners = new ArrayList<>();
    }

    /**
     * Устанавливает новую новость церкви и оповещает всех подписчиков.
     *
     * <p>После присвоения значения полю {@code newsChurch} немедленно
     * вызывается {@link #notifyObservers()}.
     *
     * @param news новая новость церкви.
     */
    public void setNewsChurch(String news) {
        this.newsChurch = news;
        notifyObservers();
    }

    /**
     * Регистрация нового прихожанина.
     *
     * <p>Добавляет {@link Observer} в список рассылки.
     *
     * @param observer подписчик, который будет зарегистрирован на получение оповещений.
     */
    @Override
    public void registerObserver(Observer observer) {
        parishioners.add(observer);
    }

    /**
     * Удаление прихожанина.
     *
     * <p>Исключает {@link Observer} из списка рассылки.
     *
     * @param observer подписчик, который больше не будет получать оповещения.
     */
    @Override
    public void removeObserver(Observer observer) {
        parishioners.remove(observer);
    }

    /**
     * Оповещение всех прихожан.
     *
     * <p>Проходит по списку подписчиков и вызывает {@link Observer#update(String)},
     * передавая текущую новость церкви.
     */
    @Override
    public void notifyObservers() {
        for (Observer o : parishioners) o.update(newsChurch);
    }
}

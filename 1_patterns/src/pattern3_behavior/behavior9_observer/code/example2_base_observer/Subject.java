package pattern3_behavior.behavior9_observer.code.example2_base_observer;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Класс Subject (Наблюдаемый / Издатель)</b>
 *
 * <p>Конкретный класс, который совмещает в себе <i>роль интерфейса</i> Subject
 * и его <i>реализацию</i>. В данном упрощённом примере отдельный интерфейс
 * для субъекта не выделяется — вся логика управления подписчиками и
 * уведомлений сосредоточена в одном классе.</p>
 *
 * <p><b>Основные обязанности:</b></p>
 * <ol>
 *     <li>Хранить список зарегистрированных наблюдателей ({@link Observer})</li>
 *     <li>Предоставлять методы подписки ({@link #addObserver(Observer)})
 *         и отписки ({@link #removeObserver(Observer)})</li>
 *     <li>Уведомлять всех наблюдателей при изменении внутреннего состояния
 *         через {@link #notifyObservers()}</li>
 * </ol>
 *
 * <p><i>Используется push-модель:</i> при вызове {@link #setState(String)}
 * новое состояние автоматически рассылается всем подписчикам.</p>
 *
 * @see Observer
 * @see ConcreteObserver
 */
class Subject {
    private List<Observer> observers = new ArrayList<>();
    private String state;

    /**
     * Добавляет нового наблюдателя в список подписчиков.
     *
     * <p>После вызова этого метода переданный {@link Observer} будет
     * получать уведомления при каждом изменении состояния субъекта.</p>
     *
     * @param observer наблюдатель, которого необходимо подписать
     * @see #removeObserver(Observer)
     * @see #notifyObservers()
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Удаляет наблюдателя из списка подписчиков.
     *
     * <p>После вызова этого метода переданный {@link Observer} больше
     * не будет получать уведомления от данного субъекта.</p>
     *
     * @param observer наблюдатель, которого необходимо отписать
     * @see #addObserver(Observer)
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Уведомляет всех зарегистрированных наблюдателей об изменении состояния.
     *
     * <p>Метод итерирует по списку подписчиков и вызывает
     * {@link Observer#update(String)} у каждого из них, передавая
     * текущее значение состояния {@code state}.</p>
     *
     * @see Observer#update(String)
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }

    /**
     * Устанавливает новое состояние субъекта и уведомляет наблюдателей.
     *
     * <p>При каждом вызове этого метода:</p>
     * <ol>
     *     <li>Сохраняется новое значение {@code state}</li>
     *     <li>Автоматически вызывается {@link #notifyObservers()},
     *         рассылающий обновление всем подписчикам</li>
     * </ol>
     *
     * @param state новое состояние субъекта в виде строки
     * @see #notifyObservers()
     */
    public void setState(String state) {
        this.state = state;
        notifyObservers();
    }
}

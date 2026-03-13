package pattern3_behavior.behavior9_observer.code.example1_church;

/**
 * <b>Демонстрация паттерна Observer</b> на примере католической церкви и её прихожан.
 *
 * <p>Порядок выполнения:
 * <ol>
 *     <li>Создаётся издатель — {@link CatholicChurch}.</li>
 *     <li>Создаются подписчики — {@link Parishioner}.
 *         Каждый прихожанин автоматически регистрируется в церкви через конструктор.</li>
 *     <li>Церковь публикует новость через {@link CatholicChurch#setNewsChurch(String)},
 *         и все прихожане получают уведомление.</li>
 * </ol>
 *
 * <p><b>Ожидаемый вывод:</b>
 * <pre>
 * Мартин Лютер узнал новость: Инквизиция была ошибкой... месса Mea Culpa 12 марта 2000 года
 * Жан Кальвин узнал новость: Инквизиция была ошибкой... месса Mea Culpa 12 марта 2000 года
 * </pre>
 *
 * @see Observable
 * @see Observer
 * @see CatholicChurch
 * @see Parishioner
 */
public class Main {
    public static void main(String[] args) {
        //Создали церковь
        var catholicChurch = new CatholicChurch();

        //Создали прихожан и зарегистрировали их в церкви
        new Parishioner("Мартин Лютер", catholicChurch);
        new Parishioner("Жан Кальвин", catholicChurch);

        //В церкви вышла новость - подписчики о ней узнали
        catholicChurch.setNewsChurch("Инквизиция была ошибкой... месса Mea Culpa 12 марта 2000 года");
    }
}
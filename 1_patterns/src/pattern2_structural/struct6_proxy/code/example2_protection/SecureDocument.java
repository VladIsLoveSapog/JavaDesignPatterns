package pattern2_structural.struct6_proxy.code.example2_protection;

/**
 * Паттерн Заместитель (Proxy) — реальный документ (Real Subject).
 * <p>
 * Содержит бизнес-логику работы с документом: хранение содержимого,
 * запись и удаление. Сам по себе не выполняет никаких проверок доступа —
 * за это отвечает защищающий прокси {@link ProtectionProxyDocument}.
 * <p>
 * Реализует интерфейс {@link Document}, что позволяет подменять
 * реальный объект прокси-заместителем прозрачно для клиента.
 */
public class SecureDocument implements Document {

    /** Название документа, используется для идентификации в логах */
    private final String name;

    /** Текущее содержимое документа */
    private String content;

    /** Флаг, указывающий, был ли документ удалён */
    private boolean deleted;

    /**
     * Создаёт новый документ с указанным именем и начальным содержимым.
     *
     * @param name    название документа
     * @param content начальное содержимое документа
     */
    public SecureDocument(String name, String content) {
        this.name = name;
        this.content = content;
        this.deleted = false;
    }

    /**
     * Читает содержимое документа.
     * <p>
     * Если документ был удалён, возвращает соответствующее сообщение.
     *
     * @return содержимое документа или сообщение об удалении
     */
    @Override
    public String read() {
        if (deleted) {
            return "[Документ '" + name + "' был удалён]";
        }
        return "[Документ '" + name + "']: " + content;
    }

    /**
     * Записывает новое содержимое в документ.
     * <p>
     * Если документ удалён, операция игнорируется с выводом предупреждения.
     *
     * @param content новое содержимое для записи
     */
    @Override
    public void write(String content) {
        if (deleted) {
            System.out.println("Невозможно записать в удалённый документ '" + name + "'.");
            return;
        }
        this.content = content;
        System.out.println("Документ '" + name + "' успешно обновлён.");
    }

    /**
     * Удаляет документ, устанавливая флаг удаления.
     * <p>
     * После удаления операции чтения и записи становятся недоступными.
     */
    @Override
    public void delete() {
        this.deleted = true;
        this.content = null;
        System.out.println("Документ '" + name + "' удалён.");
    }
}

package pattern2_structural.struct6_proxy.code.example2_protection;

/**
 * Паттерн Заместитель (Proxy) — защищающий прокси (Protection Proxy).
 * <p>
 * Контролирует доступ к реальному документу {@link SecureDocument},
 * проверяя роль пользователя перед каждой операцией.
 * Разграничение доступа:
 * <ul>
 *     <li>{@link Role#READER} — разрешено только чтение</li>
 *     <li>{@link Role#EDITOR} — разрешены чтение и запись</li>
 *     <li>{@link Role#ADMIN} — разрешены все операции, включая удаление</li>
 * </ul>
 * <p>
 * Прокси реализует тот же интерфейс {@link Document}, что и реальный объект,
 * поэтому клиент может работать с ним, не зная о наличии проверок.
 */
public class ProtectionProxyDocument implements Document {

    /** Реальный документ, доступ к которому контролируется прокси */
    private final Document realDocument;

    /** Роль текущего пользователя, определяющая набор разрешённых операций */
    private final Role userRole;

    /** Имя пользователя, используется для логирования попыток доступа */
    private final String userName;

    /**
     * Создаёт защищающий прокси для указанного документа.
     *
     * @param realDocument реальный документ, к которому контролируется доступ
     * @param userRole     роль пользователя, определяющая уровень доступа
     * @param userName     имя пользователя для логирования
     */
    public ProtectionProxyDocument(Document realDocument, Role userRole, String userName) {
        this.realDocument = realDocument;
        this.userRole = userRole;
        this.userName = userName;
    }

    /**
     * Читает содержимое документа.
     * <p>
     * Чтение доступно всем ролям: {@link Role#READER}, {@link Role#EDITOR}, {@link Role#ADMIN}.
     *
     * @return содержимое документа
     */
    @Override
    public String read() {
        // Чтение доступно всем ролям — просто делегируем вызов реальному объекту
        System.out.println("[Прокси] Пользователь '" + userName + "' (" + userRole + ") читает документ.");
        return realDocument.read();
    }

    /**
     * Записывает новое содержимое в документ.
     * <p>
     * Запись доступна только ролям {@link Role#EDITOR} и {@link Role#ADMIN}.
     * При недостаточных правах операция отклоняется с выводом предупреждения.
     *
     * @param content новое содержимое для записи
     */
    @Override
    public void write(String content) {
        // Проверяем, имеет ли пользователь право на запись
        if (userRole == Role.READER) {
            System.out.println("[Прокси] ОТКАЗАНО: пользователь '" + userName
                    + "' (" + userRole + ") не имеет прав на запись.");
            return;
        }
        System.out.println("[Прокси] Пользователь '" + userName + "' (" + userRole + ") записывает в документ.");
        realDocument.write(content);
    }

    /**
     * Удаляет документ.
     * <p>
     * Удаление доступно только роли {@link Role#ADMIN}.
     * При недостаточных правах операция отклоняется с выводом предупреждения.
     */
    @Override
    public void delete() {
        // Удаление — самая опасная операция, доступна только администратору
        if (userRole != Role.ADMIN) {
            System.out.println("[Прокси] ОТКАЗАНО: пользователь '" + userName
                    + "' (" + userRole + ") не имеет прав на удаление.");
            return;
        }
        System.out.println("[Прокси] Пользователь '" + userName + "' (" + userRole + ") удаляет документ.");
        realDocument.delete();
    }
}

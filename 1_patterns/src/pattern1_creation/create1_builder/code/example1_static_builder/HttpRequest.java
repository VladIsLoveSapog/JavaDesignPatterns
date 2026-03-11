package pattern1_creation.create1_builder.code.example1_static_builder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * HTTP-запрос — пример статического вложенного Builder.
 * <p>
 * Почему Builder должен быть STATIC?
 * ─────────────────────────────────
 * Нестатический вложенный класс (inner class) неявно хранит ссылку на экземпляр
 * внешнего класса. Значит, чтобы создать HttpRequest.Builder, вам сначала нужен
 * объект HttpRequest. Но именно Builder и должен создавать HttpRequest — замкнутый
 * круг! Static nested class этой проблемы не имеет: он не привязан к конкретному
 * экземпляру внешнего класса и создаётся через new HttpRequest.Builder(...).
 * <p>
 * Ключевые особенности:
 * - private final поля → объект неизменяем после создания
 * - Обязательные параметры (method, url) задаются в конструкторе Builder
 * - Опциональные параметры имеют разумные умолчания
 * - Метод header() накапливает заголовки
 * - Валидация в конструкторе Builder (не в build()), чтобы ошибка была
 * понятна до построения цепочки
 */
public class HttpRequest {

    // Обязательные поля
    private final String method;    // HTTP-метод: GET, POST, PUT, DELETE и т.д.
    private final String url;       // URL запроса

    // Опциональные поля с умолчаниями
    private final Map<String, String> headers;       // HTTP-заголовки (по умолчанию пустые)
    private final String body;                       // Тело запроса (по умолчанию null)
    private final int timeout;                       // Таймаут в секундах (по умолчанию 30)
    private final String authToken;                  // Bearer-токен авторизации (по умолчанию null)
    private final boolean followRedirects;           // Следовать ли редиректам (по умолчанию true)

    /**
     * Приватный конструктор — объект создаётся ТОЛЬКО через Builder.
     * Это гарантирует, что все обязательные поля заполнены и валидация пройдена.
     */
    private HttpRequest(Builder builder) {
        this.method = builder.method;
        this.url = builder.url;
        // Защитная копия: снаружи никто не сможет изменить карту заголовков
        this.headers = Map.copyOf(builder.headers);
        this.body = builder.body;
        this.timeout = builder.timeout;
        this.authToken = builder.authToken;
        this.followRedirects = builder.followRedirects;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getAuthToken() {
        return authToken;
    }

    public boolean isFollowRedirects() {
        return followRedirects;
    }

    /**
     * Статический вложенный класс Builder.
     * <p>
     * Слово STATIC означает: этот вложенный класс НЕ привязан к экземпляру
     * HttpRequest. Его можно создать напрямую: new HttpRequest.Builder("GET", "...")
     * без предварительного создания объекта HttpRequest.
     */
    public static class Builder {

        // Обязательные — инициализируются в конструкторе Builder
        private final String method;
        private final String url;

        // Опциональные — с умолчаниями
        private final Map<String, String> headers = new HashMap<>();    // пустая карта заголовков
        private String body;                                            // тело отсутствует
        private int timeout = 30;                                       // 30 секунд — разумное умолчание
        private String authToken;                                       // без авторизации
        private boolean followRedirects;                                // следовать редиректам

        /**
         * Конструктор принимает только ОБЯЗАТЕЛЬНЫЕ параметры.
         * Валидация здесь, а не в build() — ошибка обнаруживается как можно раньше.
         *
         * @param method HTTP-метод (GET, POST, PUT, DELETE, PATCH и т.д.)
         * @param url    URL запроса — не должен быть пустым
         */
        public Builder(String method, String url) {
            this.method = method;
            this.url = url;
        }

        /**
         * Добавляет один заголовок к запросу.
         * Метод называется header() (не setHeader()), чтобы код читался как предложение:
         * builder.header("Content-Type", "application/json").header("Accept", "text/html")
         *
         * @param name  имя заголовка
         * @param value значение заголовка
         * @return this — для цепочки вызовов (fluent interface)
         */
        public Builder header(String name, String value) {
            this.headers.put(name, value);
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder timeout(int seconds) {
            if (seconds <= 0) {
                throw new IllegalArgumentException("Таймаут должен быть больше 0");
            }
            this.timeout = seconds;
            return this;
        }

        public Builder authToken(String token) {
            this.authToken = token;
            return this;
        }

        /**
         * Отключает автоматическое следование редиректам.
         * Именование без параметра: followRedirects(false) менее читаемо,
         * чем явный метод noRedirects().
         */
        public Builder noRedirects() {
            this.followRedirects = false;
            return this;
        }

        /**
         * Финальный метод — создаёт объект HttpRequest.
         * К этому моменту все обязательные поля уже проверены (в конструкторе Builder).
         *
         * @return готовый неизменяемый HttpRequest
         */
        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HttpRequest that = (HttpRequest) o;
        return timeout == that.timeout && followRedirects == that.followRedirects && Objects.equals(method, that.method) && Objects.equals(url, that.url) && Objects.equals(headers, that.headers) && Objects.equals(body, that.body) && Objects.equals(authToken, that.authToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, url, headers, body, timeout, authToken, followRedirects);
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                ", timeout=" + timeout +
                ", authToken='" + authToken + '\'' +
                ", followRedirects=" + followRedirects +
                '}';
    }
}

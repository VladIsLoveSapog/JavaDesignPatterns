package pattern1_creation.create1_builder.code.example1_static_builder;

/**
 * Демонстрация паттерна Builder на примере HttpRequest.
 *
 * Фокус примера: статический вложенный Builder.
 * Ключевая идея: Builder должен быть static, чтобы не требовал
 * существующего экземпляра внешнего класса для своего создания.
 */
public class HttpRequestMain {

    public static void main(String[] args) {

        System.out.println("=== Пример 1: Статический вложенный Builder — HttpRequest ===\n");

        // ─── Сценарий 1: Простой GET-запрос ──────────────────────────────────
        System.out.println("── Сценарий 1: Простой GET-запрос ──");
        // Только обязательные параметры — всё остальное из умолчаний:
        // timeout=30, followRedirects=true, headers={}, body=null
        HttpRequest getRequest = new HttpRequest.Builder("GET", "https://api.example.com/users")
                .build();
        System.out.println(getRequest);
        System.out.println();

        // ─── Сценарий 2: POST с заголовками, телом и авторизацией ────────────
        System.out.println("── Сценарий 2: POST-запрос с заголовками, телом и токеном ──");
        HttpRequest postRequest = new HttpRequest.Builder("POST", "https://api.example.com/users")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("X-Request-ID", "abc-123")
                .body("{\"name\": \"Иван\", \"email\": \"ivan@example.com\"}")
                .authToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
                .timeout(60)
                .build();
        System.out.println(postRequest);
        System.out.println();

        // ─── Сценарий 3: Запрос без следования редиректам ────────────────────
        System.out.println("── Сценарий 3: Запрос без автоматических редиректов ──");
        // Полезно, когда нужно самостоятельно обработать 301/302
        HttpRequest noRedirectRequest = new HttpRequest.Builder("GET", "https://short.link/abc")
                .noRedirects()
                .timeout(5)
                .build();
        System.out.println(noRedirectRequest);
        System.out.println("followRedirects = " + noRedirectRequest.isFollowRedirects());
        System.out.println();
    }
}

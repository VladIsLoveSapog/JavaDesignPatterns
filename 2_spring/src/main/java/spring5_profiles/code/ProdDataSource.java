package spring5_profiles.code;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Objects;

//Бин, активный при профиле "prod" — подключение к PostgreSQL
@Component
@Profile("prod")
public class ProdDataSource implements IDataSource {

    private final String url = "jdbc:postgresql://localhost:5432/proddb";
    private final String driverClassName = "org.postgresql.Driver";

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getDriverClassName() {
        return driverClassName;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "url='" + url + '\'' +
                ", driverClassName='" + driverClassName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdDataSource that = (ProdDataSource) o;
        return Objects.equals(url, that.url)
                && Objects.equals(driverClassName, that.driverClassName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, driverClassName);
    }
}

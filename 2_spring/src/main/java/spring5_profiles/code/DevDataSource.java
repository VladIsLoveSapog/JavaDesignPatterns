package spring5_profiles.code;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Objects;

//Бин, активный при профиле "dev" — подключение к встроенной H2 базе данных
@Component
@Profile("dev")
public class DevDataSource implements IDataSource {

    private final String url = "jdbc:h2:mem:devdb";
    private final String driverClassName = "org.h2.Driver";

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
        DevDataSource that = (DevDataSource) o;
        return Objects.equals(url, that.url)
                && Objects.equals(driverClassName, that.driverClassName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, driverClassName);
    }
}

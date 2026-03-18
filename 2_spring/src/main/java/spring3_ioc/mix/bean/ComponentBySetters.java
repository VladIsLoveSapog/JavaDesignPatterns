package spring3_ioc.mix.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Компонент, инициализация которого происходит через сеттеры.
 * Сначала создается пустой объект через конструктор без параметров.
 * Затем при помощи сеттеров производиться заполнение полей.
 * <p>
 * Аннотация @Component указывает, что объекты данного класса будут бинами, т.е.
 * будут создаваться и управляться Spring Container.
 * В данном случае явно указывается id класса.
 * <p>
 * Аннотация @PropertySource указывает файл, в котором лежат данные для подстановки при помощи аннотации @Value
 */
@Component("bySetters")
@PropertySource("component.property")
public class ComponentBySetters {
    private String host;
    private String port;
    private String creationsType;
    private Integer connections;

    public ComponentBySetters() {
    }

    public String getHost() {
        return host;
    }

    /**
     * Получение данных через @Value из resources/component.property
     */
    @Value("${componentBySetters.host}")
    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    /**
     * Получение данных через @Value из resources/component.property
     */
    @Value("${componentBySetters.port}")
    public void setPort(String port) {
        this.port = port;
    }

    public String getCreationsType() {
        return creationsType;
    }

    /**
     * Получение данных через @Value из resources/component.property
     */
    @Value("${componentBySetters.creationsType}")
    public void setCreationsType(String creationsType) {
        this.creationsType = creationsType;
    }

    public Integer getConnections() {
        return connections;
    }

    /**
     * Получение данных через @Value из resources/component.property
     */
    @Value("${componentBySetters.connections}")
    public void setConnections(Integer connections) {
        this.connections = connections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentBySetters that = (ComponentBySetters) o;
        return Objects.equals(host, that.host)
                && Objects.equals(port, that.port)
                && Objects.equals(creationsType, that.creationsType)
                && Objects.equals(connections, that.connections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port, creationsType, connections);
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", creationsType='" + creationsType + '\'' +
                ", connections=" + connections +
                '}';
    }
}

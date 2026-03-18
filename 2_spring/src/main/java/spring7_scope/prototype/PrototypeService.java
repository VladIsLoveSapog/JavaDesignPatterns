package spring7_scope.prototype;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

//Бин с областью видимости prototype
@Component
@Scope("prototype")
public class PrototypeService {
    private String host;
    private String port;
    private Integer connections;

    public PrototypeService(@Value("192.162.152.8") String host,
                            @Value("8080") String port,
                            @Value("50") Integer connections) {
        this.host = host;
        this.port = port;
        this.connections = connections;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Integer getConnections() {
        return connections;
    }

    public void setConnections(Integer connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", connections=" + connections +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrototypeService that = (PrototypeService) o;
        return Objects.equals(host, that.host)
                && Objects.equals(port, that.port)
                && Objects.equals(connections, that.connections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                host,
                port,
                connections);
    }
}

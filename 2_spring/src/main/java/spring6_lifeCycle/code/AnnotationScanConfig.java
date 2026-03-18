package spring6_lifeCycle.code;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//Конфигурационный класс в котором указан пакет для сканирования
@Configuration
@ComponentScan(basePackages = "spring6_lifeCycle")
public class AnnotationScanConfig {
}

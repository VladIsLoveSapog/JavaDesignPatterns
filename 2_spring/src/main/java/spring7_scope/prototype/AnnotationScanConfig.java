package spring7_scope.prototype;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//Конфигурационный файл, в котором указан класс для сканирования
@Configuration
@ComponentScan(basePackageClasses = PrototypeService.class)
public class AnnotationScanConfig {
}

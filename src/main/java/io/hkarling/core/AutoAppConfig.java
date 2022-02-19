package io.hkarling.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    excludeFilters = @Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
    // 예제코드의 유지를 위해 @Configuration 제외. 보통은 하지 않는다
)
public class AutoAppConfig {

}
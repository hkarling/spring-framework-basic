package io.hkarling.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//    basePackages = "io.hkarling.core.member", // 탐색 패키지 시작, 기본은 설정 클래스의 위치 하위. 따라서 최상단에 만든다
    excludeFilters = @Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
    // 예제코드의 유지를 위해 @Configuration 제외. 보통은 하지 않는다
)
public class AutoAppConfig {

}
package io.hkarling.core.scan.filter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {

    @Test
    @DisplayName("컴포넌트 스캔 필터 테스트")
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        assertThrows(
            NoSuchBeanDefinitionException.class,
            () -> ac.getBean("beanB", BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
        includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
        /* @FilterType 옵션
         *  - ANNOTATION : 기본값, 애노테이션 인식으로 동작, 보통 그냥 이거 쓴다
         *  - ASSIGNABLE_TYPE : 지정한 타입과 자식 타입을 인식해서 동작
         *  - ASPECTJ : AspectJ 패턴 사용
         *  - REGEX : 정규표현식
         *  - CUSTOM : TypeFilter 라는 인터페이스를 구현해서 처리
        */
    )
    static class ComponentFilterAppConfig {

    }


}

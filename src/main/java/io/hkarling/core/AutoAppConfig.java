package io.hkarling.core;

import io.hkarling.core.member.MemberRepository;
import io.hkarling.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
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

    /*@Bean("memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }*/
    /* 자동 빈과 수동 빈이 충돌시
     *  - 수동 빈이 우선권을 가지며 override 된다
     *  - Overriding bean definition for bean 'memoryMemberRepository' with a different definition: replacing
     *  - 최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본값을 바꾸었다
     *  - spring.main.allow-bean-definition-overriding=true
     */
}
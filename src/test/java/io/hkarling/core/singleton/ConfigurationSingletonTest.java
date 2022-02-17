package io.hkarling.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import io.hkarling.core.AppConfig;
import io.hkarling.core.member.MemberRepository;
import io.hkarling.core.member.MemberServiceImpl;
import io.hkarling.core.order.OrderService;
import io.hkarling.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository  = " + memberRepository2);
        System.out.println("memberRepository                  = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        // 출력: bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$bd479d70

        /* 순수한 클래스라면 class hello.core.AppConfig 와 같이 출력 되어야 한다
         * 이는 스프링이 CGLIB 라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은
         * 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것이다
         */
    }

}

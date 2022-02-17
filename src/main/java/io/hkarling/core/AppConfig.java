package io.hkarling.core;

import io.hkarling.core.discount.DiscountPolicy;
import io.hkarling.core.discount.FixDiscountPolicy;
import io.hkarling.core.discount.RateDiscountPolicy;
import io.hkarling.core.member.MemberRepository;
import io.hkarling.core.member.MemberService;
import io.hkarling.core.member.MemberServiceImpl;
import io.hkarling.core.member.MemoryMemberRepository;
import io.hkarling.core.order.OrderService;
import io.hkarling.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 애플리케이션의 전체 동작 방식을 구성(config)하기 위해,
 * 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스를 작성.
 * 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
 * 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입(연결)해준다.
 */
@Configuration
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()

    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberService
    // call AppConfig.memberRepository

    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /**
     * 역할을 고려한 구현
     */
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy(); // 할인정책 변경 시 여기만 수정하면 된다
        return new RateDiscountPolicy();
    }

}

package io.hkarling.core;

import io.hkarling.core.discount.FixDiscountPolicy;
import io.hkarling.core.member.MemberService;
import io.hkarling.core.member.MemberServiceImpl;
import io.hkarling.core.member.MemoryMemberRepository;
import io.hkarling.core.order.OrderService;
import io.hkarling.core.order.OrderServiceImpl;

/**
 * 애플리케이션의 전체 동작 방식을 구성(config)하기 위해,
 * 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스를 작성.
 * 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
 * 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입(연결)해준다.
 */
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}

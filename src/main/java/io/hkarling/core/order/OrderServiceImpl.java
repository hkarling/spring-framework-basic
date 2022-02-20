package io.hkarling.core.order;

import io.hkarling.core.discount.DiscountPolicy;
import io.hkarling.core.member.Member;
import io.hkarling.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

//    @Autowired // 3. 필드 주입 - 외부에서 변경이 불가능하여 DI 프레임워크 없이 테스트가 안됨. 지양하라
    private final MemberRepository memberRepository;
//    @Autowired // 단, 애플리케이션의 실제코드와 상관없는 테스트 코드 및 스프링 설정 목적 @Configuration 에서는 쓴다
    private final DiscountPolicy discountPolicy;

    /* 1. 생성자 주입 (빈 생성과 주입 시점이 같다)
     *  - 생성자 호출시점에 딱 1번만 호출되는 것이 보장된다
     *  - 불변, 필수 의존관계에 사용
     *  - 스프링 빈이고 생성자 1개일 시 @Autowired 생략 가능
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /* 2. 수정자 주입
     * - setter 라 불리는 필드의 값을 변경하는 수정자 메서드를 통하여 의존관계를 주입
     * - 선택, 변경 가능성이 있는 의존관계에 사용
     * - 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법
     *   - 자바에서는 필드의 값을 직접 변경하지 않고 get/set 을 통해 수정하는 규칙을 만들었다 -> 자바빈 프로퍼티 규약
     */
    /*@Autowired // (required = false) 주입할 대상이 없어도 동작하게 할때, 주입대상이 없으면 원래 오류난다
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }*/

    /* 4. 일반 메서드 주입
     *  - 일반 메서드를 통해서 주입받을 수 있다 - 쓰지 않는다
     */
    /*@Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}

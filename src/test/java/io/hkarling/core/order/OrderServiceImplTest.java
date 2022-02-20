package io.hkarling.core.order;

import static org.junit.jupiter.api.Assertions.*;

import io.hkarling.core.discount.FixDiscountPolicy;
import io.hkarling.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

    /* 생성자 주입을 선택해라!
     *  - 불변
     *    - 대부분의 의존관계 주입은 한번만 일어나며 애플리케이션 종료 시점까지 의존관계를 변경할 일이 없다
     *      오히려 대부분의 의존관계는 애플리케이션 종료 전까지 변하면 안된다 (불변해야 한다)
     *    - 수정자 주입을 사용하면, setXXX 메서드를 public 으로 열어두어야 한다
     *    - 누군가 실수로 변경할 수 도 있고, 변경하면 안되는 메서드를 열어두는 것은 좋은 설계 방법이 아니다
     *    - 생성자 주입은 객체를 생성할 때 딱 1번만 호출되므로 이후에 호루되는 일이 없다
     *      따라서 불변하게 설계할 수 있다
     */

    @Test
    void CreatOrder() {
//        OrderServiceImpl orderService = new OrderServiceImpl(); // 의존관계를 뭘 넣어야 되는지? 누락된다
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        //orderService.createOrder(1L, "itemA", 10000);
    }

}
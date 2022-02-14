package io.hkarling.core;

import io.hkarling.core.member.Grade;
import io.hkarling.core.member.Member;
import io.hkarling.core.member.MemberService;
import io.hkarling.core.member.MemberServiceImpl;
import io.hkarling.core.order.Order;
import io.hkarling.core.order.OrderService;
import io.hkarling.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = appContext.getBean("memberService", MemberService.class);
        OrderService orderService = appContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(memberA);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
    }

}

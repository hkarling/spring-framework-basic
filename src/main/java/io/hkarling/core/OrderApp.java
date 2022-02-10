package io.hkarling.core;

import io.hkarling.core.member.Grade;
import io.hkarling.core.member.Member;
import io.hkarling.core.member.MemberService;
import io.hkarling.core.member.MemberServiceImpl;
import io.hkarling.core.order.Order;
import io.hkarling.core.order.OrderService;
import io.hkarling.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(memberA);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
    }

}

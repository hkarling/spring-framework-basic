package io.hkarling.core.order;

import io.hkarling.core.discount.DiscountPolicy;
import io.hkarling.core.discount.FixDiscountPolicy;
import io.hkarling.core.member.Member;
import io.hkarling.core.member.MemberRepository;
import io.hkarling.core.member.MemoryMemberRepositoryImpl;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepositoryImpl();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}

package io.hkarling.core.discount;

import io.hkarling.core.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);

}

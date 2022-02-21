package io.hkarling.core.discount;

import io.hkarling.core.member.Grade;
import io.hkarling.core.member.Member;
import org.springframework.stereotype.Component;

@Component // expected single matching bean but found 2: fixDiscountPolicy,rateDiscountPolicy
public class FixDiscountPolicy implements DiscountPolicy {

    private final int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}

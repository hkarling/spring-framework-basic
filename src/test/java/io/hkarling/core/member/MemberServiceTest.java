package io.hkarling.core.member;

import io.hkarling.core.AppConfig;
import io.hkarling.core.discount.DiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

//    MemberService memberService = new MemberServiceImpl(memberRepository); // DIP 위반. 추상화에도 의존하고 구현체에도 의존함
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(findMember).isEqualTo(findMember);
    }

    @Test
    void findMember() {
    }
}
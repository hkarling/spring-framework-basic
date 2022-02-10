package io.hkarling.core;

import io.hkarling.core.member.Grade;
import io.hkarling.core.member.Member;
import io.hkarling.core.member.MemberService;
import io.hkarling.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }

}

package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {


    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {

        memberRepository.clearStore();
    }

    @Test
    void join() throws Exception{
        //given
        Member member= new Member();
        member.setName("hi");
        //when

        Long saveID = memberService.join(member);

        //then
        Member findMember = memberRepository.findById(saveID).get();
        assertEquals(member.getName(),findMember.getName());
    }



    @Test
    public void 중복회원_예외() throws Exception {
        Member member1 = new Member();
        member1.setName("m1");

        Member member2 = new Member();
        member2.setName("m1");


        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }

    // 전체 회원 조회
    @Test
    void findMember() {

    }

    @Test
    void findOne() {
    }
}
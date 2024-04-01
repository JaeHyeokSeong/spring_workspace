package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("성재혁");

        // when
        Long savedId = memberService.join(member);

        // then
        Member searchedMember = memberService.findMemberById(savedId).get();
        Assertions.assertThat(member.getName()).isEqualTo(searchedMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("성재혁");

        Member member2 = new Member();
        member2.setName("성재혁");

        // when
        memberService.join(member1);

        // then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat("이미 존재하는 회원입니다.").isEqualTo(e.getMessage());
    }

    @Test
    void findMembers() {
    }

    @Test
    void findMemberById() {
    }
}

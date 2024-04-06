package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    // single ton 을 유지하기 위해서
    MemberRepository memberRepository = MemberRepository.getInstance();

    /**
     * each test 이후에 memberRepository에 저장된 값 지우기
     */
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("save member")
    void save() {
        // given
        Member member1 = new Member("member1", 20);

        // when
        Member savedMember = memberRepository.save(member1);

        // then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isSameAs(member1);
    }

    @Test
    void findAll() {
        // given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        List<Member> result = memberRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}
package hello.springtx.propagation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class SpringTxTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LogRepository logRepository;

    private static final String username = "user1";

    @Test
    void test1() {
        memberService.joinV1(username);
    }

    @Test
    void test2() {
        assertTrue(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isPresent());
    }
}

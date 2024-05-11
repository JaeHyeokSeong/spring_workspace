package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        /*List<Member> members = findAll();

        for (Member member : members) {
            if (member.getLoginId().equals(loginId)) {
                return Optional.of(member);
            }
        }

        // loginId 존재X
        return Optional.empty();*/

        // 위의 내용을 좀더 간결하게 적는 방법
        return findAll().stream()
                .filter((member) -> member.getLoginId().equals(loginId))
                .findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}

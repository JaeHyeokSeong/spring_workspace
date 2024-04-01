package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {

    private final int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        // vip 등급
        if(member.getGrade() == Grade.Vip) {
            return discountFixAmount;
        }

        // basic 등급
        return 0;
    }
}

package hello.core.scan;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService bean = ac.getBean(MemberService.class);
        Assertions.assertThat(bean).isInstanceOf(MemberService.class);
        Assertions.assertThat(bean).isInstanceOf(MemberServiceImpl.class);

        OrderServiceImpl orderServiceImpl = ac.getBean("orderServiceImpl", OrderServiceImpl.class);
        MemberRepository memberRepository = orderServiceImpl.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);

        DiscountPolicy discountPolicy = orderServiceImpl.getDiscountPolicy();
        Assertions.assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

}

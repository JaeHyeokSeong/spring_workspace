package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecoratorV1 extends Decorator {

    public TimeDecoratorV1(Component component) {
        super(component);
    }

    @Override
    public String operation() {
        log.info("TimeDecoratorV1 실행");
        long startTime = System.currentTimeMillis();

        String result = super.component.operation();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeDecoratorV1 종료 resultTime={}", resultTime);
        return result;
    }
}

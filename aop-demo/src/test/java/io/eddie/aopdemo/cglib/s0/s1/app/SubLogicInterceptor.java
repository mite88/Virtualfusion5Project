package io.eddie.aopdemo.cglib.s0.s1.app;

import io.eddie.aopdemo.proxy.jdk_dytmanic_proxy.s02.PaymentService;
import io.eddie.aopdemo.proxy.jdk_dytmanic_proxy.s02.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;
import org.jspecify.annotations.Nullable;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * packageName    : io.eddie.aopdemo.cglib.s0.s1.app
 * fileName       : SubLogicIntercptor
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */
@Slf4j
@RequiredArgsConstructor
public class SubLogicInterceptor implements MethodInterceptor {

    private final MockService target;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        log.info("전처리");
        Object result = proxy.invoke(target, args); // 리플렉션 대신 MethodProxy 사용 추천
        log.info("후처리");
        return result;
    }
}

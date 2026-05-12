package io.eddie.aopdemo.spring_proxy;

import io.eddie.aopdemo.cglib.s0.s1.app.SubLogicInterceptor;
import io.eddie.aopdemo.spring_proxy.app.ConcerteMockService;
import io.eddie.aopdemo.spring_proxy.app.MockService;
import io.eddie.aopdemo.spring_proxy.app.MockServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;
import java.net.ConnectException;

/**
 * packageName    : io.eddie.aopdemo.spring_proxy.app
 * fileName       : SprinvProxyTests
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */
@Slf4j
public class SpringProxyTests {

    @Test
    @DisplayName("JDK 동적 프록시 사용")
    void proxy_test(){
        MockServiceImpl mockService = new MockServiceImpl();

        ProxyFactory factory = new ProxyFactory(mockService);
        factory.addAdvice(new SubLogicInterceptor());

        MockService proxy = (MockService) factory.getProxy();
        log.info("factory.getClass()={}",factory.getClass());
        log.info("proxy.getClass()={}",proxy.getClass());
    }

    static class SubLogicInterceptor implements MethodInterceptor {

        @Override
        public @Nullable Object invoke(MethodInvocation invocation) throws Throwable {

            log.info("전처리");
            Object result = invocation.proceed();
            log.info("후처리");

            return result;
        }
    }

    @Test
    void test2(){

        // 1. 구상 클래스(인터페이스 없음) 생성
        ConcerteMockService mockService2 = new ConcerteMockService();

        // 2. 프록시 팩토리 생성 (factory2)
        ProxyFactory factory2 = new ProxyFactory(mockService2);
        factory2.addAdvice(new SubLogicInterceptor());

        ConcerteMockService proxy2 = (ConcerteMockService) factory2.getProxy();

        log.info("factory2.getClass()={}",factory2.getClass());
        log.info("proxy2.getClass()={}",proxy2.getClass());

    }

    @Test
    void t3() {

        MockService mockService = new MockServiceImpl();

        ProxyFactory factory = new ProxyFactory(mockService);
        factory.setProxyTargetClass(true);
        factory.addAdvice(new SubLogicInterceptor());

        MockService proxy = (MockService) factory.getProxy();

        log.info("factory.getClass() = {}", factory.getClass());
        log.info("proxy.getClass() = {}", proxy.getClass());

    }
}

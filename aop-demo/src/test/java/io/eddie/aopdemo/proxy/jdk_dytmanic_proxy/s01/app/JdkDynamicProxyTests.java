package io.eddie.aopdemo.proxy.jdk_dytmanic_proxy.s01.app;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

/**
 * packageName    : io.eddie.aopdemo.proxy.jdk_dytmanic_proxy.s01.app
 * fileName       : JdkDynamicProxyTests
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */
public class JdkDynamicProxyTests {

    @Test
    void test1(){
        MockSeriviceImpl targetObject = new MockSeriviceImpl();
        SubLogicInvocationHandler handler = new SubLogicInvocationHandler(targetObject);

        MockService proxy = (MockService) Proxy.newProxyInstance(
                MockService.class.getClassLoader(),
                new Class[]{MockService.class},
                handler);

        proxy.logic1();
        proxy.logic2();
    }
}

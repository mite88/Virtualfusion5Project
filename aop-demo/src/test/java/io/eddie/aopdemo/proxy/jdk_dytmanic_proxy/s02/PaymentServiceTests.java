package io.eddie.aopdemo.proxy.jdk_dytmanic_proxy.s02;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

/**
 * packageName    : io.eddie.aopdemo.proxy.jdk_dytmanic_proxy.s02
 * fileName       : PaymentServiceTests
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */
public class PaymentServiceTests {

    @Test
    void test1(){
        PaymentServiceImpl targetObject = new PaymentServiceImpl();
        PaymentHandler paymentHandler = new PaymentHandler(targetObject);

        PaymentService proxy= (PaymentService)Proxy.newProxyInstance(
                PaymentService.class.getClassLoader(),
                new Class[]{PaymentService.class},
                paymentHandler
        );

        proxy.payment();

    }
}

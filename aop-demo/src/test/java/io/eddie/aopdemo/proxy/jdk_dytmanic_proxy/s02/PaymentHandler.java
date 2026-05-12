package io.eddie.aopdemo.proxy.jdk_dytmanic_proxy.s02;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * packageName    : io.eddie.aopdemo.proxy.jdk_dytmanic_proxy.s02
 * fileName       : PaymentHandler
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
public class PaymentHandler implements InvocationHandler {

    private final PaymentService paymentService;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("결제 전 로깅");
        Object result = method.invoke(paymentService, args);
        log.info("결제 후 로깅");
        return result;
    }
}

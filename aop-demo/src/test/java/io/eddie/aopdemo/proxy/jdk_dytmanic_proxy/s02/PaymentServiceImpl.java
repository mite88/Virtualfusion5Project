package io.eddie.aopdemo.proxy.jdk_dytmanic_proxy.s02;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName    : io.eddie.aopdemo.proxy.jdk_dytmanic_proxy.s02
 * fileName       : PaymentServiceImpl
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */
@Slf4j
public class PaymentServiceImpl implements PaymentService{
    @Override
    public void payment() {
        log.info("결제 수행");
    }
}

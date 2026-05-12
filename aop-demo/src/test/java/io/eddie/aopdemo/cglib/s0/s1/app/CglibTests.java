package io.eddie.aopdemo.cglib.s0.s1.app;

import io.eddie.aopdemo.proxy.jdk_dytmanic_proxy.s02.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

/**
 * packageName    : io.eddie.aopdemo.cglib.s0.s1.app
 * fileName       : CglibTests
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */
public class CglibTests {

    @Test
    void Test1(){
        // 1. 진짜 알맹이 객체 생성
        MockService targetObject = new MockService();

        // 2. CGLIB의 핵심! 프록시를 만들어주는 Enhancer 기계 세팅
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MockService.class); // "MockService를 상속받아서 프록시를 만들어라!"

        enhancer.setCallback(new SubLogicInterceptor(targetObject));

        // 3. 프록시 객체 생성
        MockService proxy = (MockService) enhancer.create();

        // 4. 실행 (로그가 정상적으로 모두 출력됨!)
        proxy.logic1();
        //동적으로 만들기때문에 출력은 안됨
    }
}

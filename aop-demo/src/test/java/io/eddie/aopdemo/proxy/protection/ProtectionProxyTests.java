package io.eddie.aopdemo.proxy.protection;

import org.junit.jupiter.api.Test;

/**
 * packageName    : io.eddie.aopdemo.proxy.protection
 * fileName       : ProtectionProxyTests
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :보호 프록시 실습
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */

public class ProtectionProxyTests {

    //배포가 안될거니깐 한글로 적어도되지만........
    @Test
    /*void protection_test1(){
        RealSubject subject = new RealSubject();

        subject.operation();
    }*/
    void protection_test2(){
        RealSubject subject = new RealSubject();
        Proxy proxy = new Proxy(subject);

        proxy.operation();
    }
}

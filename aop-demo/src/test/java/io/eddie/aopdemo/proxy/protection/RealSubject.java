package io.eddie.aopdemo.proxy.protection;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * packageName    : io.eddie.aopdemo.proxy.protection
 * fileName       : RealSubject
 * author         : Admin
 * date           : 26. 5. 11.
 * description    : 보호 프록시 실습
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */
@Slf4j
public class RealSubject implements Subject{
    //@Slf4j 하면 필요없음
    //private static final Logger log = LoggerFactory.getLogger(RealSubject.class);

    @Override
    public void operation() {
        log.info("RealSubject.operation()");
        log.info("Action");
    }
}

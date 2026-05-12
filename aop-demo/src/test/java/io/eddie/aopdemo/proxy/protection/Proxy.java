package io.eddie.aopdemo.proxy.protection;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName    : io.eddie.aopdemo.proxy.protection
 * fileName       : Proxy
 * author         : Admin
 * date           : 26. 5. 11.
 * description    : 보호 프록시 실습
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */

@Slf4j
public class Proxy implements Subject{

    private Subject targetObject;

    public Proxy(Subject targetObject){
        this.targetObject = targetObject;
    }

    @Override
    public void operation() {
        log.info("[prefix]Proxy.operation()"); // 전처리
        targetObject.operation();
        log.info("[postfix]Proxy.operation()"); // 후처리
    }
}

package io.eddie.aopdemo.spring_proxy.app;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName    : io.eddie.aopdemo.spring_proxy.app
 * fileName       : ConcerteMockServiced
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */

@Slf4j
public class ConcerteMockService { // ⭐ 'implements MockService' 제거!

    // ⭐ 더 이상 인터페이스의 메서드를 구현하는 것이 아니므로 @Override도 제거합니다.
    public void logic1() {
        log.info("[SUBJECT]2");
    }
}
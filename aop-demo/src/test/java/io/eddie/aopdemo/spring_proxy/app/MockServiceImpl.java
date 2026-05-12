package io.eddie.aopdemo.spring_proxy.app;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName    : io.eddie.aopdemo.spring_proxy.app
 * fileName       : MockServiceImpl
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */
@Slf4j
public class MockServiceImpl implements  MockService{
    @Override
    public void logic1() {
        log.info("[SUBJECT]1");
    }
}

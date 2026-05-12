package io.eddie.aopdemo.cglib.s0;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName    : io.eddie.aopdemo.cglib.s0
 * fileName       : ProxyUserService
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :CGLIB
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */
//상속으로...
@Slf4j
public class ProxyUserService extends UserService{

    //원본객체에 대한거 불러옴
    @Override
    public void createUser() {
        log.info("전처리");
        super.createUser();
        log.info("후처리");
    }
}

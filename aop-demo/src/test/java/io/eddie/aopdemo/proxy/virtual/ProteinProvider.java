package io.eddie.aopdemo.proxy.virtual;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName    : io.eddie.aopdemo.proxy.virtual
 * fileName       : ProteinProvider
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :가상 프록시 실습
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */
@Slf4j
public class ProteinProvider implements SupplementProvider{
    @Override
    public String provide() throws Exception {
        log.info("프로틴 생산자가 프로틴을 포장해서 보내줍니다");

        return "제품1";
    }
}

package io.eddie.aopdemo.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * packageName    : io.eddie.aopdemo.repository
 * fileName       : AopRepository
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */
@Slf4j
@Repository
public class AopRepository {

    public String logic() {
        log.info("[AopRepository] 리포지토리(데이터베이스) 데이터 저장 실행");

        // 데이터베이스에 무언가 저장했다고 가정하고 "ok"를 반환해 봅니다.
        return "ok";
    }
}

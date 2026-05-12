package io.eddie.aopdemo.service;

import io.eddie.aopdemo.repository.AopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * packageName    : io.eddie.aopdemo.service
 * fileName       : AopService
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AopService {

    private final AopRepository repository;

    public void logic1(){
        log.info("[AopService] 서비스 로직 시작");
        repository.logic();
        log.info("[AopService] 서비스 로직 종료");

    }
}

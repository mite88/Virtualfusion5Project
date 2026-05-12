package io.eddie.aopdemo.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * packageName    : io.eddie.aopdemo.aspects
 * fileName       : LoggingAspcet
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    //SpEL
    //@Around("execution(public void io.eddie.aopdemo.service.AopService.logic1())")
    @Around("@annotation(io.eddie.aopdemo.config.Logging)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{

        //postman에서 실행하세요

        log.info("[LoggingAspect] 횡단 관심사 로깅 시작");
        Object result = joinPoint.proceed();
        log.info("[LoggingAspect] 횡단 관심사 로깅 마침");

        return result;
    }

}

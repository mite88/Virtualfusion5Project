package io.eddie.aopdemo.proxy.jdk_dytmanic_proxy.s01.app;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * packageName    : io.eddie.aopdemo.proxy.jdk_dytmanic_proxy.s01.app
 * fileName       : SubLogicInvocationHandler
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */
@Slf4j
public class SubLogicInvocationHandler implements InvocationHandler {


    private Object targetObject;

    public SubLogicInvocationHandler(Object targetObject) {
        this.targetObject = targetObject;
    }

    //범용성 증가
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("[BEFORE] 로직실행 전");
        Object result = method.invoke(targetObject, args);
        log.info("[BEFORE] 로직실행후 ");

        return result;
    }
}

package io.eddie.jwt.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.el.util.Validation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * packageName    : io.eddie.jwt.config.properties
 * fileName       : JwtProperties
 * author         : Admin
 * date           : 26. 5. 22.
 * description    : yaml 설정을 빈으로 등록
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 22.        Admin       최초 생성
 */
@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "custom.jwt")
public class JwtProperties {

    private final Secrets secrets;
    private final Validation validation;

    @RequiredArgsConstructor
    @Getter
    public static class Secrets{
        private final String appKey;
        private final String originKey;
    }

    @RequiredArgsConstructor
    @Getter
    public static class Validation{
        private final Long access;
        private final Long refresh;
    }

}

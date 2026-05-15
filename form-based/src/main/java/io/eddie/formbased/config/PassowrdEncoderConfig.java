package io.eddie.formbased.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * packageName    : io.eddie.formbased.config
 * fileName       : PassowrdEncoderConfig
 * author         : Admin
 * date           : 26. 5. 15.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 15.        Admin       최초 생성
 */
@Configuration
public class PassowrdEncoderConfig {
    //혹시나 순환참조 발생을 위해 새로 만듬

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }
}

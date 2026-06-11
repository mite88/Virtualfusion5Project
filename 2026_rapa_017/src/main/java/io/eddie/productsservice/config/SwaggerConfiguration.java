package io.eddie.productsservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : io.eddie.productsservice.config
 * fileName       : SwaggerConfiguration
 * author         : Admin
 * date           : 26. 6. 11.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 6. 11.        Admin       최초 생성
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("상품  API 문서")
                                .description("상품과 곤련된 Rest API 문서")
                                .version("0.0.1")
                );
    }
}

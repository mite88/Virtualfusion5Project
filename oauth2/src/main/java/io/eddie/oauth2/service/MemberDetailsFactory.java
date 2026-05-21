package io.eddie.oauth2.service;

import io.eddie.oauth2.dto.MemberDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

/**
 * packageName    : io.eddie.oauth2
 * fileName       : MemberDetailsFactory
 * author         : Admin
 * date           : 26. 5. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 21.        Admin       최초 생성
 */
public class MemberDetailsFactory {
    public static MemberDetails memberDetails(String provider, OAuth2User oAuth2User){
        Map<String, Object> attributes = oAuth2User.getAttributes();

        return switch (provider.toUpperCase()){
            case "GOOGLE" ->{
                yield  MemberDetails.builder()
                        .email(attributes.get("email").toString())
                        .name((attributes.get("name").toString()))
                        .attributes(attributes)
                        .build();
            } case "NANER" -> {
                Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");

                yield  MemberDetails.builder()
                        .email(attributes.get("id").toString() + "@kakao.com")
                        .name((attributes.get("name").toString()))
                        .attributes(attributes)
                        .build();
            }
            default -> null;
        };
    }
}

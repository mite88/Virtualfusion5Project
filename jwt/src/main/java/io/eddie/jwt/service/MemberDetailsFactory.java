package io.eddie.jwt.service;


import io.eddie.jwt.dto.MemberDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class MemberDetailsFactory {

    public static MemberDetails memberDetails(String provider, OAuth2User oAuth2User) {

        Map<String, Object> attributes = oAuth2User.getAttributes();

        switch (provider.toUpperCase()) {
            case "GOOGLE" -> {
                return MemberDetails.builder()
                        .email(attributes.get("email").toString())
                        .name(attributes.get("name").toString())
                        .attributes(attributes)
                        .build();
            }
            case "KAKAO" -> {
                Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
                return MemberDetails.builder()
                        .email(attributes.get("id").toString() + "@kakao.com")
                        .name(properties.get("nickname").toString())
                        .attributes(attributes)
                        .build();
            }
            case "NAVER" -> {
                Map<String, Object> properties = (Map<String, Object>) attributes.get("response");
                return MemberDetails.builder()
                        .email(properties.get("email").toString())
                        .name(properties.get("nickname").toString())
                        .attributes(attributes)
                        .build();
            }
            default -> throw new IllegalArgumentException("지원하지 않는 제공자입니다 : " + provider);
        }

    }

}

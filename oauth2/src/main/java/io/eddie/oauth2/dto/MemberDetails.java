package io.eddie.oauth2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * packageName    : io.eddie.oauth2.dto
 * fileName       : MemberDetails
 * author         : Admin
 * date           : 26. 5. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 21.        Admin       최초 생성
 */
@Getter
public class MemberDetails implements OAuth2User {

    private String email;
    private String name;
    @Setter
    private String role;
    private  Map<String, Object> attributes;

    @Builder
    public MemberDetails(String email, String name, String role, Map<String, Object> attributes) {
        this.email = email;
        this.name = name;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }
}
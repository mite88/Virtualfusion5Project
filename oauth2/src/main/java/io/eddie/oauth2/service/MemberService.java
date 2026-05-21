package io.eddie.oauth2.service;

import io.eddie.oauth2.dao.MemberRepository;
import io.eddie.oauth2.domain.Member;
import io.eddie.oauth2.dto.MemberDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * packageName    : io.eddie.oauth2.service
 * fileName       : MemberService
 * author         : Admin
 * date           : 26. 5. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 21.        Admin       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;
    //ctrl + o


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

       String provider = userRequest.getClientRegistration().getRegistrationId().toUpperCase();
       MemberDetails memberDetails =  MemberDetailsFactory.memberDetails(provider, oAuth2User);


        //log.info("OAuth2UserRequest = {}", userRequest);
        //log.info("OAuth2UserRequest = {}", userRequest.getClientRegistration().getRegistrationId());

        //여기에 Client -> Authorization Server로 Request를 보내는 로직이 들어감
        //return super.loadUser(userRequest);

        //Map<String, Object> attributes = oAuth2User.getAttributes();

        //attributes.forEach((key, value) -> log.info("Attribute: {}, Value: {}", key, value));

        //String email = attributes.get("email").toString();

       //Optional<Member> memberOptional = memberRepository.findByEmail(email);
        Optional<Member> memberOptional = memberRepository.findByEmail(memberDetails.getEmail());

        Member member = memberOptional.orElseGet(() ->
            memberRepository.save(Member.builder()
                    .name(memberDetails.getName())
                    .email(memberDetails.getEmail())
                    .provider(userRequest.getClientRegistration().getRegistrationId())
                    .nickname("치킨같은 너")
                    .build())
        );

        /*if(memberOptional.isEmpty()){
            Member member = Member.builder()
                    .name(attributes.get("name").toString())
                    .email(email)
                    .provider(userRequest.getClientRegistration().getRegistrationId())
                    .nickname("치킨같은 너")
                    .build();

            memberRepository.save(member);
            return oAuth2User;
        }*/

        //Member member = memberOptional.get();

        return new MemberDetails(member.getEmail(), member.getName(), member.getRole(), memberDetails.getAttributes());
    }
}

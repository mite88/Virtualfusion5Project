package io.eddie.jwt.service;

import io.eddie.jwt.dao.MemberRepository;
import io.eddie.jwt.domain.Member;
import io.eddie.jwt.dto.MemberDetails;
import io.eddie.jwt.mapper.MemberMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService extends DefaultOAuth2UserService {

    private final MemberRepository repository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId().toUpperCase();
        MemberDetails memberDetails = MemberDetailsFactory.memberDetails(provider, oAuth2User);

        Optional<Member> memberOptional = repository.findByEmail(memberDetails.getEmail());

        Member findMember = memberOptional.orElseGet(() ->
            repository.save(Member.builder()
                    .name(memberDetails.getName())
                    .email(memberDetails.getEmail())
                    .provider(provider)
                    .nickname(memberDetails.getName())
                    .build())
        );

        if ( findMember.getProvider().equals(provider) ) {
            memberDetails.setRole(findMember.getRole());
            return memberDetails;
        } else {
            throw new RuntimeException("Provider mismatch");
        }

    }

    public Optional<Member> findById(Long id) {
        return repository.findById(id);
    }

    public Member getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 회원은 존재하지 않습니다."));
    }

    public MemberDetails loadMemberDetailsById(Long id) {

        Member findMember = getById(id);

        return MemberDetails.from(findMember);
//        return MemberMapper.toMemberDetails(findMember);

    }

}

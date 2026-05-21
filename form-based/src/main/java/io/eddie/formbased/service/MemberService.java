package io.eddie.formbased.service;

import io.eddie.formbased.dao.MemberRepository;
import io.eddie.formbased.domain.Member;
import io.eddie.formbased.dto.MemberDetails;
import io.eddie.formbased.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * packageName    : io.eddie.formbased.service
 * fileName       : MemberService
 * author         : Admin
 * date           : 26. 5. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 21.        Admin       최초 생성
 */
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //void 변환
    @Transactional
    public void save(SignUpRequest request){

        Member member = Member.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .build();

        //DB 회원정보 저장
        memberRepository.save(member);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> memberOptional = memberRepository.findByUsername(username);

        Member member = memberOptional.orElseThrow(
                () -> new UsernameNotFoundException("해당 회원을 찾을 수 없습니다.")
        );


        return new MemberDetails(
                member.getUsername(),
                member.getPassword(),
                member.getRole()
        );
    }
}

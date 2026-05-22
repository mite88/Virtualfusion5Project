package io.eddie.jwt.dao;

import io.eddie.jwt.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName    : io.eddie.oauth2.dao
 * fileName       : MemberRepository
 * author         : Admin
 * date           : 26. 5. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 21.        Admin       최초 생성
 */

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}

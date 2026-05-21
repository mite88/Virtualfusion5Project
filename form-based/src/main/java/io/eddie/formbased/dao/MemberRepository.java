package io.eddie.formbased.dao;

import io.eddie.formbased.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName    : io.eddie.formbased.dto
 * fileName       : MemberRequest
 * author         : Admin
 * date           : 26. 5. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 21.        Admin       최초 생성
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

}
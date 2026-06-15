package io.dnlwjtud.blog.members.repository;

import io.dnlwjtud.blog.members.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

<<<<<<< HEAD
    Optional<Member> findByEmail(String email);
=======
    Optional<Member> findByProviderId(String providerId);
>>>>>>> 1c874ab668465e2fe8b05767c841230044bbc07e

}

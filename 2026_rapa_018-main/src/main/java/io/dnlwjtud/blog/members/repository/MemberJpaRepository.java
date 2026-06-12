package io.dnlwjtud.blog.members.repository;

import io.dnlwjtud.blog.members.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    Optional<Member> findByProviderId(String providerId);

}

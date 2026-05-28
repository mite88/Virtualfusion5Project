package io.eddie.jwt.dao;

import io.eddie.jwt.domain.RefreshToken;
import io.eddie.jwt.domain.RefreshTokenBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenBlackListRepository
        extends JpaRepository<RefreshTokenBlackList, Long> {

    boolean existsByRefreshToken(RefreshToken refreshToken);

}

package io.eddie.jwt.dao.adpater;

import io.eddie.jwt.dao.RefreshTokenBlackListRepository;
import io.eddie.jwt.dao.RefreshTokenRepository;
import io.eddie.jwt.dao.TokenRepository;
import io.eddie.jwt.domain.Member;
import io.eddie.jwt.domain.RefreshToken;
import io.eddie.jwt.domain.RefreshTokenBlackList;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryAdapter implements TokenRepository {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenBlackListRepository blackListRepository;

    private final EntityManager entityManager;


    @Override
    public RefreshToken save(Member member, String token) {
        return refreshTokenRepository.save(
                RefreshToken.builder()
                        .refreshToken(token)
                        .member(member)
                        .build()
        );
    }

    @Override
    public Optional<RefreshToken> findValidRefTokenByToken(String token) {

        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findByRefreshToken(token);

        if ( refreshTokenOptional.isEmpty() ) return refreshTokenOptional;

        RefreshToken findRefreshToken = refreshTokenOptional.get();

        return isBannedRefreshToken(findRefreshToken)
                ? Optional.empty()
                : Optional.of(findRefreshToken);

    }

    @Override
    public Optional<RefreshToken> findValidRefTokenByMemberId(Long memberId) {
        return entityManager.createQuery("""
            select
                rf
            from
                RefreshToken rf
            left join
                RefreshTokenBlackList rtb
            on
                rtb.refreshToken = rf
            where
                rf.member.id = :memberId
              and
                rtb.id is null
            """, RefreshToken.class)
            .setParameter("memberId", memberId)
            .getResultStream()
            .findFirst();
    }

    @Override
    public RefreshToken appendBlackList(RefreshToken refreshToken) {
        blackListRepository.save(new RefreshTokenBlackList(refreshToken));

        return refreshToken;
    }

    private boolean isBannedRefreshToken(RefreshToken refreshToken) {
        return blackListRepository.existsByRefreshToken(refreshToken);
    }

}

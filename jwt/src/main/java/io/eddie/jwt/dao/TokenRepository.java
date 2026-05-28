package io.eddie.jwt.dao;

import io.eddie.jwt.domain.Member;
import io.eddie.jwt.domain.RefreshToken;

import java.util.Optional;

public interface TokenRepository {

     RefreshToken save(Member member, String token);

     Optional<RefreshToken> findValidRefTokenByToken(String token);
     Optional<RefreshToken> findValidRefTokenByMemberId(Long memberId);

     RefreshToken appendBlackList(RefreshToken refreshToken);

}

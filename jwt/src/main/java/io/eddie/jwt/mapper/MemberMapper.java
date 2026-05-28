package io.eddie.jwt.mapper;

import io.eddie.jwt.domain.Member;
import io.eddie.jwt.dto.MemberDetails;

public class MemberMapper {

    public static MemberDetails toMemberDetails(Member member) {
        return MemberDetails.builder()
                .build();
    }

}

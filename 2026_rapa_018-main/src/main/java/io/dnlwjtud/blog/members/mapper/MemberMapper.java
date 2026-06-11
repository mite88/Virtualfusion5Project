package io.dnlwjtud.blog.members.mapper;

import io.dnlwjtud.blog.members.dto.MemberDescription;
import io.dnlwjtud.blog.members.dto.MemberDetails;
import io.dnlwjtud.blog.members.entity.Member;

public class MemberMapper {

    public static MemberDescription toDescription(Member member) {
        return new MemberDescription(
                member.getUsername(),
                member.getEmail(),
                member.getRole(),
                member.getCreatedAt()
        );
    }

    public static MemberDetails toDetails(Member member) {
        return new MemberDetails(
                member.getUsername(),
                member.getPassword(),
                member.getRole()
        );
    }

}

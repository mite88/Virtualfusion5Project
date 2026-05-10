package mapper.s4;

import domain.Member;
import domain.MemberDto;

import java.util.List;
import java.util.Optional;

public interface MemberMapper {

    List<MemberDto> findAll();

    Optional<Member> findById(Long id);

}

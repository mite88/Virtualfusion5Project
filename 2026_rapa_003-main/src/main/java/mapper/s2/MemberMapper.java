package mapper.s2;

import domain.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface MemberMapper {

    // CRUD
    List<Member> findAll(); // 다건조회

    Optional<Member> findById(Long id);// 단건조회

    Optional<Member> findByEmail(String email);

    int save(Member member);

    int updateBalance(@Param("id") Long id, @Param("amount") int amount);

    int deleteById(Long id);

    // 총 회원수
    int count();

}

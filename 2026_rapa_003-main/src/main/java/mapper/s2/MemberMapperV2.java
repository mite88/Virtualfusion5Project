package mapper.s2;

import domain.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

public interface MemberMapperV2 {

    @Select("""
    SELECT
        id,
        name,
        password,
        email,
        balance
    FROM
        member
    """)
    List<Member> findAll(); // 다건조회

    @Select("""
    SELECT
        id,
        name,
        password,
        email,
        balance
    FROM
        member
    WHERE
        id = #{id}
    """)
    Optional<Member> findById(Long id);// 단건조회

    @Select("""
    SELECT
        id,
        name,
        password,
        email,
        balance
    FROM
        member
    WHERE
        email = #{email}
    """)
    Optional<Member> findByEmail(String email);

    @Insert("""
    INSERT INTO member
            (name, password, email, balance )
    VALUES
        ( #{name}, #{password}, #{email}, #{balance} )
    """)
    int save(Member member);

    @Update("""
    UPDATE member
    SET
        balance = #{amount}
    WHERE
        id = #{id}
    """)
    int updateBalance(@Param("id") Long id, @Param("amount") int amount);

    @Delete("""
    DELETE FROM member
    WHERE
        id = #{id}
    """)
    int deleteById(Long id);

    @Select("""
    SELECT
        COUNT(*)
    FROM
        member
    """)
    int count();


}

import domain.Member;
import mapper.s2.MemberMapperV2;
import org.apache.ibatis.session.SqlSession;
import util.HikariMyBatisConnector;
import util.MyBatisSessionConnector;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MemberCrud {

    static void main() throws Exception {

        MyBatisSessionConnector connector = new HikariMyBatisConnector(
                "mapper.s2",
                "domain"
        );

        try (SqlSession conn = connector.openSession(true)) {

            MemberMapperV2 memberMapper = conn.getMapper(MemberMapperV2.class);

            Member member1 = new Member(
                    null, "user_" + genRandomNumber(), "1234", "user%d@email.com".formatted(genRandomNumber()), genRandomNumber()
            );

            Member member2 = new Member(
                    null, "user_" + genRandomNumber(), "1234", "user%d@email.com".formatted(genRandomNumber()), genRandomNumber()
            );

            memberMapper.save(member1);
            memberMapper.save(member2);

            System.out.println("member1 id = " + member1.getId());
            System.out.println("member2 id = " + member2.getId());

        }

        try (SqlSession session = connector.openSession()) {

            MemberMapperV2 memberMapper = session.getMapper(MemberMapperV2.class);

            List<Member> members = memberMapper.findAll();

            System.out.println("회원 목록 =========");

            for (Member member : members) {
                System.out.println("회원 번호 : " + member.getId());
                System.out.println("회원 이름 : " + member.getName());
                System.out.println("회원 이메일 : " + member.getEmail());
                System.out.println("회원 잔고 : " + member.getBalance());
            }


        }

        try (SqlSession session = connector.openSession(true) ) {

            MemberMapperV2 memberMapper = session.getMapper(MemberMapperV2.class);

            Long targetMemberId = 1L;
            int targetAmount = 10;

            memberMapper.updateBalance(targetMemberId, targetAmount);

        }

        try (SqlSession session = connector.openSession() ) {

            MemberMapperV2 memberMapper = session.getMapper(MemberMapperV2.class);

            Long targetMemberId = 1L;

            Optional<Member> memberOptional = memberMapper.findById(targetMemberId);
            Member findMember = memberOptional.orElseThrow(() -> new NoSuchElementException("해당 회원은 존재하지 않습니다."));

            System.out.println("수정된 회원 ===========");
            System.out.println("회원 번호 : " + findMember.getId());
            System.out.println("회원 이름 : " + findMember.getName());
            System.out.println("회원 이메일 : " + findMember.getEmail());
            System.out.println("회원 잔고 : " + findMember.getBalance());


        }


        try (SqlSession session = connector.openSession(true)) {

            MemberMapperV2 memberMapper = session.getMapper(MemberMapperV2.class);
            
            Long targetMemberId = 2L;

            int affectedRow = memberMapper.deleteById(targetMemberId);

            System.out.println("affectedRow = " + affectedRow);

        }


        try (SqlSession session = connector.openSession() ) {

            MemberMapperV2 memberMapper = session.getMapper(MemberMapperV2.class);

            Long targetMemberId = 2L;

            Optional<Member> memberOptional = memberMapper.findById(targetMemberId);
            memberOptional.orElseThrow(() -> new NoSuchElementException("해당 회원은 존재하지 않습니다."));

        } catch ( NoSuchElementException e ) {
            System.out.println("!!! 예외 발생");
            System.out.println(e.getMessage());
        }

    }


    private static int genRandomNumber() {
        return (int) (Math.random() * 1000000);
    }

}

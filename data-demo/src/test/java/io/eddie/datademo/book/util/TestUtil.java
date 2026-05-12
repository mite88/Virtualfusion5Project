package io.eddie.datademo.book.util;

import io.eddie.datademo.book.entity.Book;
import io.eddie.datademo.book.entity.Member;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * packageName    : io.eddie.datademo.book.util
 * fileName       : TestUtil
 * author         : Admin
 * date           : 26. 5. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 12.        Admin       최초 생성
 */
public class TestUtil {

    /*
     * 1. 난수 생성: 중복 방지를 위해 UUID나 Random을 활용해 고유한 이메일, ISBN 생성
     * 2. 빌더 활용: 엔티티에 선언한 @Builder를 사용하여 가독성 있는 객체 생성
     * 3. 리스트 생성: IntStream을 활용하여 대량의 테스트 데이터를 한 번에 생성
    * */

    // 회원생성
    public static Member generateMember() {
        String randomStr = UUID.randomUUID().toString().substring(0, 8);
        return Member.builder()
                .name("user_" + randomStr)
                .email(randomStr + "@test.com")
                .build();
    }

    // 도서 생성
    public static Book generateBook() {
        String isbn = UUID.randomUUID().toString().substring(0, 13);
        return Book.builder()
                .title("Book_" + isbn)
                .isbn(isbn)
                .author("Author_" + isbn.substring(0, 5))
                .build();
    }

    // 리스트 생성 (대량 테스트용)
   /* public static List<Book> generateBookList(int amount) {
        return IntStream.range(0, amount)
                .mapToObj(_ -> generateBook())
                .toList();
    }*/

}

package io.eddie.datademo.domain.orders.exceptions;

/**
 * packageName    : io.eddie.datademo.domain.orders.exceptions
 * fileName       : CouldNotFindSuchOrderException
 * author         : Admin
 * date           : 26. 5. 13.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 13.        Admin       최초 생성
 */
//커스텀 exception 만들기
public class CouldNotFindSuchOrderException extends RuntimeException{

    private static final String MESSAGE = "주문서를 찾을 수 없습니다.";

    public CouldNotFindSuchOrderException() {
        super(MESSAGE);
    }

    public CouldNotFindSuchOrderException(Throwable cause) {
        super(MESSAGE, cause);
    }

    public CouldNotFindSuchOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}

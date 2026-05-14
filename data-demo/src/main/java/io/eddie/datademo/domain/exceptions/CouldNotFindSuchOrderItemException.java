package io.eddie.datademo.domain.exceptions;

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
public class CouldNotFindSuchOrderItemException extends RuntimeException{

    private static final String MESSAGE = "해당주문은 일치하지 않습니다.";

    public CouldNotFindSuchOrderItemException() {
        super(MESSAGE);
    }

    public CouldNotFindSuchOrderItemException(Throwable cause) {
        super(MESSAGE, cause);
    }

    public CouldNotFindSuchOrderItemException(String message, Throwable cause) {
        super(message, cause);
    }
}

package mapper.s4;

import domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderMapper {

    List<Order> findByMemberId(Long memberId);
    List<Order> findAllInlineMapping();

    List<Order> findAllWithMemberNestedSelect();

}

package mapper.s1;

import org.apache.ibatis.annotations.Select;

public interface TestMapperV3 {

    @Select("SELECT 1")
    Integer test();

}

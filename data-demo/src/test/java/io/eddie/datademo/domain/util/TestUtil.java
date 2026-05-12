package io.eddie.datademo.domain.util;

import io.eddie.datademo.domain.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * packageName    : io.eddie.datademo.dao.hibern
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

    private static final String ITEM_PREFIX = "ITEM_";

    public static String genRandomItemCode(){
        return ITEM_PREFIX +genNumStr();
    }

    public static String genNumStr(){

        int num = (int)(int) (Math.random() * 100_000);
        return Integer.toString(num);
    }


    //ctrl+rit + m 으로 매서드따로빼기
    public static Integer genRandomPrice(){
        int price = (int) (Math.random() * 100_000);

        return price * 1_000;
    }

    public static Items generateItems() {

        //Items item =  new Items(n, c, genRandomPrice());

        //@Items에  @Builder를 사용했기때문에 아래외 같이 가능함
        Items item = Items.builder()
                .name(genRandomItemCode())
                .code(genRandomItemCode())
                .price(genRandomPrice())
                .build();
        return item;
    }

    public static List<Items> generateItemList(int amount){
        return IntStream.range(0, amount).mapToObj(_ -> generateItems()).toList();
    }

}

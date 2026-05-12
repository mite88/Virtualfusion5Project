package io.eddie.aopdemo.proxy.virtual;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName    : io.eddie.aopdemo.proxy.virtual
 * fileName       : ProteinDistributor
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :가상 프록시 실습
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */
@Slf4j
public class ProteinDistributor implements SupplementProvider{

    private SupplementProvider provider;
    private String protein; //프로틴

    public ProteinDistributor(SupplementProvider provider) {
        this.provider = provider;
    }

    @Override
    public String provide() throws Exception {

        //String protein = provider.provide();
        //무거운걸 반환한다고 생각하고....(파싱, 통신, 이미지 등)
        log.info("유통업체가 주문을 받음");

        if(protein == null){
            log.info("재고없음");
            protein = provider.provide();
        }

        log.info("재고 있음. 유통함");

        return this.protein;
    }
}

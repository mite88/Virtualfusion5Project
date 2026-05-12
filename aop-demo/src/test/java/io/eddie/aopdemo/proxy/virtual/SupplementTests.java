package io.eddie.aopdemo.proxy.virtual;

import org.junit.jupiter.api.Test;

/**
 * packageName    : io.eddie.aopdemo.proxy.virtual
 * fileName       : SupplementTests
 * author         : Admin
 * date           : 26. 5. 11.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 11.        Admin       최초 생성
 */
public class SupplementTests {

    @Test
    void test1() throws Exception {
        ProteinProvider provider = new ProteinProvider();//제공업체
        ProteinDistributor distributor = new ProteinDistributor(provider);//유통업체

        distributor.provide();
        distributor.provide();
        distributor.provide();

    }
}

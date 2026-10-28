package io.github.zhengye1.flashsale;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class FlashSaleBackendApplicationTests {

    @Test
    void contextLoads() {
    }

}

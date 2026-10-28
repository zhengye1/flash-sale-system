package io.github.zhengye1.flashsale;

import org.springframework.boot.SpringApplication;

public class TestFlashSaleBackendApplication {

    public static void main(String[] args) {
        SpringApplication.from(FlashSaleBackendApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }

}

package io.github.zhengye1.flashsale;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class OpenInViewTests {
    @Autowired
    ApplicationContext context;

    @Test
    void openInViewIsDisabled() {
        assertThat(context.getBeanNamesForType(OpenEntityManagerInViewInterceptor.class))
                .isEmpty();
    }
}

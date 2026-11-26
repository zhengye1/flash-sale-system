package io.github.zhengye1.flashsale;

import static org.assertj.core.api.Assertions.assertThat;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.MigrationInfoService;
import org.flywaydb.core.api.MigrationState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class DatabaseMigrationTests {

    @Autowired
    Flyway flyway;

    @Test
    void allMigrationsApplySuccessfully() {
        MigrationInfoService info = flyway.info();
        assertThat(info.pending()).isEmpty();
        assertThat(info.applied()).extracting(MigrationInfo::getState).containsOnly(MigrationState.SUCCESS);
    }
}

package com.bh.rms.config;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Tag("prod")
@ActiveProfiles({"prod"})
@SpringBootTest
public class AbstractProdIntegrationTest {
}

package com.bh.rms.config;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Tag("local")
@ActiveProfiles({"local"})
@SpringBootTest
public class AbstractLocalIntegrationTest {
}

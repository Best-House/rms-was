package com.bh.rms.integration.document.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public abstract class AbstractDocumentTest {
    @Autowired
    private MockMvc mockMvc;

    public MockMvc getMockMvc() {
        return mockMvc;
    }

}

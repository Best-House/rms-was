package com.bh.rms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

@ActiveProfiles({"local"})
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
public class AbstractRestDocTest {
    @Autowired
    protected MockMvc mockMvc;

    protected OperationRequestPreprocessor getRequestPreprocessor() {
        return preprocessRequest(
                modifyUris() // (1)
                        .scheme("https")
                        .host("hojun.asuscomm.com")
                        .removePort(),
                prettyPrint()); // (2)
    }

    protected OperationResponsePreprocessor getResponsePreprocessor() {
        return preprocessResponse(prettyPrint()); // (3)
    }
}

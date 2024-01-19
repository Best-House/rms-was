package com.bh.rms.integration.document.config;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

public class DocumentProcessor {
    public static OperationRequestPreprocessor getRequestPreprocessor() {
        return preprocessRequest(
                modifyUris() // (1)
                        .scheme("https")
                        .host("hojun.asuscomm.com")
                        .removePort(),
                prettyPrint()); // (2)
    }

    public static OperationResponsePreprocessor getResponsePreprocessor() {
        return preprocessResponse(prettyPrint()); // (3)
    }
}

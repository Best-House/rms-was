package com.bh.rms.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class MaterialControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createMaterials() throws Exception {
        this.mockMvc.perform(
                        RestDocumentationRequestBuilders.post("/api/materials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"water\",\n" +
                                "    \"price\": 2000,\n" +
                                "    \"amount\": 1000\n" +
                                "}")
                )
                .andExpect(status().isOk())
                .andDo(
                        document("materials-create",
                                requestFields(
                                        fieldWithPath("name").description("name"),
                                        fieldWithPath("price").description("price").optional(),
                                        fieldWithPath("amount").description("amount").optional()
                                ),
                                responseFields( // response 필드 정보 입력
                                        fieldWithPath("id").description("ID"),
                                        fieldWithPath("name").description("name"),
                                        fieldWithPath("priceInfo").description("optional").optional(),
                                        fieldWithPath("priceInfo.price").description("price >= 0"),
                                        fieldWithPath("priceInfo.amount").description("amount > 0")
                                )
                        )
                );

    }
}
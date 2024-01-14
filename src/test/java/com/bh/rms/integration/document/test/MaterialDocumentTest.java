package com.bh.rms.integration.document.test;

import com.bh.rms.integration.document.fixture.MaterialFixtureGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static com.bh.rms.integration.document.config.DocumentProcessor.getRequestPreprocessor;
import static com.bh.rms.integration.document.config.DocumentProcessor.getResponsePreprocessor;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MaterialDocumentTest extends AbstractDocumentTest {
    @Autowired
    MaterialFixtureGenerator materialFixtureGenerator;

    @Test
    public void createMaterial() throws Exception {
        ResultActions resultActions = getMockMvc().perform(
                        post("/api/materials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(makeJsonString(materialFixtureGenerator.getMaterialCreateRequest()))
                );

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andDo(log())
                .andDo(
                        document("materials-create",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                requestFields(
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("name of material").attributes(key("constraint").value("not empty")),
                                        fieldWithPath("defaultUnitPrice").type(JsonFieldType.NUMBER).description("default unit price of material").optional().attributes(key("constraint").value(">= 0"))
                                ),
                                responseFields( // response 필드 정보 입력
                                        fieldWithPath("id").type(JsonFieldType.STRING).description("identifier of material")
                                )
                        )
                );

        materialFixtureGenerator.cleanUp();
    }

    @Test
    public void updateMaterial() throws Exception {
        String id = materialFixtureGenerator.createMaterial();

        ResultActions resultActions = getMockMvc().perform(
                        put("/api/materials/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(makeJsonString(materialFixtureGenerator.getMaterialCreateRequest()))
                );

        resultActions
                .andExpect(status().isOk())
                .andDo(log())
                .andDo(
                        document("materials-update",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                pathParameters(parameterWithName("id").description("id of material")),
                                requestFields(
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("name of material").attributes(key("constraint").value("not empty")),
                                        fieldWithPath("defaultUnitPrice").type(JsonFieldType.NUMBER).description("default unit price of material").optional().attributes(key("constraint").value(">= 0"))
                                )
                        )
                );
        materialFixtureGenerator.cleanUp();
    }

    @Test
    public void deleteMaterial() throws Exception {
        String id = materialFixtureGenerator.createMaterial();

        ResultActions resultActions = getMockMvc().perform(
                        delete("/api/materials/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                );

        resultActions
                .andExpect(status().isOk())
                .andDo(log())
                .andDo(
                        document("materials-delete",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                pathParameters(parameterWithName("id").description("id of material"))
                        )
                );
        materialFixtureGenerator.cleanUp();
    }

    @Test
    public void getMaterial() throws Exception {
        String id = materialFixtureGenerator.createMaterial();

        ResultActions resultActions = getMockMvc().perform(
                        get("/api/materials/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                );

        resultActions
                .andExpect(status().isOk())
                .andDo(log())
                .andDo(
                        document("materials-get",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                pathParameters(parameterWithName("id").description("id of material")),
                                responseFields( // response 필드 정보 입력
                                        fieldWithPath("id").type(JsonFieldType.STRING).description("identifier of material"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("name of material"),
                                        fieldWithPath("defaultUnitPrice").type(JsonFieldType.NUMBER).description("defaultUnitPrice of material")
                                )
                        )
                );
        materialFixtureGenerator.cleanUp();
    }

    @Test
    public void getAllMaterial() throws Exception {
        materialFixtureGenerator.createMaterial();
        materialFixtureGenerator.createMaterial();

        ResultActions resultActions = getMockMvc().perform(
                        get("/api/materials")
                                .contentType(MediaType.APPLICATION_JSON)
                );

        resultActions
                .andExpect(status().isOk())
                .andDo(log())
                .andDo(
                        document("materials-getAll",
                                getRequestPreprocessor(), getResponsePreprocessor()
                                )
                );

        materialFixtureGenerator.cleanUp();
    }
}
package com.bh.rms.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MaterialControllerTest extends DocumentTest{
    @Autowired
    private MaterialController materialController;

    private final String materialName = "name";
    private final double materialDefaultUnitPrice = 3.0;

    public String createMaterialByController() {
        MaterialController.MaterialCreateRequest request = new MaterialController.MaterialCreateRequest();
        request.setName(materialName);
        request.setDefaultUnitPrice(materialDefaultUnitPrice);
        MaterialController.MaterialCreateResponse response = materialController.create(request);
        return response.getId();
    }

    public void deleteMaterialByController(String id) {
        materialController.delete(id);
    }

    @Test
    public void createMaterial() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(
                        post("/api/materials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "water",
                                    "defaultUnitPrice": 2
                                }""")
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
                )
                .andDo(result -> {
                    String id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
                    deleteMaterialByController(id);
                });
    }

    @Test
    public void updateMaterial() throws Exception {
        String id = createMaterialByController();

        ResultActions resultActions = this.mockMvc.perform(
                        put("/api/materials/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                {
                                    "name": "water2",
                                    "defaultUnitPrice": 3
                                }""")
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
        deleteMaterialByController(id);
    }

    @Test
    public void deleteMaterial() throws Exception {
        String id = createMaterialByController();

        ResultActions resultActions = this.mockMvc.perform(
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
    }

    @Test
    public void getMaterial() throws Exception {
        String id = createMaterialByController();

        ResultActions resultActions = this.mockMvc.perform(
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
        deleteMaterialByController(id);
    }

    @Test
    public void getAllMaterial() throws Exception {
        String id1 = createMaterialByController();
        String id2 = createMaterialByController();

        ResultActions resultActions = this.mockMvc.perform(
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

        deleteMaterialByController(id1);
        deleteMaterialByController(id2);
    }
}
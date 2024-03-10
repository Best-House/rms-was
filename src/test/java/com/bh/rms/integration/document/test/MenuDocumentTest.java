package com.bh.rms.integration.document.test;

import com.bh.rms.integration.document.fixture.MenuFixtureGenerator;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static com.bh.rms.integration.document.config.DocumentProcessor.getRequestPreprocessor;
import static com.bh.rms.integration.document.config.DocumentProcessor.getResponsePreprocessor;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuDocumentTest extends AbstractDocumentTest {

    @Autowired
    MenuFixtureGenerator menuFixtureGenerator;

    @AfterEach
    public void afterEach() {
        menuFixtureGenerator.cleanUp();
    }

    @Test
    void createMenu() throws Exception {
        ResultActions resultActions = getMockMvc().perform(
                post("/api/menus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(makeJsonString(menuFixtureGenerator.getMenuCreateRequest()))
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andDo(log())
                .andDo(
                        document("menu-create",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                requestFields(
                                        fieldWithPath("name").type(JsonFieldType.STRING)
                                                .description("name of menu").optional().attributes(key("constraint").value("can not be empty.")),
                                        fieldWithPath("price").type(JsonFieldType.NUMBER)
                                                .description("price of menu").optional().attributes(key("constraint").value("price >= 0.")),
                                        fieldWithPath("recipeId").type(JsonFieldType.STRING)
                                                .description("recipeId of menu").optional().attributes(key("constraint").value("can not be empty."))
                                ),
                                responseFields(
                                        fieldWithPath("id").type(JsonFieldType.STRING)
                                                .description("identifier of menu")
                                )
                        )
                )
                .andDo(result -> {
                    String id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
                    menuFixtureGenerator.deleteMenu(id);
                });
    }

    @Test
    void updateMenu() throws Exception {
        String id = menuFixtureGenerator.createMenu();

        ResultActions resultActions = getMockMvc().perform(
                put("/api/menus/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(makeJsonString(menuFixtureGenerator.getMenuUpdateRequest()))
        );

        resultActions
                .andExpect(status().isOk())
                .andDo(log())
                .andDo(
                        document("menu-update",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                pathParameters(parameterWithName("id").description("id of menu")),
                                requestFields(
                                        fieldWithPath("name").type(JsonFieldType.STRING)
                                                .description("name of menu").optional().attributes(key("constraint").value("can not be empty.")),
                                        fieldWithPath("price").type(JsonFieldType.NUMBER)
                                                .description("price of menu").optional().attributes(key("constraint").value("price >= 0.")),
                                        fieldWithPath("recipeId").type(JsonFieldType.STRING)
                                                .description("recipeId of menu").optional().attributes(key("constraint").value("can not be empty."))
                                )
                        )
                );
    }

    @Test
    void deleteMenu() throws Exception {
        String id = menuFixtureGenerator.createMenu();

        ResultActions resultActions = getMockMvc().perform(
                delete("/api/menus/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andExpect(status().isOk())
                .andDo(log())
                .andDo(
                        document("menu-delete",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                pathParameters(parameterWithName("id").description("id of menu"))
                        )
                );
    }

    @Test
    void getMenu() throws Exception {
        String id = menuFixtureGenerator.createMenu();

        ResultActions resultActions = getMockMvc().perform(
                get("/api/menus/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.recipeId").exists())
                .andDo(log())
                .andDo(
                        document("menu-get",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                pathParameters(parameterWithName("id").description("id of menu")),
                                responseFields(
                                        fieldWithPath("id").type(JsonFieldType.STRING).description("identifier of menu"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("name of menu"),
                                        fieldWithPath("price").type(JsonFieldType.NUMBER).description("price of menu"),
                                        fieldWithPath("recipeId").type(JsonFieldType.STRING).description("recipeId of menu")
                                )
                        )
                );
    }

    @Test
    void getMenus() throws Exception {
        menuFixtureGenerator.createMenu();
        menuFixtureGenerator.createMenu();

        ResultActions resultActions = getMockMvc().perform(
                get("/api/menus")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[1].id").exists())
                .andExpect(jsonPath("$.[2].id").doesNotExist())
                .andDo(log())
                .andDo(
                        document("menu-getAll",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                responseFields(
                                        fieldWithPath("[]").type(JsonFieldType.ARRAY).description("all of menu"),
                                        fieldWithPath("[].id").type(JsonFieldType.STRING).description("identifier of menu"),
                                        fieldWithPath("[].name").type(JsonFieldType.STRING).description("name of menu"),
                                        fieldWithPath("[].price").type(JsonFieldType.NUMBER).description("price of menu"),
                                        fieldWithPath("[].recipeId").type(JsonFieldType.STRING).description("recipeId of menu")
                                )
                        )
                );
    }
}

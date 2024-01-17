package com.bh.rms.integration.document.test;

import com.bh.rms.integration.document.fixture.RecipeFixtureGenerator;
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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RecipeDocumentTest extends AbstractDocumentTest {
    @Autowired
    private RecipeFixtureGenerator recipeFixtureGenerator;

    @AfterEach
    public void afterEach() {
        recipeFixtureGenerator.cleanUp();
    }

    @Test
    void createRecipe() throws Exception {
        ResultActions resultActions = getMockMvc().perform(
                post("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(makeJsonString(recipeFixtureGenerator.getRecipeCreateRequest()))
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andDo(log())
                .andDo(
                        document("recipes-create",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                requestFields(
                                        fieldWithPath("name").type(JsonFieldType.STRING)
                                                .description("name of recipe").attributes(key("constraint").value("not empty")),
                                        fieldWithPath("ingredients").type(JsonFieldType.ARRAY)
                                                .description("ingredients of recipe").optional().attributes(key("constraint").value("can be empty.")),
                                        fieldWithPath("ingredients[].materialId").type(JsonFieldType.STRING)
                                                .description("materialId of ingredient").attributes(key("constraint").value("material exist")),
                                        fieldWithPath("ingredients[].amount").type(JsonFieldType.NUMBER)
                                                .description("amount of ingredient").attributes(key("constraint").value("amount >= 0."))
                                ),
                                responseFields(
                                        fieldWithPath("id").type(JsonFieldType.STRING)
                                                .description("identifier of recipe")
                                )
                        )
                )
                .andDo(result -> {
                    String id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
                    recipeFixtureGenerator.deleteRecipe(id);
                });
    }

    @Test
    void updateRecipe() throws Exception {
        String id = recipeFixtureGenerator.createRecipe();

        ResultActions resultActions = getMockMvc().perform(
                put("/api/recipes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(makeJsonString(recipeFixtureGenerator.getRecipeCreateRequest()))
        );

        resultActions
                .andExpect(status().isOk())
                .andDo(log())
                .andDo(
                        document("recipes-update",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                pathParameters(parameterWithName("id").description("id of recipe")),
                                requestFields(
                                        fieldWithPath("name").type(JsonFieldType.STRING)
                                                .description("name of recipe").attributes(key("constraint").value("not empty")),
                                        fieldWithPath("ingredients").type(JsonFieldType.ARRAY)
                                                .description("ingredients of recipe").optional().attributes(key("constraint").value("can be empty.")),
                                        fieldWithPath("ingredients[].materialId").type(JsonFieldType.STRING)
                                                .description("materialId of ingredient").attributes(key("constraint").value("material exist")),
                                        fieldWithPath("ingredients[].amount").type(JsonFieldType.NUMBER)
                                                .description("amount of ingredient").attributes(key("constraint").value("amount >= 0."))
                                )
                        )
                );
    }

    @Test
    void deleteRecipe() throws Exception {
        String id = recipeFixtureGenerator.createRecipe();

        ResultActions resultActions = getMockMvc().perform(
                delete("/api/recipes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andExpect(status().isOk())
                .andDo(log())
                .andDo(
                        document("recipes-delete",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                pathParameters(parameterWithName("id").description("id of recipe"))
                        )
                );
    }

    @Test
    void getRecipe() throws Exception {
        String id = recipeFixtureGenerator.createRecipe();

        ResultActions resultActions = getMockMvc().perform(
                get("/api/recipes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.ingredients").isArray())
                .andExpect(jsonPath("$.ingredients[0].materialId").exists())
                .andExpect(jsonPath("$.ingredients[1].materialId").exists())
                .andExpect(jsonPath("$.ingredients[2]").doesNotExist())
                .andDo(
                        document("recipes-get",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                pathParameters(parameterWithName("id").description("id of recipe")),
                                responseFields(
                                        fieldWithPath("id").type(JsonFieldType.STRING).description("identifier of recipe"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("name of recipe"),
                                        fieldWithPath("ingredients").type(JsonFieldType.ARRAY).description("ingredients of recipe"),
                                        fieldWithPath("ingredients[].materialId").type(JsonFieldType.STRING).description("materialId of ingredient"),
                                        fieldWithPath("ingredients[].amount").type(JsonFieldType.NUMBER).description("amount of ingredient")
                                )
                        )
                );
    }

    @Test
    void getAllRecipe() throws Exception {
        recipeFixtureGenerator.createRecipe();
        recipeFixtureGenerator.createRecipe();

        ResultActions resultActions = getMockMvc().perform(
                get("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[1].id").exists())
                .andExpect(jsonPath("$[2].id").doesNotExist())
                .andDo(
                        document("recipes-getAll",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                responseFields(
                                        fieldWithPath("[]").type(JsonFieldType.ARRAY).description("all of recipes"),
                                        fieldWithPath("[].id").type(JsonFieldType.STRING).description("id of recipe"),
                                        fieldWithPath("[].name").type(JsonFieldType.STRING).description("name of recipe"),
                                        fieldWithPath("[].ingredients").type(JsonFieldType.ARRAY).description("ingredients of recipe"),
                                        fieldWithPath("[].ingredients[].materialId").type(JsonFieldType.STRING).description("materialId of ingredient"),
                                        fieldWithPath("[].ingredients[].amount").type(JsonFieldType.NUMBER).description("amount of ingredient")
                                )
                        )
                );
    }

    @Test
    void getCost() throws Exception {
        String recipeId = recipeFixtureGenerator.createRecipe();

        ResultActions resultActions = getMockMvc().perform(
                get("/api/recipes/{id}/cost", recipeId)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cost").isNumber())
                .andExpect(jsonPath("$.unknownPriceMaterialIds").isArray())
                .andDo(
                        document("recipes-getCost",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                pathParameters(parameterWithName("id").description("id of recipe")),
                                responseFields(
                                        fieldWithPath("cost").type(JsonFieldType.NUMBER).description("cost of recipe"),
                                        fieldWithPath("unknownPriceMaterialIds").type(JsonFieldType.ARRAY).description("materialIds with no price info")
                                )
                        )
                );
    }
}
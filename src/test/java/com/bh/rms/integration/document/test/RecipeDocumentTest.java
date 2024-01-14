package com.bh.rms.integration.document.test;

import com.bh.rms.integration.document.fixture.RecipeFixtureGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static com.bh.rms.integration.document.config.DocumentProcessor.getRequestPreprocessor;
import static com.bh.rms.integration.document.config.DocumentProcessor.getResponsePreprocessor;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RecipeDocumentTest extends AbstractDocumentTest {
    @Autowired
    private RecipeFixtureGenerator recipeFixtureGenerator;

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
                );
        recipeFixtureGenerator.cleanUp();
    }
}
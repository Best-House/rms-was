package com.bh.rms.integration.document.test;

import com.bh.rms.integration.document.fixture.PurchaseFixtureGenerator;
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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PurchaseDocumentTest extends AbstractDocumentTest {

    @Autowired
    PurchaseFixtureGenerator purchaseFixtureGenerator;

    @AfterEach
    public void afterEach() {
        purchaseFixtureGenerator.cleanUp();
    }

    @Test
    void createPurchase() throws Exception {
        ResultActions resultActions = getMockMvc().perform(
                post("/api/purchases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(makeJsonString(purchaseFixtureGenerator.getPurchaseCreateRequest()))
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andDo(log())
                .andDo(
                        document("purchases-create",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                requestFields(
                                        fieldWithPath("purchaseItems").type(JsonFieldType.ARRAY)
                                                .description("purchaseItems of purchase").optional().attributes(key("constraint").value("can not be empty.")),
                                        fieldWithPath("purchaseItems[].materialId").type(JsonFieldType.STRING)
                                                .description("materialId of purchaseItem").attributes(key("constraint").value("material exist")),
                                        fieldWithPath("purchaseItems[].price").type(JsonFieldType.NUMBER)
                                                .description("price of purchaseItem").attributes(key("constraint").value("price >= 0.")),
                                        fieldWithPath("purchaseItems[].amount").type(JsonFieldType.NUMBER)
                                                .description("amount of purchaseItem").attributes(key("constraint").value("amount > 0."))
                                ),
                                responseFields(
                                        fieldWithPath("id").type(JsonFieldType.STRING)
                                                .description("identifier of purchase")
                                )
                        )
                )
                .andDo(result -> {
                    String id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
                    purchaseFixtureGenerator.deletePurchase(id);
                });
    }

    @Test
    void updatePurchase() throws Exception {
        String id = purchaseFixtureGenerator.createPurchase();

        ResultActions resultActions = getMockMvc().perform(
                put("/api/purchases/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(makeJsonString(purchaseFixtureGenerator.getPurchaseUpdateRequest())));

        resultActions
                .andExpect(status().isOk())
                .andDo(log())
                .andDo(
                        document("purchases-update",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                pathParameters(parameterWithName("id").description("id of purchase")),
                                requestFields(
                                        fieldWithPath("purchaseItems").type(JsonFieldType.ARRAY)
                                                .description("purchaseItems of purchase").optional().attributes(key("constraint").value("can not be empty.")),
                                        fieldWithPath("purchaseItems[].materialId").type(JsonFieldType.STRING)
                                                .description("materialId of purchaseItem").attributes(key("constraint").value("material exist")),
                                        fieldWithPath("purchaseItems[].price").type(JsonFieldType.NUMBER)
                                                .description("price of purchaseItem").attributes(key("constraint").value("price >= 0.")),
                                        fieldWithPath("purchaseItems[].amount").type(JsonFieldType.NUMBER)
                                                .description("amount of purchaseItem").attributes(key("constraint").value("amount > 0.")),
                                        fieldWithPath("purchaseItems[].purchaseDate").type(JsonFieldType.NUMBER)
                                                .description("amount of purchaseItem").attributes(key("constraint").value("purchaseDate >= 0."))
                                ))
                );
    }

    @Test
    void deletePurchase() throws Exception {
        String id = purchaseFixtureGenerator.createPurchase();

        ResultActions resultActions = getMockMvc().perform(
                delete("/api/purchases/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andExpect(status().isOk())
                .andDo(log())
                .andDo(
                        document("purchases-delete",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                pathParameters(parameterWithName("id").description("id of purchase"))
                        )
                );
    }

    @Test
    void getPurchase() throws Exception {
        String id = purchaseFixtureGenerator.createPurchase();

        ResultActions resultActions = getMockMvc().perform(
                get("/api/purchases/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.purchaseItems").isArray())
                .andExpect(jsonPath("$.purchaseItems[0]").exists())
                .andExpect(jsonPath("$.purchaseItems[1]").exists())
                .andExpect(jsonPath("$.purchaseItems[2]").doesNotExist())
                .andExpect(jsonPath("$.purchaseItems[0].materialId").exists())
                .andExpect(jsonPath("$.purchaseItems[0].price").exists())
                .andExpect(jsonPath("$.purchaseItems[0].amount").exists())
                .andExpect(jsonPath("$.purchaseItems[1].materialId").exists())
                .andExpect(jsonPath("$.purchaseItems[1].price").exists())
                .andExpect(jsonPath("$.purchaseItems[1].amount").exists())
                .andDo(log())
                .andDo(
                        document("purchases-get",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                responseFields(
                                        fieldWithPath("id").type(JsonFieldType.STRING).description("id of purchase"),
                                        fieldWithPath("purchaseItems").type(JsonFieldType.ARRAY).description("purchaseItems of purchase"),
                                        fieldWithPath("purchaseItems[].materialId").type(JsonFieldType.STRING).description("materialId of purchaseItem"),
                                        fieldWithPath("purchaseItems[].price").type(JsonFieldType.NUMBER).description("price of purchaseItem"),
                                        fieldWithPath("purchaseItems[].amount").type(JsonFieldType.NUMBER).description("amount of purchaseItem"),
                                        fieldWithPath("purchaseItems[].purchaseDate").type(JsonFieldType.NUMBER).description("amount of purchaseItem")
                                )
                        )
                );
    }

    @Test
    void getAllPurchases() throws Exception {
        purchaseFixtureGenerator.createPurchase();
        purchaseFixtureGenerator.createPurchase();

        ResultActions resultActions = getMockMvc().perform(
                get("/api/purchases")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[1].id").exists())
                .andExpect(jsonPath("$.[2].id").doesNotExist())
                .andDo(log())
                .andDo(
                        document("purchases-getAll",
                                getRequestPreprocessor(), getResponsePreprocessor(),
                                responseFields(
                                        fieldWithPath("[]").type(JsonFieldType.ARRAY).description("all of purchases"),
                                        fieldWithPath("[].id").type(JsonFieldType.STRING).description("id of purchase"),
                                        fieldWithPath("[].purchaseItems").type(JsonFieldType.ARRAY).description("purchaseItems of purchase"),
                                        fieldWithPath("[].purchaseItems[].materialId").type(JsonFieldType.STRING).description("materialId of purchaseItem"),
                                        fieldWithPath("[].purchaseItems[].price").type(JsonFieldType.NUMBER).description("price of purchaseItem"),
                                        fieldWithPath("[].purchaseItems[].amount").type(JsonFieldType.NUMBER).description("amount of purchaseItem"),
                                        fieldWithPath("[].purchaseItems[].purchaseDate").type(JsonFieldType.NUMBER).description("amount of purchaseItem")
                                )
                        )
                );
    }
}

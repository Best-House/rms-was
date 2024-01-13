package com.bh.rms.integration.document.test;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class RecipeDocumentTest extends AbstractDocumentTest {
//    @Autowired
//    private RecipeController recipeController;
//
//    @Test
//    void createRecipe() throws Exception {
//        ResultActions resultActions = getMockMvc().perform(
//                post("/api/recipes")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                                {
//                                     "name": "coffee",
//                                     "ingredients": [
//                                         {
//                                             "materialId": "material_1",
//                                             "amount" : 10
//                                         }
//                                     ]
//                                }""")
//        );
//
//        resultActions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").isNotEmpty())
//                .andDo(log())
//                .andDo(
//                        document("recipe-create",
//                                getRequestPreprocessor(), getResponsePreprocessor(),
//                                requestFields(
//                                        fieldWithPath("name").type(JsonFieldType.STRING)
//                                                .description("name of recipe").attributes(key("constraint").value("not empty")),
//                                        fieldWithPath("ingredients").type(JsonFieldType.ARRAY)
//                                                .description("ingredients of recipe").optional().attributes(key("constraint").value("materialId is required. amount >= 0."))
//                                ),
//                                responseFields(
//                                        fieldWithPath("id").type(JsonFieldType.STRING)
//                                                .description("identifier of recipe")
//                                )
//                        )
//                )
//                .andDo(result -> {
//                    String id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
////                    deleteMaterialByController(id);
//                });
//    }
}
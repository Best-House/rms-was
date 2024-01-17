package com.bh.rms.domain.aggregate.recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeFactory {
    public static RecipeFactoryForCreate forCreate() {
        return new RecipeFactoryForCreate();
    }

    public static RecipeFactoryForUpdate forUpdate() {
        return new RecipeFactoryForUpdate();
    }

    public static class RecipeFactoryForCreate{
        private String name;
        private List<Ingredient> ingredients;

        public RecipeFactoryForCreate() {
            ingredients = new ArrayList<>();
        }

        public RecipeFactoryForCreate setName(String name) {
            this.name = name;
            return this;
        }

        public RecipeFactoryForCreate addIngredients(String materialId, double amount) {
            Ingredient ingredient = new Ingredient(materialId, amount);
            ingredients.add(ingredient);
            return this;
        }

        public Recipe build() {
            Recipe recipe = new Recipe();
            recipe.setName(name);
            recipe.setIngredients(ingredients);
            return recipe;
        }
    }

    public static class RecipeFactoryForUpdate{
        private String id;
        private String name;
        private List<Ingredient> ingredients;

        public RecipeFactoryForUpdate() {
            ingredients = new ArrayList<>();
        }

        public RecipeFactoryForUpdate setId(String id) {
            this.id = id;
            return this;
        }

        public RecipeFactoryForUpdate setName(String name) {
            this.name = name;
            return this;
        }

        public RecipeFactoryForUpdate addIngredients(String materialId, double amount) {
            Ingredient ingredient = new Ingredient(materialId, amount);
            ingredients.add(ingredient);
            return this;
        }

        public Recipe build() {
            Recipe recipe = new Recipe();
            recipe.setId(id);
            recipe.setName(name);
            recipe.setIngredients(ingredients);
            return recipe;
        }
    }
}

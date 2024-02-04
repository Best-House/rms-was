package com.bh.rms.domain.aggregate.menu;

public class MenuFactory {
    public static MenuFactoryForCreate forCreate() {
        return new MenuFactoryForCreate();
    }

    public static MenuFactoryForUpdate forUpdate() {
        return new MenuFactoryForUpdate();
    }

    public static class MenuFactoryForCreate {
        private String name;
        private Integer price;
        private String recipeId;

        public MenuFactoryForCreate setName(String name) {
            this.name = name;
            return this;
        }

        public MenuFactoryForCreate setPrice(Integer price) {
            this.price = price;
            return this;
        }

        public MenuFactoryForCreate setRecipeId(String recipeId) {
            this.recipeId = recipeId;
            return this;
        }

        public Menu build() {
            Menu menu = new Menu();
            menu.setName(name);
            menu.setPrice(price);
            menu.setRecipeId(recipeId);
            return menu;
        }
    }

    public static class MenuFactoryForUpdate {
        private String id;
        private String name;
        private Integer price;
        private String recipeId;

        public MenuFactoryForUpdate setId(String id) {
            this.id = id;
            return this;
        }

        public MenuFactoryForUpdate setName(String name) {
            this.name = name;
            return this;
        }

        public MenuFactoryForUpdate setPrice(Integer price) {
            this.price = price;
            return this;
        }

        public MenuFactoryForUpdate setRecipeId(String recipeId) {
            this.recipeId = recipeId;
            return this;
        }

        public Menu build() {
            Menu menu = new Menu();
            menu.setId(id);
            menu.setName(name);
            menu.setPrice(price);
            menu.setRecipeId(recipeId);
            return menu;
        }
    }
}

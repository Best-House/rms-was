package com.bh.rms.domain.aggregate.material;

public class MaterialFactory {
    public static MaterialFactoryForCreate forCreate() {
        return new MaterialFactoryForCreate();
    }

    public static MaterialFactoryForUpdate forUpdate() {
        return new MaterialFactoryForUpdate();
    }

    public static class MaterialFactoryForCreate{
        private String name;
        private Double defaultUnitPrice;

        public MaterialFactoryForCreate setName(String name) {
            this.name = name;
            return this;
        }

        public MaterialFactoryForCreate setDefaultUnitPrice(Double defaultUnitPrice) {
            this.defaultUnitPrice = defaultUnitPrice;
            return this;
        }

        public Material build() {
            Material material = new Material();
            material.setName(name);
            material.setDefaultUnitPrice(defaultUnitPrice);
            return material;
        }
    }

    public static class MaterialFactoryForUpdate{
        private String id;
        private String name;
        private Double defaultUnitPrice;

        public MaterialFactoryForUpdate setId(String id) {
            this.id = id;
            return this;
        }

        public MaterialFactoryForUpdate setName(String name) {
            this.name = name;
            return this;
        }

        public MaterialFactoryForUpdate setDefaultUnitPrice(Double defaultUnitPrice) {
            this.defaultUnitPrice = defaultUnitPrice;
            return this;
        }

        public Material build() {
            Material material = new Material();
            material.setId(id);
            material.setName(name);
            material.setDefaultUnitPrice(defaultUnitPrice);
            return material;
        }
    }
}

package com.bh.rms.domain.service;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.infra.MaterialRepository;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.infra.RecipeRepository;
import com.bh.rms.domain.service.exception.MaterialMismatchException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final MaterialRepository materialRepository;


    public RecipeService(RecipeRepository recipeRepository, MaterialRepository materialRepository) {
        this.recipeRepository = recipeRepository;
        this.materialRepository = materialRepository;
    }


    public Recipe create(String name, Map<String, Double> ingredients) {
        Recipe recipe = new Recipe(name, ingredients);
        List<String> materialIds = recipe.getContainedMaterialIds();

        List<Material> materials = materialRepository.findByIds(materialIds); // material service 로 추상화하기
        if(materialIds.size() != materials.size()) {
            throw new MaterialMismatchException();
        }
        return recipeRepository.save(recipe);
    }

    public void delete(String recipeId) {
        recipeRepository.delete(recipeId);
    }

    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    public Recipe get(String recipeId) {
        return recipeRepository.findById(recipeId);
    }

    public RecipeCostResult getCost(String recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId);
        List<Material> materials = materialRepository.findByIds(recipe.getContainedMaterialIds());

        return new RecipeCostResult(
                recipe.getCost(getMaterialUnitPriceMap(materials)),
                getUnknownPriceMaterials(materials)
        );
    }

    private Map<String, Double> getMaterialUnitPriceMap(List<Material> materials) {
        return materials.stream()
                .filter(Material::hasPriceInfo)
                .collect(
                        Collectors.toMap(
                                Material::getId,
                                Material::getUnitPrice
                        )
                );
    }

    private List<Material> getUnknownPriceMaterials(List<Material> materials) {
        return materials.stream()
                .filter(material -> !material.hasPriceInfo())
                .collect(Collectors.toList());
    }

    @AllArgsConstructor
    @Data
    public static class RecipeCostResult {
        private double cost;
        private List<Material> unknownPriceMaterials;
    }
}

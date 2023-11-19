package com.bh.rms.domain.compositions.cost;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.infra.MaterialRepository;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.infra.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CostService {
    private final RecipeRepository recipeRepository;
    private final MaterialRepository materialRepository;


    public CostService(RecipeRepository recipeRepository, MaterialRepository materialRepository) {
        this.recipeRepository = recipeRepository;
        this.materialRepository = materialRepository;
    }

    public CostResult getCost(String recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId);
        List<Material> materials = materialRepository.findByIds(recipe.getMaterialIdsOfIngredients());

        return new CostResult(
                recipe.getCost(new CostCalculator(getMaterialUnitPriceMap(materials))),
                getUnknownPriceMaterials(materials)
        );
    }

    private Map<String, Double> getMaterialUnitPriceMap(List<Material> materials) {
        return materials.stream()
                .filter(Material::hasDefaultUnitPrice)
                .collect(
                        Collectors.toMap(
                                Material::getId,
                                Material::getUnitPrice
                        )
                );
    }

    private List<Material> getUnknownPriceMaterials(List<Material> materials) {
        return materials.stream()
                .filter(material -> !material.hasDefaultUnitPrice())
                .collect(Collectors.toList());
    }

    @AllArgsConstructor
    @Data
    public static class CostResult {
        private double cost;
        private List<Material> unknownPriceMaterials;
    }
}

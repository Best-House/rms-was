package com.bh.rms.domain.compositions.cost;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.infra.MaterialRepository;
import com.bh.rms.domain.aggregate.purchase.Purchase;
import com.bh.rms.domain.aggregate.purchase.PurchaseItem;
import com.bh.rms.domain.aggregate.purchase.infra.PurchaseRepository;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.infra.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostService {
    private final RecipeRepository recipeRepository;
    private final MaterialRepository materialRepository;
    private final PurchaseRepository purchaseRepository;


    public CostService(RecipeRepository recipeRepository, MaterialRepository materialRepository,
                       PurchaseRepository purchaseRepository) {
        this.recipeRepository = recipeRepository;
        this.materialRepository = materialRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public CostResult getRecentCost(String recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId);
        List<String> materialIdsOfIngredients = recipe.getMaterialIdsOfIngredients();

        List<Material> materials = materialRepository.findByIds(materialIdsOfIngredients);
        List<PurchaseItem> purchaseItems = purchaseRepository.findRecentPurchaseItemsBy(materialIdsOfIngredients);

        CostCalculator costCalculator = new CostCalculator();
        costCalculator.putDefaultUnitPriceOf(materials);
        costCalculator.putPurchaseUnitPrice(purchaseItems);

        return new CostResult(
                recipe.getCost(costCalculator),
                costCalculator.getUnknownPriceOf(materialIdsOfIngredients)
        );
    }

    @AllArgsConstructor
    @Data
    public static class CostResult {
        private double cost;
        private List<String> unknownPriceMaterialIds;
    }
}

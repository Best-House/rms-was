package com.bh.rms.infra.inmemory;

import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.exception.NotFoundRecipeException;
import com.bh.rms.domain.aggregate.recipe.infra.RecipeRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@Primary
public class InMemoryRecipeRepository implements RecipeRepository {
    private final Map<String, Recipe> recipeMap;
    private final AtomicInteger atomicInteger;

    public InMemoryRecipeRepository() {
        recipeMap = new HashMap<>();
        atomicInteger = new AtomicInteger();
    }

    @Override
    public String create(Recipe recipe) {
        recipe.setId(String.format("recipe%d", atomicInteger.incrementAndGet()));
        recipeMap.put(recipe.getId(), recipe);
        return recipe.getId();
    }

    @Override
    public void update(Recipe recipe) {
        if(!recipeMap.containsKey(recipe.getId())) {
            throw new NotFoundRecipeException();
        }
        recipeMap.put(recipe.getId(), recipe);
    }

    @Override
    public void delete(String recipeId) {
        if(!recipeMap.containsKey(recipeId)) {
            throw new NotFoundRecipeException();
        }
        recipeMap.remove(recipeId);
    }

    @Override
    public Recipe findById(String recipeId) throws NotFoundRecipeException {
        if(!recipeMap.containsKey(recipeId)) {
            throw new NotFoundRecipeException();
        }
        return recipeMap.get(recipeId);
    }

    @Override
    public List<Recipe> findAll() {
        return recipeMap.values().stream().toList();
    }
}

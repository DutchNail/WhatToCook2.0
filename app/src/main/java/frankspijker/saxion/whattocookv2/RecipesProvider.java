package frankspijker.saxion.whattocookv2;

import java.util.ArrayList;
import java.util.List;

import frankspijker.saxion.whattocookv2.Recipes;

public class RecipesProvider {

    public static List<Recipes> recipesList;

    static {
        recipesList = new ArrayList<>();


    }

    public static void addItem(Recipes recipe) {
        recipesList.add(recipe);
        for (Ingredient ingredient: recipe.getIngredientList()) {
            IngredientProvider.addIngredient(ingredient);
        }
    }

    public static List<Recipes> getRecipesList() {
        return recipesList;
    }

    public static int getSize() {
        return recipesList.size();
    }

    public static void removeRecipe(Recipes recipe) {
        for (Ingredient ingredient : recipe.getIngredientList()) {
            IngredientProvider.removeIngredient(ingredient);
        }
        recipesList.remove(recipe);
    }

    public static void setRecipesList(List<Recipes> recipesList) {
        RecipesProvider.recipesList = recipesList;
    }
}

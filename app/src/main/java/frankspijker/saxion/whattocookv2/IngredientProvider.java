package frankspijker.saxion.whattocookv2;

import java.util.ArrayList;
import java.util.List;

class IngredientProvider {

    public static List<Ingredient> ingredientList;

    static {
        ingredientList = new ArrayList<>();
    }

    public static List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public static void setIngredientList(List<Ingredient> ingredientList) {
        IngredientProvider.ingredientList = ingredientList;
    }

    public static int getSize() {
        return ingredientList.size();
    }

    public static void removeIngredient(Ingredient ingredient) {
        ingredientList.remove(ingredient);
    }

    public static void addIngredient(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }
}
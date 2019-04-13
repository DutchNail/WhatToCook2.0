package frankspijker.saxion.whattocookv2;

import java.util.ArrayList;
import java.util.List;

public class Recipes {

    private static int idCounter = 0;
    private int id;
    private String title;
    private String description;
    private List<Ingredient> ingredientList;



    public Recipes(String title, String description, List<Ingredient> ingredientList) {
        this.id = idCounter;
        this.title = title;
        this.description = description;
        this.ingredientList = ingredientList;
        idCounter++;
    }

    @Override
    public String toString() {
        return "Recipes{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ingredientList=" + ingredientList +
                '}';
    }

    static Recipes getRecipeById(int recipeId) {
        List<Recipes> recipes = RecipesProvider.getRecipesList();
        for (Recipes recipe: recipes) {
            if(recipe.getId() == recipeId) {
                return recipe;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredientList() {
        return this.ingredientList;
    }


    public List<Ingredient> getIngredientsFromRecipe(String title) {
        if(this.title.equals(title)) {
            return this.ingredientList;
        }
        return null;
    }


    public void addIngredient(Ingredient i) {
        this.ingredientList.add(i);
    }
}

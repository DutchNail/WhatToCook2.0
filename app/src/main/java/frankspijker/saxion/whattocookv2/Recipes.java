package frankspijker.saxion.whattocookv2;

import java.util.List;

public class Recipes {

    private static int idcounter;
    private int id;
    private String title;
    private String description;
    private List<Ingredient> ingredientList;



    public Recipes(String title, String description, List<Ingredient> ingredientList) {
        this.id = idcounter++;
        this.title = title;
        this.description = description;
        this.ingredientList = ingredientList;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public List<Ingredient> getIngredientsFromRecipe(String title) {
        if(this.title.equals(title)) {
            return this.ingredientList;
        }
        return null;
    }
}

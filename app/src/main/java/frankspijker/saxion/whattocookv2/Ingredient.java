package frankspijker.saxion.whattocookv2;

import java.util.List;

public class Ingredient {

    private static int idcounter;
    private int id;
    private String name;
    private double amount;
    private String type;


    public int getId() {
        return id;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public Ingredient(String name, double amount, String type) {
        this.id = idcounter;
        this.name = name;
        this.amount = amount;
        this.type = type;
//        this.amountType = amountType;
        idcounter++;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }

    static Ingredient getIngredientById(int ingredientId) {
        List<Ingredient> ingredients = IngredientProvider.getIngredientList();
        for (Ingredient ingredient: ingredients) {
            if(ingredient.getId() == ingredientId) {
                return ingredient;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}

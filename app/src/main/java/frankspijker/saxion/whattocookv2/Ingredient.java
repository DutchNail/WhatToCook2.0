package frankspijker.saxion.whattocookv2;

public class Ingredient {


    private String name;
    private int amount;
    public enum AmountType {
        Kilogram, Gram, Liter, Mililiter,
        centiliter, deciliter, miligram,
        gram, stuks
    }
    private AmountType amountType;


    public Ingredient(String name, int amount, AmountType amountType) {
        this.name = name;
        this.amount = amount;
        this.amountType = amountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public AmountType getAmountType() {
        return amountType;
    }

    public void setAmountType(AmountType amountType) {
        this.amountType = amountType;
    }
}

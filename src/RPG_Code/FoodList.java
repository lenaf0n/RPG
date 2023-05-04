package RPG_Code;


public class FoodList extends Consumables{
    private int meatFood;
    private int friesFood;
    private int sodaFood;
    private int max;

    public FoodList(int meatFood, int friesFood, int sodaFood, int max) {
        this.meatFood = meatFood;
        this.sodaFood = sodaFood;
        this.friesFood = friesFood;
        this.max = max;
    }
    //pour savoir combien il reste au total et si il faut enelever cette option pour joueur
    @Override
    public int calcTotalItems() {
        return this.meatFood + this.friesFood + this.sodaFood;
    }

    //pour savoir quoi afficher et de combien ca heal
    public int getMeatHeal(Hero player) {
        return 4 + player.getFpUpgrade();
    }

    //pour avoir info sur combien attack ou defense augmente
    public int getAttackDefenceBetter(Hero player) {
        return 2 + player.getFpUpgrade();
    }

    public int getMeatFood() {
        return meatFood;
    }

    public void setMeatFood(int meatFood) {
        this.meatFood = meatFood;
    }

    public int getSodaFood() {
        return sodaFood;
    }

    public void setSodaFood(int sodaFood) {
        this.sodaFood = sodaFood;
    }

    public int getFriesFood() {
        return friesFood;
    }

    public void setFriesFood(int friesFood) {
        this.friesFood = friesFood;
    }

    public int getMax() {return max;}
    public void setMax(int max) {this.max = max;}

    //pour quand hero choisit more food & potion
    public void setAllFood(int maxFood) {
        this.meatFood = maxFood;
        this.friesFood = maxFood;
        this.sodaFood = maxFood;
    }
}

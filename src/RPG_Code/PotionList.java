package RPG_Code;

public class PotionList extends Consumables { // meme chose que FoodList
    private int firePotion;
    private int damagePotion;
    private int waterPotion;
    private int max;

    public PotionList(int firePotion, int damagePotion, int waterPotion, int max) {
        this.damagePotion = damagePotion;
        this.waterPotion = waterPotion;
        this.firePotion = firePotion;
        this.max = max;
    }
    public int getDamagePotion() {
        return damagePotion;
    }
    public void setDamagePotion(int damagePotion) {
        this.damagePotion = damagePotion;
    }

    public int getFirePotion() {
        return firePotion;
    }
    public void setFirePotion(int firePotion) {
        this.firePotion = firePotion;
    }

    public int getWaterPotion() {
        return waterPotion;
    }
    public void setWaterPotion(int waterPotion) {
        this.waterPotion = waterPotion;
    }

    @Override
    public int calcTotalItems() {
        return this.firePotion + this.damagePotion + this.waterPotion;
    }

    public int getMax() {return max;}
    public void setMax(int max) {this.max = max;}


    public void setAllPotion(int maxPotion) {
        this.firePotion = maxPotion;
        this.waterPotion = maxPotion;
        this.damagePotion = maxPotion;
    }
}

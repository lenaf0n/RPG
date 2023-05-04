package RPG_Code;

public abstract class SpellCaster extends Hero {
    int mana;
    int potion;

    public int getMana() {return this.mana;}
    public void setMana(int mana) {this.mana = mana;}

    public void setPotion(int potion) {this.potion = potion;}
    public int getPotion() {return this.potion;}

    //seulement le mage ou le healer peut faire cette action
    public void defendYourself() {
        this.setPotionAffect("defend");
        this.setDefence(this.getDefence()+2+this.getFpUpgrade());

        this.setPotionTurn(0);
        this.setPotion(this.getPotion()-1);
    }
}

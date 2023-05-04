package RPG_Code;

import java.util.Random;

public abstract class Combatant {
    protected String name;
    protected int attack;
    protected int defence;
    protected float life;
    Random rand = new Random();

    //getters et setters
    public void setLife(float life) {
        life = Math.min(life, 20);
        this.life = life;
    }
    public float getLife() {
        return this.life;
    }

    public void setAttack(int attack) {this.attack = attack;}
    public int getAttack() {return this.attack;}

    public void setDefence(int defence) {this.defence = defence;}
    public int getDefence() {return this.defence;}

    public String getName() {
        return this.name;
    }

    //pour savoir quel type d'hero en string
    public String typeToString() {
        Combatant beingType = this;
        if (beingType instanceof Healer) {
            return "Healer";
        }
        else if (beingType instanceof Hunter) {
            return "Hunter";
        }
        else if (beingType instanceof Mage) {
            return "Mage";
        }
        else {
            return "Warrior";
        }
    }
}

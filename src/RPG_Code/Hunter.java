package RPG_Code;

public class Hunter extends Hero {
    private int arrows;
    private final PotionList potions;

    public Hunter (int attack, int defence, int life, int arrows, int fpUpgrade, String potionAffect, Weapons weapons,
                   FoodList foods, PotionList potions, String foodEffect, int foodTurn, int potionTurn, String name) {
        this.attack = attack;
        this.defence = defence;
        this.life = life;
        this.arrows = arrows;
        this.fpUpgrade = fpUpgrade;
        this.potionAffect = potionAffect;
        this.weapons = weapons;
        this.foods = foods;
        this.potions = potions;
        this.foodEffect = foodEffect;
        this.foodTurn = foodTurn;
        this.potionTurn = potionTurn;
        this.name = name;

    }

    public void setArrows(int arrows) {this.arrows = arrows;}
    public int getArrows() {return this.arrows; }

    public PotionList getPotions() {return this.potions;}

    //attack normal du hunter
    public boolean attackNormalOfHunter(Enemy enemy) {
        float damage = this.attack(this, enemy);
        boolean riposte;
        //si reste des fleche
        if (this.getArrows() != 0) {
            this.setArrows(this.getArrows()-1);
            riposte = false;
        }
        //si il en a plus chance que l'enemy riposte l'attaque
        else {
            riposte = rand.nextInt(3)==0;
            if (riposte) {
                this.setLife(this.getLife() + this.getDefence() - enemy.getAttack());
            }
        }
        enemy.setLife(enemy.getLife()-damage);
        return riposte;
    }

    //attack du hunter si il utilise la potion pour faire une attack feu
    public boolean fireAttackOfHunter(Enemy enemy) {
        float damage = this.attack(this, enemy)+this.getFpUpgrade();
        boolean riposte;
        //utilise la potion
        this.setPotionAffect("fire");
        this.setPotionTurn(0);

        this.getPotions().setFirePotion(this.getPotions().getFirePotion()-1);
        //si reste des fleche
        if (this.getArrows() != 0) {
            this.setArrows(this.getArrows()-1);
            riposte = false;
        }
        //si il en a plus chance que l'enemy riposte l'attaque
        else {
            riposte = rand.nextInt(3)==0;
            if (riposte) {
                this.setLife(this.getLife() + this.getDefence() - enemy.getAttack());
            }
        }
        enemy.setLife(enemy.getLife()-damage);
        return riposte;
    }

    //attack du hunter si il utilise la potion pour faire une attack eau
    public boolean waterAttackOfHunter(Enemy enemy) {// meme chose que plus haut
        float damage = this.attack(this, enemy)+this.getFpUpgrade();
        boolean riposte = false;

        this.setPotionAffect("water");
        enemy.setDefence(enemy.getDefence()-2);
        this.setPotionTurn(0);

        this.getPotions().setWaterPotion(this.getPotions().getWaterPotion()-1);
        if (this.getArrows() != 0) {
            this.setArrows(this.getArrows()-1);
        }
        else {
            riposte = rand.nextInt(3)==0;
            if (riposte) {
                this.setLife(this.getLife() + this.getDefence() - enemy.getAttack());
            }
        }
        enemy.setLife(enemy.getLife()-damage);
        return riposte;
    }

    //attack du hunter si il utilise la potion pour faire une attack avec plus de degat
    public boolean strengthAttackOfHunter(Enemy enemy) {//meme chose que plus haut
        float damage = 2*(this.attack(this, enemy))+this.getFpUpgrade();
        boolean riposte = false;
        this.getPotions().setDamagePotion(this.getPotions().getDamagePotion()-1);
        if (this.getArrows() != 0) {
            this.setArrows(this.getArrows()-1);
        }
        else {
            riposte = rand.nextInt(3)==0;
            if (riposte) {
                this.setLife(this.getLife() + this.getDefence() - enemy.getAttack());
            }
        }
        enemy.setLife(enemy.getLife()-damage);
        return riposte;
    }
}

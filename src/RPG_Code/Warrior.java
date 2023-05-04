package RPG_Code;

public class Warrior extends Hero{
    private final PotionList potions;
    public Warrior (int attack, int defence, int life, int fpUpgrade, String potionAffect, Weapons weapons, FoodList foods,
                    PotionList potions, String foodEffect, int foodTurn, int potionTurn, String name) {
        this.attack = attack;
        this.defence = defence;
        this.life = life;
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

    public PotionList getPotions() {return this.potions;}

    //attack normal du warrior
    public String normalAttackOfWarrior(Enemy enemy) {
        float damage = this.attack(this, enemy);
        String special = "normal";
        boolean criticalHit = rand.nextInt(5)==0;
        if (criticalHit) {
            damage = (float) (damage*(1.5));
            special = "crit";
        }
        else {
            boolean riposte = rand.nextInt(3)==0;
            if (riposte) {
                this.setLife(this.getLife() + this.getDefence() - enemy.getAttack());
                special = "riposte";
            }
        }
        enemy.setLife(enemy.getLife()-damage);
        return special;
    }

    //code pour attack du warrior en utilisant la potion fire
    public String fireAttackOfWarrior(Enemy enemy) {
        float damage = this.attack(this, enemy)+this.getFpUpgrade();
        String special = "normal";
        this.setPotionAffect("fire");
        this.setPotionTurn(0);

        this.getPotions().setFirePotion(this.getPotions().getFirePotion()-1);
        boolean criticalHit = rand.nextInt(5)==0;
        if (criticalHit) {
            damage = (float) (damage*(1.5));
            special = "crit";
        }
        else {
            boolean riposte = rand.nextInt(3)==0;
            if (riposte) {
                this.setLife(this.getLife() + this.getDefence() - enemy.getAttack());
                special = "riposte";
            }
        }
        enemy.setLife(enemy.getLife()-damage);
        return special;
    }

    //code pour attack du warrior en utilisant la potion water
    public String waterAttackOfWarrior(Enemy enemy) {
        float damage = this.attack(this, enemy)+this.getFpUpgrade();
        String special = "normal";
        this.setPotionAffect("water");
        enemy.setDefence(enemy.getDefence()-2);
        this.setPotionTurn(0);

        this.getPotions().setWaterPotion(this.getPotions().getWaterPotion()-1);
        boolean criticalHit = rand.nextInt(5)==0;
        if (criticalHit) {
            damage = (float) (damage*(1.5));
            special = "crit";
        }
        else {
            boolean riposte = rand.nextInt(3)==0;
            if (riposte) {
                this.setLife(this.getLife() + this.getDefence() - enemy.getAttack());
                special = "riposte";
            }
        }
        enemy.setLife(enemy.getLife()-damage);
        return special;
    }

    //code pour attack du warrior en utilisant la potion damage
    public boolean strengthAttackOfWarrior(Enemy enemy) {
        float damage = 2*(this.attack(this, enemy))+this.getFpUpgrade();

        this.getPotions().setDamagePotion(this.getPotions().getDamagePotion()-1);
        boolean riposte = rand.nextInt(3)==0;
        if (riposte) {
            this.setLife(this.getLife() + this.getDefence() - enemy.getAttack());
        }
        enemy.setLife(enemy.getLife()-damage);
        return riposte;
    }
}

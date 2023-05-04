package RPG_Code;

import java.util.ArrayList;

public class Mage extends SpellCaster {
    public Mage (int attack, int defence, int life, int mana, int fpUpgrade, int potion, String potionAffect, Weapons weapons,
                 FoodList foods, String foodEffect, int foodTurn, int potionTurn, String name) {
        this.attack = attack;
        this.defence = defence;
        this.life = life;
        this.mana = mana;
        this.fpUpgrade = fpUpgrade;
        this.potion = potion;
        this.potionAffect = potionAffect;
        this.weapons = weapons;
        this.foods = foods;
        this.foodEffect = foodEffect;
        this.foodTurn = foodTurn;
        this.potionTurn = potionTurn;
        this.name = name;

    }
    //attack thunder du mage, enleve 3 vie a tout les enemy a travers leur armure
    public void lightningAttack(Mage player, ArrayList<Enemy> enemies) {
        for (Enemy i : enemies) {
            i.setLife(i.getLife()-3);
        }
        this.setMana(player.getMana()-3);
    }

    //attack normal du mage
    public void soundAttack(Mage player, Enemy enemy) {
        float damage = this.attack(player, enemy);
        enemy.setLife(enemy.getLife()-damage);
    }

    //attack poison du mage
    public void poisonAttack(Mage player, Enemy enemy) {
        enemy.setAffected("poison");
        enemy.setAffectedTurn(0);
        float damage = this.attack(player, enemy);
        enemy.setLife(enemy.getLife()-damage);
    }
}

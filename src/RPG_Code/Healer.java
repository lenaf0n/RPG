package RPG_Code;

import java.util.ArrayList;

public class Healer extends SpellCaster {
    public Healer (int potion, int attack, int defence, int life, int fpUpgrade, int mana, String potionAffect,
                   Weapons weapons, FoodList foods, String foodEffect, int foodTurn, int potionTurn, String name) {
        this.potion = potion;
        this.attack = attack;
        this.defence = defence;
        this.life = life;
        this.fpUpgrade = fpUpgrade;
        this.mana = mana;
        this.potionAffect = potionAffect;
        this.weapons = weapons;
        this.foods = foods;
        this.foodEffect = foodEffect;
        this.foodTurn = foodTurn;
        this.potionTurn = potionTurn;
        this.name = name;
    }

    //attack du healer
    public void attackForHealer(Healer player, Enemy enemy) {
        float damage = player.attack(player, enemy);
        enemy.setLife(enemy.getLife() - damage);
        System.out.println(enemy.getName() + " was attacked \nLife: " + enemy.getLife());
    }

    //heal special du healer ou il heal tout le monde
    public void healAllPlayers(Healer player, ArrayList<Combatant> players) {
        player.setMana(player.getMana() - 2);
        for (Combatant i : players) {
            i.setLife(i.getLife() + 3);
        }
    }

    //heal normal du healer
    public void healOnePlayer(Healer player, Combatant healedPlayer) {
        player.setMana(player.getMana() - 1);
        healedPlayer.setLife(healedPlayer.getLife() + 6 + player.getFpUpgrade());
    }
}

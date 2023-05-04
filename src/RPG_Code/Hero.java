package RPG_Code;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Hero extends Combatant {
    protected int fpUpgrade;
    protected int potionTurn;
    protected String potionAffect;
    protected Weapons weapons;
    protected FoodList foods;
    protected String foodEffect = "no";
    protected int foodTurn;
    Scanner sc = new Scanner(System.in);



    public String getFoodEffect() {return this.foodEffect;}
    public void setFoodEffect(String foodEffect) {this.foodEffect = foodEffect;}

    public int getFoodTurn() {return this.foodTurn;}
    public void setFoodTurn(int foodTurn) {this.foodTurn = foodTurn;}

    public void setFpUpgrade(int fpUpgrade) {this.fpUpgrade = fpUpgrade;}
    public int getFpUpgrade() {return this.fpUpgrade;}

    public void setPotionTurn(int potionTurn) {this.potionTurn = potionTurn;}
    public int getPotionTurn() {return this.potionTurn;}


    public void setPotionAffect(String potionAffect) {this.potionAffect = potionAffect;}
    public String getPotionAffect() {return this.potionAffect;}

    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public FoodList getFoods() {return this.foods;}

    public void setWeapons(Weapons weapons) {this.weapons = weapons;}
    public Weapons getWeapons() {return weapons;}

    //pour choisir qu'elle enemy le hero voudra attacker
    public Enemy chooseEnemy(ArrayList<Enemy> enemies) {
        for (int j = 0; j < enemies.size(); j++) {
            System.out.println("["+ (j + 1) + "] " + enemies.get(j).getName() + "(" + enemies.get(j).getLife() + " life)");
        }
        System.out.println("Choose enemy to attack : ");
        int enemyNum = sc.nextInt();
        return enemies.get(enemyNum - 1);
    }

    //pour retourner le degat que les heros vont faire au enemy
    public float attack(Hero attacker, Combatant attacked) {
        return attacker.getAttack() + attacker.getWeapons().getWeaponDamage() - attacked.getDefence();
    }

    //manger de la viande
    public void eatMeat() {
        this.foods.setMeatFood(this.foods.getMeatFood()-1);
        this.setLife(this.getLife() + 4 + this.getFpUpgrade());
    }

    //manger des frites
    public void eatFries() {
        this.foods.setFriesFood(this.foods.getFriesFood()-1);
        this.setLife(this.getLife() + 2 + this.getFpUpgrade());

        this.setFoodEffect("strength");
        this.setFoodTurn(0);
    }

    //boire un energy drink
    public void eatSoda() {
        this.foods.setSodaFood(this.foods.getSodaFood()-1);
        this.setDefence(this.getDefence() + 2 + this.getFpUpgrade());

        this.setFoodEffect("def");
        this.setFoodTurn(0);
    }

}

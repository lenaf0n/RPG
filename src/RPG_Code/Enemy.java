package RPG_Code;


import java.util.ArrayList;

public class Enemy extends Combatant {
    protected String affected;
    protected int affectedTurn;

    public Enemy (int attack, int defence, int life, String name, String affected, int affectedTurn) {
        this.attack = attack;
        this.defence = defence;
        this.life = life;
        this.name = name;
        this.affected = affected;
        this.affectedTurn = affectedTurn;
    }

    //getters et setters pour toutes les variables
    public int getAffectedTurn() {
        return affectedTurn;
    }

    public void setAffectedTurn(int affectedTurn) {
        this.affectedTurn=affectedTurn;
    }

    public String getAffected() {return this.affected;}
    public void setAffected(String affected) {this.affected = affected;}

// attack d'un enemy normal
    public boolean normalAttack(Hero player) {
        //degat que l'hero va resevoir avec un minimum de 0 pour empecher degat negatif
        float damage = Math.max(this.getAttack() - this.getDefence(),1);

        //1/4 chance d'une attack critique de l'enemy
        boolean moreDamage = rand.nextInt(4)==0;
        if (moreDamage) {
            damage = damage*2;
        }
        //attack joueur
        player.setLife(player.getLife() - damage);
        //dit si attack critique ou non, pour savoir quoi dire
        return moreDamage;
    }

    //attack du boss
    public String bossAttack(ArrayList<Combatant> players, Hero player) {
        //dit si attack critique, special ou non, pour savoir quoi dire
        String special = "normal";

        // attack critique ou special
        boolean moreDamage = rand.nextInt(3)==0;
        if (moreDamage) {
            //attack special du boss pour savoir si il attaque tout le monde ene meme temps
            boolean specialAttack = rand.nextInt(4)==0;
            if (specialAttack) {
                for (Combatant i : players) {
                    float damage = Math.max(this.getAttack() - i.getDefence(), 1);
                    i.setLife(i.getLife()-damage);
                }
                special = "spec";
            }
            //attaque critique sur un enemy
            else{
                float damage =Math.max(this.getAttack() - player.getDefence(), 1);
                player.setLife(player.getLife()-damage);
                special = "crit";
            }
        }
        //attaque normal
        else{
            float damage = Math.max(this.getAttack() - player.getDefence(),1);
            player.setLife(player.getLife() - damage);
        }
        return special;
    }

}

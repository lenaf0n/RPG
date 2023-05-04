import RPG_Code.Combatant;
import RPG_Code.Enemy;
import RPG_Code.Hero;

import java.util.ArrayList;

public class ComputerFights {
    public void computerTurn(ArrayList<Combatant> players, Enemy enemy) {

        // mage poisoning enemy damage each turn for 2 turn
        if (!(enemy.getAffected().equals("no"))) {
            if (enemy.getAffectedTurn() < 2) {
                if ((enemy.getAffected().equals("poison")) ||(enemy.getAffected().equals("fire"))) {
                    enemy.setLife(enemy.getLife() - 2);
                    enemy.setAffectedTurn(enemy.getAffectedTurn() + 1);
                    System.out.println(enemy.getName()+" took damage, life: "+enemy.getLife());
                }
                else if (enemy.getAffected().equals("water")) {
                    enemy.setAffectedTurn(enemy.getAffectedTurn() + 1);
                    System.out.println(enemy.getName()+" slipped, defense: "+enemy.getDefence());
                }
            } else {
                if (enemy.getAffected().equals("water")) {
                    enemy.setDefence(enemy.getDefence()+2);
                    System.out.println("The floor has been cleaned !");
                }
                enemy.setAffected("no");
                enemy.setAffectedTurn(0);
            }
        }
        // attack of enemy
        int r = Hero.getRandomNumber(0, players.size());
        Hero player = (Hero) players.get(r);

        if (enemy.getName().equals("Boss")) {
            String special = enemy.bossAttack(players, player);
            if (special.equals("spec")) {
                System.out.println("KABOOOM ! \nEveryone took damage !");
            }
            else if (special.equals("crit")) {
                System.out.println("Critical Hit! \n"+player.getName()+" has been attacked \ntheir life is now "+player.getLife());
            }
            else {
                System.out.println(player.getName()+" has been attacked \ntheir life is now "+player.getLife());
            }
        }
        else {
            boolean crit = enemy.normalAttack(player);
            if (crit) {
                System.out.println("Critical Hit!");
            }
            System.out.println(player.getName()+" has been attacked \ntheir life is now "+player.getLife());
        }
    }

}

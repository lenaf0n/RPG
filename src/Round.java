import RPG_Code.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Round {

    public static ArrayList<Integer> getRandomOrder(int max) {
        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            order.add(i);
        }
        Collections.shuffle(order);
        return order;
    }

    public boolean roundPlayed(ArrayList<Combatant> players, ArrayList<Enemy> enemies) {
        Scanner sc = new Scanner(System.in);

        boolean playerWon = true;
        int beingTurn;

        // play until all heroes or all enemies are dead
        while (true) {
            int totalBeings = players.size() + enemies.size();
            ArrayList<Integer> order = getRandomOrder(totalBeings);

            // make all beings play
            for (Integer integer : order) {
                //enemy fight
                if (integer >= players.size()) {
                    //get enemy that is playing
                    beingTurn = integer - players.size();
                    Enemy enemy;
                    try {
                        enemy = enemies.get(beingTurn);
                    }catch (Exception e) {
                        enemy = enemies.get(beingTurn-1);
                    }

                    if (enemy.getLife() == 0) {
                        checkDeadEnemy(enemies);
                    } else {
                        System.out.println(enemy.getName() + " is now playing :");
                        System.out.println("Life: " + enemy.getLife() + "\tAttack: " + enemy.getAttack());
                        System.out.println("Defense: " + enemy.getDefence() + "\tAffected: " + enemy.getAffected() + "\n");

                        ComputerFights computerFights = new ComputerFights();
                        computerFights.computerTurn(players, enemy);
                    }
                }
                //user turn
                else {
                    beingTurn = integer; // who plays
                    Hero player = (Hero) players.get(beingTurn);// get the player
                    System.out.println("It is the turn of " + players.get(beingTurn).getName());
                    System.out.println("Life: " + player.getLife() + "\tAttack: " + player.getAttack());
                    System.out.println("Defense: " + player.getDefence() + "\tFood: " + player.getFoods().calcTotalItems());
                    if (player instanceof SpellCaster) {
                        System.out.println("Potion: " + ((SpellCaster)player).getPotion() + "\tMana: " + ((SpellCaster) player).getMana());
                    } else if (player instanceof Hunter) {
                        System.out.println("Potion: " + ((Hunter) player).getPotions().calcTotalItems() + "\tArrows: " + ((Hunter) player).getArrows());
                    } else if (player instanceof Warrior) {
                        System.out.println("Potion: " + ((Warrior) player).getPotions().calcTotalItems());
                    }
                    System.out.println();

                    UserFights userFights = new UserFights();
                    userFights.userTurn(players, enemies, player);
                }

                System.out.println("Understood ? (y)");
                sc.nextLine();

                System.out.println();
                System.out.println("- - - - - - - - ");
                System.out.println();

                //looks for enemy dead
                checkDeadEnemy(enemies);
                //checks if all enemies are dead
                if (enemies.size() == 0) {
                    break;
                }

                //looks for player dead
                ArrayList<Combatant> toRemovePlayer = new ArrayList<>();
                for (Combatant j : players) {
                    if (j.getLife() <= 0) {
                        toRemovePlayer.add(j);
                    }
                }
                players.removeAll(toRemovePlayer);
                players.trimToSize();
                //checks if all players are dead
                if (players.size() == 0) {
                    break;
                }
            }
            if (players.size() == 0) {
                playerWon = false;
                break;
            } else if (enemies.size() == 0) {
                break;
            }
        }
        return playerWon;
    }

    public void checkDeadEnemy(ArrayList<Enemy> allEnemies) {
        ArrayList<Enemy> toRemoveEnemy = new ArrayList<>();
        for (Enemy j : allEnemies) {
            if (j.getLife() <= 0) {
                toRemoveEnemy.add(j);
            }
        }
        allEnemies.removeAll(toRemoveEnemy);
        allEnemies.trimToSize();
    }
}

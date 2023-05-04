import RPG_Code.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.round;

public class Game {
    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static String randomName() {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            Random r = new Random();
            char c = (char) (r.nextInt(26) + 'a');
            name.append(c);
        }
        return name.toString();
    }

    public void gameRun(ArrayList<Combatant> allPlayers) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Enemy> allEnemies = new ArrayList<>();
        String roundPlayed;

        //create round and player
        for (int i = 0; i < 5; i++) {
            if (i < 4) {
                roundPlayed = "normal";
                for (int j = 0; j < allPlayers.size(); j++) {
                    String name = randomName();
                    int attack = getRandomNumber(4,6);
                    int defense = getRandomNumber(1,2);
                    if (i != 0) {
                        if (i%2 == 0) {
                            attack = getRandomNumber(4+i/2,6+i/2);
                        }
                        else {
                            defense = getRandomNumber(1+i/2,2+i/2);
                        }
                    }
                    Enemy enemy = new Enemy(attack,defense,getRandomNumber(8+2*i,12+2*i), name, "no", 0);
                    allEnemies.add(enemy);
                }
            }
            //creation de boss
            else {
                roundPlayed = "boss";
                String name = "Boss";
                Enemy enemy = new Enemy(10*round(round(allPlayers.size()/2)), 5, 25*allPlayers.size(), name, "no", 0);
                allEnemies.add(enemy);
            }

            System.out.println("Let the games begin!! ");
            System.out.println("\n");

            Round round = new Round();
            boolean playerWinner = round.roundPlayed(allPlayers, allEnemies);
            if (roundPlayed.equals("normal")) {
                if (!playerWinner) {
                    System.out.println("You lost ! GAME OVER");
                    break;
                }
                else {
                    System.out.println("You won the round !");

                    //help + reset players after end of round
                    for (Combatant j : allPlayers) {
                        j.setLife(j.getLife()+3);
                        ((Hero)j).getFoods().setAllFood(((Hero)j).getFoods().getMax());
                        if (j instanceof Hunter) {
                            ((Hunter) j).setArrows(Math.min(((Hunter) j).getArrows()+3, 8));
                            ((Hunter) j).getPotions().setAllPotion(((Hunter) j).getPotions().getMax());
                        }
                        else if (j instanceof Mage) {
                            ((Mage) j).setMana(((Mage) j).getMana()+3);
                        }
                        else if (j instanceof Healer) {
                            ((Healer) j).setMana(((Healer) j).getMana()+3);
                        }
                        else if (j instanceof Warrior) {
                            ((Warrior) j).getPotions().setAllPotion(((Warrior) j).getPotions().getMax());
                        }
                    }
                    System.out.println("- - - - - - - - - ");

                    for (Combatant j : allPlayers) {
                        System.out.println(j.getName() + " turn to pick");
                        Hero player = (Hero)j;
                        int benefitsChoice = 0;
                        boolean isValid;
                        do {
                            if (player instanceof Hunter) {
                                System.out.println("[1] more attack [2] more defense [3] better food & potion [4] more food & potion [" +
                                        "5] more arrows");
                            }
                            else if (player instanceof Warrior) {
                                System.out.println("[1] more attack [2] more defense [3] better food & potion [4] more food & potion");
                            }
                            else {
                                System.out.println("[1] more attack [2] more defense [3] better food & potion [4] more food & potion [" +
                                        "6] more mana");
                            }

                            if(sc.hasNextInt()) {
                                benefitsChoice = sc.nextInt();
                                if ((benefitsChoice >= 1) && (benefitsChoice <=6)) {
                                    if (player instanceof Hunter) {
                                        isValid = benefitsChoice != 6;
                                    }
                                    else if ((player instanceof Mage) || (player instanceof Healer)) {
                                        isValid = benefitsChoice != 5;
                                    }
                                    else {
                                        isValid = benefitsChoice <= 4;
                                    }
                                }
                                else {
                                    isValid = false;
                                }
                                sc.nextLine();
                            } else {
                                System.out.println("Please input an integer.");
                                isValid = false;
                                sc.nextLine();
                            }
                        } while (!isValid);

                        switch (benefitsChoice) {
                            case 1:
                                player.setAttack(player.getAttack() + 2);
                                break;
                            case 2:
                                player.setDefence(player.getDefence() + 2);
                                break;
                            case 3:
                                player.setFpUpgrade(player.getFpUpgrade() + 1);
                                break;
                            case 4:
                                player.getFoods().setMax(player.getFoods().getMax() + 1);
                                player.getFoods().setAllFood(player.getFoods().getMax());

                                if (player instanceof Warrior) {
                                    ((Warrior) player).getPotions().setMax(((Warrior) player).getPotions().getMax() + 1);
                                    ((Warrior) player).getPotions().setAllPotion(((Warrior) player).getPotions().getMax());
                                } else if (player instanceof Hunter) {
                                    ((Hunter) player).getPotions().setMax(((Hunter) player).getPotions().getMax() + 1);
                                    ((Hunter) player).getPotions().setAllPotion(((Hunter) player).getPotions().getMax());
                                } else {
                                    ((SpellCaster)player).setPotion(((SpellCaster)player).getPotion() + 3);
                                }
                                break;
                            case 5:
                                ((Hunter) player).setArrows(((Hunter) player).getArrows() + 5);
                                break;
                            case 6:
                                ((SpellCaster) player).setMana(((SpellCaster) player).getMana() + 3);
                                break;
                        }
                    }
                }
            }
            else {
                if (!playerWinner) {
                    System.out.println("You lost ! GAME OVER");
                    break;
                }
                else {
                    System.out.println("GAME WON ! ");
                    System.out.println("\n");
                    System.out.println("Congratulations, you were able to defeat all enemies \nAnd have consequently saved the world ! " +
                            "\nAll you have left to do is go home.");
                }
            }
        }
    }
}

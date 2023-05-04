import RPG_Code.*;

import java.util.ArrayList;
import java.util.Scanner;

public class UserFights {
    public void userTurn(ArrayList<Combatant> players, ArrayList<Enemy> enemies, Hero player) {
        //combat du joueur
        Scanner sc = new Scanner(System.in);
        if (player instanceof Hunter) {
            //s'occuper de l'arme du hunter si plus de fleche = attack sera moins puissante
            if (((Hunter) player).getArrows() == 0) {
                player.setWeapons(Weapons.Arrow);
            }
        }

        //s'occupe des effets nourriture
        if (!(player.getFoodEffect().equals("no"))) {
            if (player.getFoodTurn() == 2) {
                if (player.getFoodEffect().equals("strength")) {
                    player.setAttack(player.getAttack()-2);

                }
                else {
                    player.setDefence(player.getDefence()-2);
                }
                player.setFoodTurn(0);
                player.setFoodEffect("no");
            }
            else {
                player.setFoodTurn(player.getFoodTurn()+1);
            }
        }

        //s'occupe des effets potion
        if (!(player.getPotionAffect().equals("no"))) {
            if (player.getPotionTurn() == 2) {
                player.setPotionAffect("no");
                player.setDefence(player.getDefence()-2);
            }
            else {
                player.setPotionTurn(player.getPotionTurn()+1);
            }
        }
        boolean isValid;
        int turnChoice = 0;
        System.out.print("You can : ");

        do {
            // prints option for specific player with specific items
            if (player.getFoods().calcTotalItems() != 0) {
                System.out.print("[1] eat ");
            }
            System.out.print("[2] Attack ");
            if ((player instanceof SpellCaster) && (((SpellCaster)player).getPotion() != 0)) {
                System.out.print("[3] Defend ");
            }
            if ((player instanceof Healer) && (((Healer) player).getMana() != 0)) {
                System.out.print("[4] heal others ");
            }

            //checks if user input is valid
            if(sc.hasNextInt()) {
                turnChoice = sc.nextInt();

                if ((turnChoice <= 4) && (turnChoice >= 1)) {
                    if (turnChoice == 1) {
                        isValid = player.getFoods().calcTotalItems() != 0;
                    }
                    else if (turnChoice == 3) {
                        isValid = (player instanceof SpellCaster) && (((SpellCaster)player).getPotion() != 0);
                    }
                    else if (turnChoice == 4) {
                        isValid = (player instanceof Healer) && (((Healer)player).getMana() != 0);
                    }
                    else {
                        isValid = true;
                    }
                } else {
                    System.out.println("please pick input between options provided");
                    isValid = false;
                }
                sc.nextLine();

            } else {
                System.out.println("Please input an integer.");
                isValid = false;
                sc.nextLine();
            }
        }while (!isValid);



        switch (turnChoice) {
            case 1:
                // eat food
                int foodChoice = 0;
                do {
                    //print food options
                    if (player.getFoods().getMeatFood() != 0) {
                        System.out.println("[1] Meat Food: " + player.getFoods().getMeatFood());
                    }
                    if (player.getFoods().getFriesFood() != 0) {
                        System.out.println("[2] Fries Food: "+player.getFoods().getFriesFood());
                    }
                    if (player.getFoods().getSodaFood() != 0) {
                        System.out.println("[3] Soda Food: "+player.getFoods().getSodaFood());
                    }

                    //check if eating choice is valid
                    if(sc.hasNextInt()) {
                        foodChoice = sc.nextInt();
                        if ((foodChoice <= 3) && (foodChoice >= 1)) {
                            switch (foodChoice) {
                                case 1:
                                    isValid = player.getFoods().getMeatFood() != 0;
                                    break;
                                case 2:
                                    isValid = player.getFoods().getFriesFood() != 0;
                                    break;
                                case 3:
                                    isValid = player.getFoods().getSodaFood() != 0;
                                    break;
                            }
                        }
                        else {
                            isValid = false;
                        }
                        sc.nextLine();
                    }
                    else {
                        System.out.println("Please input an integer.");
                        isValid = false;
                        sc.nextLine();
                    }
                } while (!isValid);

                //mise en scene
                switch (foodChoice) {
                    case 1:
                        player.eatMeat();
                        System.out.println("You ate a nice meal, \nLife: "+player.getLife());
                        break;
                    case 2:
                        player.eatFries();
                        System.out.println("You ate yummy fries, \nAttack: "+player.getAttack());
                        break;
                    case 3:
                        player.eatSoda();
                        System.out.println("You drunk an energizing soda, \nDefence: "+player.getDefence());
                        break;
                }
                break;

            case 2:
                // attack enemy
                if (player instanceof Healer) {
                    //healer attack
                    Enemy enemy = player.chooseEnemy(enemies);
                    ((Healer) player).attackForHealer(((Healer) player), enemy);
                    System.out.println(enemy.getName()+" has been attack, \nlife: "+enemy.getLife());
                }
                else if (player instanceof Mage) {
                    //mage attack
                    int spellChoice = 0;

                    //user proofing
                    do {
                        if (((Mage) player).getMana() >= 3) {
                            System.out.println("[1] lightning attack: damage everyone (-3 mana)");
                        }
                        System.out.println("[2] sound wave attack: normal attack (-0 mana)");
                        if (((Mage)player).getMana() >= 2) {
                            System.out.println("[3] poison: affected for 2 turns (-2 mana)");
                        }

                        if(sc.hasNextInt()) {
                            spellChoice = sc.nextInt();
                            if ((spellChoice >= 1) && (spellChoice <= 3)) {
                                switch (spellChoice) {
                                    case 1:
                                        isValid = ((Mage) player).getMana() >= 3;
                                        break;
                                    case 2:
                                        isValid = true;
                                        break;
                                    case 3:
                                        isValid = ((Mage)player).getMana() >= 2;
                                        break;
                                }
                            }
                            sc.nextLine();
                        }
                        else {
                            System.out.println("Please input an integer.");
                            isValid = false;
                            sc.nextLine();
                        }
                    } while (!isValid);

                    //choix de sort
                    switch (spellChoice) {
                        case 1:
                            ((Mage) player).lightningAttack(((Mage)player), enemies);
                            System.out.println("The enemies have been struck by lightning!");
                            for (Enemy i : enemies) {
                                System.out.print(i.getName()+" life: "+i.getLife()+"   ");
                            }
                            break;
                        case 2:
                            Enemy enemy = player.chooseEnemy(enemies);
                            ((Mage) player).soundAttack(((Mage) player), enemy);
                            System.out.println(enemy.getName()+" has been shocked !\nlives: "+enemy.getLife());
                            break;
                        case 3:
                            enemy = player.chooseEnemy(enemies);
                            ((Mage) player).poisonAttack(((Mage) player), enemy);
                            System.out.println(enemy.getName()+" has been poisoned ! \nlives: "+enemy.getLife());
                            break;
                    }

                }
                else if (player instanceof Hunter) {
                    //hunter attack
                    boolean riposte;
                    int attackChoice = 0;
                    Enemy enemy = player.chooseEnemy(enemies);

                    //user proofing
                    do {
                        String choices = "[1] normal attack";
                        if (((Hunter) player).getPotions().getDamagePotion() != 0) {
                            choices += "\n[2] strength attack";
                        }
                        if (((Hunter) player).getPotions().getFirePotion() != 0) {
                            choices +="\n[3] fire attack";
                        }
                        if (((Hunter) player).getPotions().getWaterPotion() != 0) {
                            choices +="\n[4] water attack";
                        }
                        System.out.println(choices);

                        if(sc.hasNextInt()) {
                            attackChoice = sc.nextInt();
                            if ((attackChoice >= 1) && (attackChoice <= 4)) {
                                switch (attackChoice) {
                                    case 1:
                                        isValid = true;
                                        break;
                                    case 2:
                                        isValid = ((Hunter) player).getPotions().getDamagePotion() != 0;
                                        break;
                                    case 3:
                                        isValid = ((Hunter) player).getPotions().getFirePotion() != 0;
                                        break;
                                    case 4:
                                        isValid = ((Hunter) player).getPotions().getWaterPotion() != 0;
                                        break;
                                }
                            }
                            sc.nextLine();
                        }
                        else {
                            System.out.println("Please input an integer.");
                            isValid = false;
                            sc.nextLine();
                        }
                    } while (!isValid);

                    //choix d'attack
                    switch (attackChoice) {
                        case 1:
                            riposte = ((Hunter) player).attackNormalOfHunter(enemy);
                            if (riposte) {
                                System.out.println("Riposte ! ");
                            }
                            System.out.println(enemy.getName()+" has been attacked ! \nlives: "+enemy.getLife());
                            break;
                        case 2:
                            riposte = ((Hunter) player).strengthAttackOfHunter(enemy);
                            if (riposte) {
                                System.out.println("Riposte ! ");
                            }
                            System.out.println(enemy.getName()+" has been seriously attacked ! \nlives: "+enemy.getLife());
                            break;
                        case 3:
                            riposte = ((Hunter) player).fireAttackOfHunter(enemy);
                            if (riposte) {
                                System.out.println("Riposte ! ");
                            }
                            System.out.println(enemy.getName()+" has been set on fire ! \nlives: "+enemy.getLife());
                            break;
                        case 4:
                            riposte = ((Hunter) player).waterAttackOfHunter(enemy);
                            if (riposte) {
                                System.out.println("Riposte ! ");
                            }
                            System.out.println(enemy.getName()+" has been soaked ! \nlives: "+enemy.getLife());
                            break;
                    }
                }
                else {
                    //warrior attack
                    String special;
                    boolean riposte;
                    Enemy enemy = player.chooseEnemy(enemies);
                    int attackChoice = 0;

                    //user proofing
                    do {
                        String choices = "[1] normal attack";
                        if (((Warrior) player).getPotions().getDamagePotion() != 0) {
                            choices += "\n[2] strength attack";
                        }
                        if (((Warrior) player).getPotions().getFirePotion() != 0) {
                            choices +="\n[3] fire attack";
                        }
                        if (((Warrior) player).getPotions().getWaterPotion() != 0) {
                            choices +="\n[4] water attack";
                        }
                        System.out.println(choices);

                        if(sc.hasNextInt()) {
                            attackChoice = sc.nextInt();
                            if ((attackChoice >= 1) && (attackChoice <= 4)) {
                                switch (attackChoice) {
                                    case 1:
                                        isValid = true;
                                        break;
                                    case 2:
                                        isValid = ((Warrior) player).getPotions().getDamagePotion() != 0;
                                        break;
                                    case 3:
                                        isValid = ((Warrior) player).getPotions().getFirePotion() != 0;
                                        break;
                                    case 4:
                                        isValid = ((Warrior) player).getPotions().getWaterPotion() != 0;
                                        break;
                                }
                            }
                            sc.nextLine();
                        }
                        else {
                            System.out.println("Please input an integer.");
                            isValid = false;
                            sc.nextLine();
                        }
                    } while (!isValid);

                    //choix d'attack
                    switch (attackChoice) {
                        case 1:
                            special = ((Warrior) player).normalAttackOfWarrior(enemy);
                            if (special.equals("riposte")) {
                                System.out.println("Riposte ! ");
                            }
                            else if (special.equals("crit")) {
                                System.out.println("Critical Hit! ");
                            }
                            System.out.println(enemy.getName()+" has been attacked ! \nlives: "+enemy.getLife());
                            break;
                        case 2:
                            riposte = ((Warrior) player).strengthAttackOfWarrior(enemy);
                            if (riposte) {
                                System.out.println("Riposte ! ");
                            }
                            System.out.println(enemy.getName()+" has been seriously attacked ! \nlives: "+enemy.getLife());
                            break;
                        case 3:
                            special = ((Warrior) player).fireAttackOfWarrior(enemy);
                            if (special.equals("riposte")) {
                                System.out.println("Riposte ! ");
                            }
                            else if (special.equals("crit")) {
                                System.out.println("Critical Hit! ");
                            }
                            System.out.println(enemy.getName()+" has been set on fire ! \nlives: "+enemy.getLife());
                            break;
                        case 4:
                            special = ((Warrior) player).waterAttackOfWarrior(enemy);
                            if (special.equals("riposte")) {
                                System.out.println("Riposte ! ");
                            }
                            else if (special.equals("crit")) {
                                System.out.println("Critical Hit! ");
                            }
                            System.out.println(enemy.getName()+" has been soaked ! \nlives: "+enemy.getLife());
                            break;
                    }
                }
                break;
            case 3:
                // defend yourself
                ((SpellCaster)player).defendYourself();
                System.out.println("We need more defense !! \ndefence: "+player.getDefence());
                break;
            case 4:
                // heal other players
                System.out.println("[1] heal everyone (2 lives - 2 mana) [2] heal one person (4 lives - 1 mana");
                int healChoice = sc.nextInt();
                switch (healChoice) {
                    case 1:
                        ((Healer) player).healAllPlayers(((Healer) player), players);
                        System.out.println("Everyone has been healed");
                        for (Combatant i : players) {
                            System.out.print(i.getName()+" lives: "+i.getLife()+"   ");
                        }
                        System.out.print("\n");
                        break;
                    case 2:
                        for (int j = 0; j < players.size(); j++) {
                            System.out.println("["+(j + 1) + "] " + players.get(j).getName());
                        }
                        System.out.println("Who do you want to heal : ");
                        int healedNumber = sc.nextInt();
                        Hero healedPlayer = (Hero)players.get(healedNumber - 1);
                        ((Healer) player).healOnePlayer((Healer) player, healedPlayer);
                        System.out.println("they have been healed, \nlife: "+healedPlayer.getLife());
                        break;
                }
                break;
        }
    }
}

import RPG_Code.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuConsole {
    public void menu() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Combatant> allPlayers = new ArrayList<>();
        FoodList foods = new FoodList(2,2,2, 2);
        PotionList potions = new PotionList(1,1,1,1);

        boolean isValid;
        int nbPlayer = 0;

        //user proofing
        do {
            System.out.print("number of players : ");
            if(sc.hasNextInt()) {
                nbPlayer = sc.nextInt();
                isValid = true;
                sc.nextLine();
            } else {
                System.out.println("Please input an integer.");
                isValid = false;
                sc.nextLine();
            }
        } while (!isValid);

        // creation de tout les players et type de hero
        for (int i = 0; i < nbPlayer; i++) {
            System.out.println("name of player : ");
            String name = sc.nextLine();

            // creation du hero type
            int heroNumber = 0;

            //user proofing
            do {
                System.out.println("Hero Type number ? 1. Healer, 2. Warrior, 3. Mage or 4. Hunter : ");
                if(sc.hasNextInt()) {
                    heroNumber = sc.nextInt();
                    isValid = (heroNumber <= 4) && (heroNumber >= 1);
                } else {
                    System.out.println("Please input an integer.");
                    isValid = false;
                }
                sc.nextLine();
            } while (!isValid);

            switch (heroNumber) {//pour creer et ajouter chaque hero dans l'equipe
                case 1:
                    Healer healer = new Healer( 3, 2, 2, 20, 0, 7, "no",
                            Weapons.Staff, foods, "no", 0, 0, name);
                    allPlayers.add(healer);
                    break;
                case 2:
                    Warrior warrior = new Warrior(2, 4, 20, 0, "no", Weapons.Sword,
                            foods, potions, "no", 0, 0, name);
                    allPlayers.add(warrior);
                    break;
                case 3:
                    Mage mage = new Mage(2, 2, 20, 7, 0, 3, "no",
                            Weapons.SpellBook, foods, "no", 0, 0, name);
                    allPlayers.add(mage);
                    break;
                case 4:
                    Hunter hunter = new Hunter(2, 3, 20, 7, 0, "no",
                            Weapons.Bow, foods, potions, "no", 0, 0, name);
                    allPlayers.add(hunter);
                    break;
            }
        }
        //lancer le jeu
        Game game = new Game();
        game.gameRun(allPlayers);
    }
}
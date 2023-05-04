import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the RPG 3000 Lite Game, \n\nBefore we begin here are the rules: \n");
        System.out.println("You and your friends (if you have some) have been picked to fights against the terrible orcs\n To win you will have to master different abilities and work together to defeat all the enemies");
        System.out.println("\nPress enter to begin");
        sc.nextLine();

        MenuConsole menuConsole = new MenuConsole();
        menuConsole.menu();
    }
}
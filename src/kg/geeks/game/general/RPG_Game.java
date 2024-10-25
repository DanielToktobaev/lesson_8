package kg.geeks.game.general;

import kg.geeks.game.players.*;

import java.util.Random;

public class RPG_Game {
    public static Random random = new Random();
    private static int roundNumber;

    public static void startGame() {
        Boss boss = new Boss("Black", 1000, 50);
        Warrior warrior1 = new Warrior("Rick", 280, 10);
        Warrior warrior2 = new Warrior("Arthur", 270, 15);
        Magic magic = new Magic("Potter", 260, 10);
        Medic doc = new Medic("Haus", 250, 5, 15);
        Medic assistant = new Medic("Levin", 300, 5, 5);
        Berserk berserk = new Berserk("Valera", 240, 10);

        Hero[] heroes = {warrior1, doc, warrior2, magic, berserk, assistant};

        printStatistics(boss, heroes);

        while (!isGameOver(boss, heroes)) {
            playRound(boss, heroes);
        }
    }

    public static void playRound(Boss boss, Hero[] heroes) {
        roundNumber++;
        boss.chooseDefence();
        boss.attack(heroes);
        for (int i = 0; i < heroes.length; i++) {
            if (boss.getHealth() > 0 && heroes[i].getHealth() > 0
            && boss.getDefence() != heroes[i].getAbility()) {
                heroes[i].attack(boss);

                if (roundNumber % 2 != 0 && heroes[i] == heroes[7]) {
                    heroes[i].applySuperPower(boss, heroes);
                }
                if (heroes[i] != heroes[7]) {
                    heroes[i].applySuperPower(boss, heroes);
                }
            }
        }
        printStatistics(boss, heroes);
    }

    private static boolean isGameOver(Boss boss, Hero[] heroes) {
        if (boss.getHealth() <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    private static void printStatistics(Boss boss, Hero[] heroes) {
        System.out.println("--------------------------");
        System.out.println("ROUND " + roundNumber);
        System.out.println(boss);
        for (Hero hero : heroes) {
            System.out.println(hero);
        }
    }
}

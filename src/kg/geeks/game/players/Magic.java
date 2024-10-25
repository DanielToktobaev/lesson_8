package kg.geeks.game.players;

import kg.geeks.game.general.RPG_Game;

public class Magic extends Hero {
    public Magic(String name, int health, int damage) {
        super(name, health, damage, SuperAbility.BOOST);
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        for (int i = 0; i < heroes.length; i++) {
            int random = RPG_Game.random.nextInt(5);
            if (getHealth() > 0) {
                if (heroes[i].getDamage() != 0) {
                    heroes[i].setDamage(heroes[i].getDamage() + random);
                }
            }
        }
        System.out.println("Magic boosted team");
    }
}

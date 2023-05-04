package RPG_Code;

public enum Weapons {
    //all weapons
    Sword(5),
    Bow(4),
    Arrow(2),
    Staff(2),
    SpellBook(3);

    private final int weaponDamage;
    Weapons(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    }

    //attack de l'arme
    public int getWeaponDamage() {
        return weaponDamage;
    }
}

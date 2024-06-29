import java.util.List;

/**
 * Represents a Fire-type Pokemon with attributes such as name, HP, attack power,
 * and a list of attacks. Provides methods to get and set the Pokemon's
 * attributes and perform attacks on another Pokemon.
 */
public class Fire implements Pokemon {
    private String name;
    private int hp;
    private int attack;
    private List<Attack> attacks;

    /**
     * Constructs a Fire-type Pokemon with the specified name, HP, attack power, and list of attacks.
     *
     * @param name The name of the Pokemon.
     * @param hp The HP (Hit Points) of the Pokemon.
     * @param attack The attack power of the Pokemon.
     * @param attacks A list of attacks that the Pokemon can perform.
     */
    public Fire(String name, int hp, int attack, List<Attack> attacks) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.attacks = attacks;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public List<Attack> getAttacks() {
        return attacks;
    }

    @Override
    public void attack(Pokemon target, Attack attack) {
        int damage = calculateDamage(this, target, attack);
        target.setHp(target.getHp() - damage);
    }

    /**
     * Calculates the advantage coefficient based on the target's type.
     *
     * @param target The target Pokemon.
     * @return The advantage coefficient.
     */
    private float advantageCoefficient(Pokemon target) {
        // If the target is of type Grass, the damage is increased by 20%
        if (target.getAdvantages().equals(this.getDisadvantages()))
            return 1.2f;
        return 1.0f;
    }

    /**
     * Calculates the damage dealt by the attacker to the target using the specified attack.
     *
     * @param attacker The attacking Pokemon.
     * @param target The target Pokemon.
     * @param attack The attack used.
     * @return The calculated damage.
     */
    private int calculateDamage(Pokemon attacker, Pokemon target, Attack attack) {
        return (int) (attacker.getAttack() + attack.getPower() * advantageCoefficient(target));
    }

    @Override
    public String getDescription() {
        return "Fire-type Pokémon are known for their strength and skill, they destroy grass-type Pokémon but have disadvantages against water.";
    }

    @Override
    public String getAdvantages() {
        return "Grass";
    }

    @Override
    public String getDisadvantages() {
        return "Water";
    }
}

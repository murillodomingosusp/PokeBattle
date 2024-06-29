import java.util.List;

/**
 * The Grass class represents a Grass-type Pokémon.
 */
public class Grass implements Pokemon {
    private String name;
    private int hp;
    private int attack;
    private List<Attack> attacks;

    /**
     * Constructs a Grass-type Pokémon with the specified name, hp, attack, and list of attacks.
     *
     * @param name    the name of the Pokémon
     * @param hp      the hit points of the Pokémon
     * @param attack  the attack power of the Pokémon
     * @param attacks the list of attacks of the Pokémon
     */
    public Grass(String name, int hp, int attack, List<Attack> attacks) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.attacks = attacks;
    }

    /**
     * Returns the name of the Pokémon.
     *
     * @return the name of the Pokémon
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the hit points of the Pokémon.
     *
     * @return the hit points of the Pokémon
     */
    @Override
    public int getHp() {
        return hp;
    }

    /**
     * Sets the hit points of the Pokémon.
     *
     * @param hp the new hit points of the Pokémon
     */
    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Returns the attack power of the Pokémon.
     *
     * @return the attack power of the Pokémon
     */
    @Override
    public int getAttack() {
        return attack;
    }

    /**
     * Returns the list of attacks of the Pokémon.
     *
     * @return the list of attacks of the Pokémon
     */
    @Override
    public List<Attack> getAttacks() {
        return attacks;
    }

    /**
     * Attacks the specified target Pokémon with the specified attack.
     *
     * @param target the target Pokémon
     * @param attack the attack used
     */
    @Override
    public void attack(Pokemon target, Attack attack) {
        int damage = calculateDamage(this, target, attack);
        target.setHp(target.getHp() - damage);
    }

    /**
     * Calculates the damage coefficient based on the type advantages and disadvantages.
     *
     * @param target the target Pokémon
     * @return the damage coefficient
     */
    private float advantageCoefficient(Pokemon target) {
        // If the target is of Grass type, the damage is increased by 20%
        if (target.getAdvantages().equals(this.getDisadvantages()))
            return 1.2f;
        return 1.0f;
    }

    /**
     * Calculates the damage dealt by the attacking Pokémon to the target Pokémon using the specified attack.
     *
     * @param attacker the attacking Pokémon
     * @param target   the target Pokémon
     * @param attack   the attack used
     * @return the damage dealt
     */
    private int calculateDamage(Pokemon attacker, Pokemon target, Attack attack) {
        return (int) (attacker.getAttack() + attack.getPower() * advantageCoefficient(target));
    }

    /**
     * Returns a description of the Pokémon.
     *
     * @return a description of the Pokémon
     */
    @Override
    public String getDescription() {
        return "Grass-type Pokémon are known for their strength and ability, they destroy Water-type Pokémon but have disadvantages against Fire.";
    }

    /**
     * Returns the type advantages of the Pokémon.
     *
     * @return the type advantages of the Pokémon
     */
    @Override
    public String getAdvantages() {
        return "Water";
    }

    /**
     * Returns the type disadvantages of the Pokémon.
     *
     * @return the type disadvantages of the Pokémon
     */
    @Override
    public String getDisadvantages() {
        return "Fire";
    }
}

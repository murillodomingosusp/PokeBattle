import java.util.List;

/**
 * Represents a Pokemon with attributes such as name, HP, attack power,
 * and a list of attacks. Provides methods to get and set the Pokemon's
 * HP, retrieve its attacks, and perform attacks on another Pokemon.
 */
public interface Pokemon {

    /**
     * Gets the name of the Pokemon.
     *
     * @return The name of the Pokemon.
     */
    String getName();

    /**
     * Gets the HP (Hit Points) of the Pokemon.
     *
     * @return The HP of the Pokemon.
     */
    int getHp();

    /**
     * Sets the HP (Hit Points) of the Pokemon.
     *
     * @param hp The new HP value to set.
     */
    void setHp(int hp);

    /**
     * Gets the attack power of the Pokemon.
     *
     * @return The attack power of the Pokemon.
     */
    int getAttack();

    /**
     * Gets the list of attacks available to the Pokemon.
     *
     * @return A list of attacks that the Pokemon can perform.
     */
    List<Attack> getAttacks();

    /**
     * Performs an attack on another Pokemon using a specified attack.
     *
     * @param target The target Pokemon to attack.
     * @param attack The attack to use on the target Pokemon.
     */
    void attack(Pokemon target, Attack attack);

    /**
     * Gets a description of the Pokemon.
     *
     * @return A description of the Pokemon.
     */
    String getDescription();

    /**
     * Gets the disadvantages of the Pokemon.
     *
     * @return A string listing the disadvantages of the Pokemon.
     */
    String getDisadvantages();

    /**
     * Gets the advantages of the Pokemon.
     *
     * @return A string listing the advantages of the Pokemon.
     */
    String getAdvantages();
}

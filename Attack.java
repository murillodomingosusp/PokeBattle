/**
 * Represents an attack with attributes such as name, power, and accuracy.
 * Provides methods to retrieve the attack's properties.
 */
public class Attack {
    private String name;
    private int power;
    private int accuracy;

    /**
     * Constructs an attack with the specified name, power, and accuracy.
     *
     * @param name The name of the attack.
     * @param power The power of the attack.
     * @param accuracy The accuracy of the attack.
     */
    public Attack(String name, int power, int accuracy) {
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
    }

    /**
     * Gets the name of the attack.
     *
     * @return The name of the attack.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the power of the attack.
     *
     * @return The power of the attack.
     */
    public int getPower() {
        return power;
    }

    /**
     * Gets the accuracy of the attack.
     *
     * @return The accuracy of the attack.
     */
    public int getAccuracy() {
        return accuracy;
    }

    @Override
    public String toString() {
        return name + " (Power: " + power + ", Accuracy: " + accuracy + ")";
    }
}

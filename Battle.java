import java.util.Random;

/**
 * Represents a battle between two Pokemon. Manages the turn-based combat
 * and handles the actions performed during each turn.
 */
public class Battle {
    private Pokemon player1Pokemon;
    private Pokemon player2Pokemon;
    private Random random;
    private boolean isPlayer1Turn;

    /**
     * Constructs a battle with the specified Pokemon for each player.
     *
     * @param player1Pokemon The Pokemon for player 1.
     * @param player2Pokemon The Pokemon for player 2.
     */
    public Battle(Pokemon player1Pokemon, Pokemon player2Pokemon) {
        this.player1Pokemon = player1Pokemon;
        this.player2Pokemon = player2Pokemon;
        this.random = new Random();
        this.isPlayer1Turn = true; // Player 1 starts
    }

    /**
     * Starts the battle and announces the participating Pokemon.
     */
    public void start() {
        System.out.println("The battle has begun!");
        System.out.println("\nBattle: " + player1Pokemon.getName() + " vs " + player2Pokemon.getName());
    }

    /**
     * Performs a turn using the selected attack.
     *
     * @param selectedAttack The attack selected for this turn.
     */
    public void performTurn(Attack selectedAttack) {
        if (isPlayer1Turn) {
            turn(player1Pokemon, player2Pokemon, selectedAttack);
            if (player2Pokemon.getHp() > 0) {
                isPlayer1Turn = false; // Switches the turn to Player 2
            }
        } else {
            turn(player2Pokemon, player1Pokemon, selectedAttack);
            if (player1Pokemon.getHp() > 0) {
                isPlayer1Turn = true; // Switches the turn to Player 1
            }
        }

        if (player1Pokemon.getHp() <= 0) {
            System.out.println(player1Pokemon.getName() + " has been defeated!");
        }
        if (player2Pokemon.getHp() <= 0) {
            System.out.println(player2Pokemon.getName() + " has been defeated!");
        }
    }

    /**
     * Executes the actions of a turn for the attacking and defending Pokemon.
     *
     * @param attacker The attacking Pokemon.
     * @param defender The defending Pokemon.
     * @param attack The attack used by the attacker.
     */
    private void turn(Pokemon attacker, Pokemon defender, Attack attack) {
        System.out.println(attacker.getName() + " uses " + attack.getName() + "!");

        if (random.nextInt(100) < attack.getAccuracy()) {
            attacker.attack(defender, attack);
            System.out.println(defender.getName() + " now has " + defender.getHp() + " HP.");
        } else {
            System.out.println(attacker.getName() + " missed the attack!");
        }
    }
}

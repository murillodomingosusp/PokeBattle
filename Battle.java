import java.util.Random;

public class Battle {
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Random random;

    /**
     * Constructor of the Battle class.
     *
     * @param pokemon1 Pokémon that will attack.
     * @param pokemon2 Pokémon that will defend.
     */
    public Battle(Pokemon pokemon1, Pokemon pokemon2) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.random = new Random();
    }

    /**
     * Method that starts the battle between two pokémon.
     */
    public void iniciar() {
        System.out.println("A batalha entre " + pokemon1.getNome() + " e " + pokemon2.getNome() + " começou!");

        while (pokemon1.getHp() > 0 && pokemon2.getHp() > 0) {
            turno(pokemon1, pokemon2);
            if (pokemon2.getHp() > 0) {
                turno(pokemon2, pokemon1);
            }
        }

        if (pokemon1.getHp() <= 0) {
            System.out.println(pokemon1.getNome() + " foi derrotado!");
        }
        if (pokemon2.getHp() <= 0) {
            System.out.println(pokemon2.getNome() + " foi derrotado!");
        }
    }

    /**
     * Method that simulates a turn in the battle.
     *
     * @param atacante Pokémon that will attack.
     * @param defensor Pokémon that will defend.
     */
    private void turno(Pokemon atacante, Pokemon defensor) {
        Ataque ataque = atacante.getAtaques().get(random.nextInt(atacante.getAtaques().size()));
        System.out.println(atacante.getNome() + " usa " + ataque.getNome() + "!");

        if (random.nextInt(100) < ataque.getAcuracia()) {
            atacante.atacar(defensor, ataque);
        } else {
            System.out.println(atacante.getNome() + " errou o ataque!");
        }
    }
}

import java.util.Random;

public class Battle {
    private Pokemon pokemonJogador1;
    private Pokemon pokemonJogador2;
    private Random random;
    private boolean isJogador1Turno;

    public Battle(Pokemon pokemonJogador1, Pokemon pokemonJogador2) {
        this.pokemonJogador1 = pokemonJogador1;
        this.pokemonJogador2 = pokemonJogador2;
        this.random = new Random();
        this.isJogador1Turno = true; // Jogador 1 começa
    }

    public void iniciar() {
        System.out.println("A batalha começou!");
        System.out.println("\nBatalha: " + pokemonJogador1.getNome() + " vs " + pokemonJogador2.getNome());
    }

    public void realizarTurno(Ataque ataqueSelecionado) {
        if (isJogador1Turno) {
            turno(pokemonJogador1, pokemonJogador2, ataqueSelecionado);
            if (pokemonJogador2.getHp() > 0) {
                isJogador1Turno = false; // Alterna o turno para Jogador 2
            }
        } else {
            turno(pokemonJogador2, pokemonJogador1, ataqueSelecionado);
            if (pokemonJogador1.getHp() > 0) {
                isJogador1Turno = true; // Alterna o turno para Jogador 1
            }
        }

        if (pokemonJogador1.getHp() <= 0) {
            System.out.println(pokemonJogador1.getNome() + " foi derrotado!");

        }
        if (pokemonJogador2.getHp() <= 0) {
            System.out.println(pokemonJogador2.getNome() + " foi derrotado!");
        }
    }

    private void turno(Pokemon atacante, Pokemon defensor, Ataque ataque) {
        System.out.println(atacante.getNome() + " usa " + ataque.getNome() + "!");

        if (random.nextInt(100) < ataque.getAcuracia()) {
            atacante.atacar(defensor, ataque);
            System.out.println(defensor.getNome() + " agora tem " + defensor.getHp() + " de HP.");
        } else {
            System.out.println(atacante.getNome() + " errou o ataque!");
        }
    }
}

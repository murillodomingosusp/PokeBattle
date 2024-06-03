import java.util.List;

public class Fire implements Pokemon {
    private String nome;
    private int hp;
    private int ataque;
    private int defesa;
    private List<Ataque> ataques;

    /**
     * Fire class constructor
     * @param nome Pokémon name
     * @param hp Pokémon health points
     * @param ataque Pokémon attack value
     * @param defesa Pokémon defense value
     * @param ataques Pokémon attacks list
     */
    public Fire(String nome, int hp, int ataque, int defesa, List<Ataque> ataques) {
        this.nome = nome;
        this.hp = hp;
        this.ataque = ataque;
        this.defesa = defesa;
        this.ataques = ataques;
    }

    /**
     * Returns the Pokémon name
     * @return Pokémon name
     */
    @Override
    public String getNome() {
        return nome;
    }

    /**
     * Returns the Pokémon health points
     * @return Pokémon health points
     */
    @Override
    public int getHp() {
        return hp;
    }

    /**
     * Sets the Pokémon health points
     * @param hp Pokémon health points
     */
    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Returns the Pokémon attack value
     * @return Pokémon attack value
     */
    @Override
    public int getAtaque() {
        return ataque;
    }

    /**
     * Returns the Pokémon defense value
     * @return Pokémon defense value
     */
    @Override
    public int getDefesa() {
        return defesa;
    }

    /**
     * Returns the Pokémon attacks list
     * @return Pokémon attacks list
     */
    @Override
    public List<Ataque> getAtaques() {
        return ataques;
    }

    /**
     * Method that makes a Pokémon attack another Pokémon
     * @param alvo Pokémon that will be attacked
     * @param ataque Attack that will be used
     */
    @Override
    public void atacar(Pokemon alvo, Ataque ataque) {
        int dano = calcularDano(this, alvo, ataque);
        alvo.setHp(alvo.getHp() - dano);
        System.out.println(this.getNome() + " usa " + ataque.getNome() + " em " + alvo.getNome() + " causando " + dano + " de dano.");
    }

    /**
     * Method that calculates the damage caused by an attack
     * @param atacante Pokémon that is attacking
     * @param alvo Pokémon that is being attacked
     * @param ataque Attack that is being used
     * @return Damage caused by the attack
     */
    private int calcularDano(Pokemon atacante, Pokemon alvo, Ataque ataque) {
        return (ataque.getPoder() * atacante.getAtaque() / alvo.getDefesa()) / 2;
    }
}

import java.util.List;

public class Water implements Pokemon {
    private String nome;
    private int hp;
    private int ataque;
    private List<Ataque> ataques;

    public Water(String nome, int hp, int ataque, List<Ataque> ataques) {
        this.nome = nome;
        this.hp = hp;
        this.ataque = ataque;
        this.ataques = ataques;
    }

    @Override
    public String getNome() {
        return nome;
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
    public int getAtaque() {
        return ataque;
    }

    @Override
    public List<Ataque> getAtaques() {
        return ataques;
    }

    @Override
    public void atacar(Pokemon alvo, Ataque ataque) {
        int dano = calcularDano(this, alvo, ataque);
        alvo.setHp(alvo.getHp() - dano);
        System.out.println(this.getNome() + " usa " + ataque.getNome() + " em " + alvo.getNome() + " causando " + dano + " de dano.");
    }

    private float coeficienteVantagem(Pokemon alvo) {
        // Se o alvo for do tipo Grass, o dano é dobrado
        if (alvo.getDesvantagens().equals(this.getVantagens())) {
            return 1.5f;
            // Se o alvo for do tipo Water, o dano é reduzido pela metade
        } else if (alvo.getVantagens().equals(this.getDesvantagens())) {
            return 0.8f;
        }
        return 1.0f;
    }

    private int calcularDano(Pokemon atacante, Pokemon alvo, Ataque ataque) {
        return (int) (atacante.getAtaque() + ataque.getPoder() * coeficienteVantagem(alvo));
    }

    @Override
    public String getDescricao() {
        return "Pokémon do tipo Fogo são conhecidos por sua força e habilidade, destroem pokemons do tipo grama mas tem desvantagens contra água.";
    }

    @Override
    public String getVantagens() {
        return "Fire";
    }

    @Override
    public String getDesvantagens() {
        return "Grass";
    }
}

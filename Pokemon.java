import java.util.List;

public interface Pokemon {
    String getNome();
    int getHp();
    void setHp(int hp);
    int getAtaque();
    List<Ataque> getAtaques();
    void atacar(Pokemon alvo, Ataque ataque);
    String getDescricao();
    String getDesvantagens();
    String getVantagens();
}

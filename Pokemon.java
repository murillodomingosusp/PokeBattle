import java.util.List;

public interface Pokemon {
    String getNome();
    int getHp();
    void setHp(int hp);
    int getAtaque();
    int getDefesa();
    List<Ataque> getAtaques();
    void atacar(Pokemon alvo, Ataque ataque);
}

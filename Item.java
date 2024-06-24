import java.util.Arrays;
import java.util.List;

public class Item {
    private String nome;
    private String descricao;

    public static final Item POTION = new Item("Potion", "Recupera 20 HP.");
    public static final Item SUPER_POTION = new Item("Super Potion", "Recupera 50 HP.");
    public static final Item REVIVE = new Item("Revive", "Revive um Pokémon desmaiado com metade do HP.");

    public static final List<Item> TODOS_ITENS = Arrays.asList(POTION, SUPER_POTION, REVIVE);

    private Item(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return nome;
    }
}

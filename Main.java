import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Ataque chama = new Ataque("Chama", 40, 100);
        Ataque lancachamas = new Ataque("Lança-Chamas", 90, 85);

        Pokemon charmander = new Fire("Charmander", 100, 52, 43, Arrays.asList(chama, lancachamas));
        Pokemon vulpix = new Fire("Vulpix", 90, 41, 40, Arrays.asList(chama, lancachamas));

        Battle batalha = new Battle(charmander, vulpix);
        batalha.iniciar();
    }
}

public class Ataque {
    private String nome;
    private int poder;
    private int acuracia;

    public Ataque(String nome, int poder, int acuracia) {
        this.nome = nome;
        this.poder = poder;
        this.acuracia = acuracia;
    }

    public String getNome() {
        return nome;
    }

    public int getPoder() {
        return poder;
    }

    public int getAcuracia() {
        return acuracia;
    }

    @Override
    public String toString() {
        return nome + " (Poder: " + poder + ", Acurácia: " + acuracia + ")";
    }
}

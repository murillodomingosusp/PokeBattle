public class Ataque {
    private String nome;
    private int poder;
    private int acuracia;

    //remake in english
    /**
     * Attack class constructor
     * @param nome Attack name
     * @param poder Attack power
     * @param acuracia Attack accuracy
     */
    public Ataque(String nome, int poder, int acuracia) {
        this.nome = nome;
        this.poder = poder;
        this.acuracia = acuracia;
    }

    /**
     * Returns the attack name
     * @return Attack name
     */
    public String getNome() {
        return nome;
    }

    /**
     * Returns the attack power
     * @return Attack power
     */
    public int getPoder() {
        return poder;
    }

    /**
     * Returns the attack accuracy
     * @return Attack accuracy
     */
    public int getAcuracia() {
        return acuracia;
    }
}

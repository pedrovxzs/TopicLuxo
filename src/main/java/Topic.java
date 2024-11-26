public class Topic {

    public Topic(int capacidade, int qtdPrioritatios) {
    }

    public int getNumeroAssentosPrioritarios() {
        return 0;
    }
    public int getNumeroAssentosNormais() {
        return 0;
    }

    public Passageiro getPassageiroAssentoNormal(int lugar) {
        return null;
    }

    public Passageiro getPassageiroAssentoPrioritario(int lugar) {
        return null;
    }

    public int getVagas() {
        return -1;
    }

    public boolean subir(Passageiro passageiro) {
        return false;
    }
    public boolean descer(String nome) {
        return true;
    }
}
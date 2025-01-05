public class Passageiro {

    private String id;

    private int idade;

    public Passageiro(String id, int idade) {
        this.id = id;
        this.idade = idade;
    }
    public boolean ePrioritario() {
        return idade >= 65;
    }

    String getNome() {
        return id;
    }

    public int getIdade() {
        return idade;
    }

    @Override
    public String toString() {
        return id + ":" + idade;
    }
}

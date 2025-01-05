import java.util.Arrays;

public class Topic {
    private final String[] assentosPreferenciais;
    private final String[] assentosNormais;
    private final int capacidadeTotal;
    private final int qtdPreferenciais;
    private final int qtdNormais;

    public Topic(int capacidadeTotal, int qtdPreferenciais) {
        this.capacidadeTotal = capacidadeTotal;
        this.qtdPreferenciais = qtdPreferenciais;
        this.qtdNormais = capacidadeTotal - qtdPreferenciais;

        this.assentosPreferenciais = new String[qtdPreferenciais];
        this.assentosNormais = new String[qtdNormais];

        inicializarAssentos();
    }

    private void inicializarAssentos() {
        for (int i = 0; i < qtdPreferenciais; i++) {
            assentosPreferenciais[i] = "@";
        }
        for (int i = 0; i < qtdNormais; i++) {
            assentosNormais[i] = "=";
        }
    }

    public Passageiro getPassageiroAssentoNormal(int lugar) {
        if (lugar >= qtdNormais || assentosNormais[lugar].equals("=")) {
            return null;
        }
        return extrairPassageiro(assentosNormais[lugar], "=");
    }

    public Passageiro getPassageiroAssentoPrioritario(int lugar) {
        if (lugar >= qtdPreferenciais) {
            return null;
        }
        if (assentosPreferenciais[lugar].equals("@")) {
            return new Passageiro("", 0);
        }
        return extrairPassageiro(assentosPreferenciais[lugar], "@");
    }

    private Passageiro extrairPassageiro(String assentoInfo, String separador) {
        String[] dadosPassageiro = assentoInfo.split("[" + separador + ":]");
        return new Passageiro(dadosPassageiro[1], Integer.parseInt(dadosPassageiro[2]));
    }

    public int getNumeroAssentosPrioritarios() {
        return qtdPreferenciais;
    }

    public int getNumeroAssentosNormais() {
        return qtdNormais;
    }

    public int getVagas() {
        int vagas = 0;
        for (int i = 0; i < qtdPreferenciais; i++) {
            if (assentosPreferenciais[i].equals("@")) {
                vagas++;
            }
        }
        for (int i = 0; i < qtdNormais; i++) {
            if (assentosNormais[i].equals("=")) {
                vagas++;
            }
        }
        return vagas;
    }

    private int encontrarVagaPreferencial() {
        for (int i = 0; i < qtdPreferenciais; i++) {
            if (assentosPreferenciais[i].equals("@")) {
                return i;
            }
        }
        return -1;
    }

    private int encontrarVagaNormal() {
        for (int i = 0; i < qtdNormais; i++) {
            if (assentosNormais[i].equals("=")) {
                return i;
            }
        }
        return -1;
    }

    private boolean passageiroJaEstaNaTopic(String nome) {
        for (int i = 0; i < qtdPreferenciais; i++) {
            if (extrairNome(assentosPreferenciais[i], "@").equals(nome)) {
                return true;
            }
        }
        for (int i = 0; i < qtdNormais; i++) {
            if (extrairNome(assentosNormais[i], "=").equals(nome)) {
                return true;
            }
        }
        return false;
    }

    private String extrairNome(String assento, String separador) {
        return assento.replaceAll("[" + separador + ":0-9]", "");
    }

    public boolean subir(Passageiro passageiro) {
        if (passageiroJaEstaNaTopic(passageiro.getNome())) {
            System.out.println("Passageiro já está na topic.");
            return false;
        }

        if (getVagas() == 0) {
            System.out.println("Topic lotada.");
            return false;
        }

        int vagaPreferencial = encontrarVagaPreferencial();
        int vagaNormal = encontrarVagaNormal();
        String infoPassageiro = passageiro.getNome() + ":" + passageiro.getIdade();

        if (passageiro.ePrioritario()) {
            if (vagaPreferencial >= 0) {
                assentosPreferenciais[vagaPreferencial] = "@" + infoPassageiro;
                return true;
            } else if (vagaNormal >= 0) {
                assentosNormais[vagaNormal] = "=" + infoPassageiro;
                return true;
            }
        } else {
            if (vagaNormal >= 0) {
                assentosNormais[vagaNormal] = "=" + infoPassageiro;
                return true;
            } else if (vagaPreferencial >= 0) {
                assentosPreferenciais[vagaPreferencial] = "@" + infoPassageiro;
                return true;
            }
        }
        return false;
    }

    public boolean descer(String nome) {
        for (int i = 0; i < qtdPreferenciais; i++) {
            if (extrairNome(assentosPreferenciais[i], "@").equals(nome)) {
                assentosPreferenciais[i] = "@";
                return true;
            }
        }

        for (int i = 0; i < qtdNormais; i++) {
            if (extrairNome(assentosNormais[i], "=").equals(nome)) {
                assentosNormais[i] = "=";
                return true;
            }
        }

        System.out.println("Passageiro não está na topic");
        return false;
    }

    @Override
    public String toString() {
        String regex = "[\\[\\],]";
        
        return "[" +
                Arrays.toString(assentosPreferenciais).replaceAll(regex, "") + " " +
                Arrays.toString(assentosNormais).replaceAll(regex, "") + " " +
                "]";
    }
}

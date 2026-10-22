// Atividade 3 - Classe Campeonato (Time)
public class Time {

    private String nome;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int pontos;

    public Time(String nome) {
        this.nome = nome;
        this.vitorias = 0;
        this.empates = 0;
        this.derrotas = 0;
        this.pontos = 0;
    }

    // Cada método atualiza o contador do resultado e os pontos ao mesmo tempo,
    // então os pontos nunca ficam "desencontrados" dos resultados.
    public void registrarVitoria() {
        vitorias++;
        pontos += 3;
    }

    public void registrarEmpate() {
        empates++;
        pontos += 1;
    }

    public void registrarDerrota() {
        derrotas++;
        // derrota vale 0 pontos, então os pontos não mudam
    }

    public int getPontos() {
        return pontos;
    }

    public String getNome() {
        return nome;
    }

    public void exibirEstatisticas() {
        System.out.println("---- " + nome + " ----");
        System.out.println("Vitórias: " + vitorias);
        System.out.println("Empates: " + empates);
        System.out.println("Derrotas: " + derrotas);
        System.out.println("Pontos: " + pontos);
    }
}

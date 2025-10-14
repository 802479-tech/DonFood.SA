
public class Garcom {
    private int idGarcom;
    private String nome;
    private String disponibilidade;

    public Garcom() {
    }

    public Garcom(int idGarcom, String nome, String disponibilidade) {
        this.idGarcom = idGarcom;
        this.nome = nome;
        this.disponibilidade = disponibilidade;
    }

    public int getIdGarcom() {
        return idGarcom;
    }

    public void setIdGarcom(int idGarcom) {
        this.idGarcom = idGarcom;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}


public class Administrador {
    private int idAdministrador;
    private String nome;
    private String senha;
    private String login;

    public Administrador() {
    }

    public Administrador(int idAdministrador, String nome, String senha, String login) {
        this.idAdministrador = idAdministrador;
        this.nome = nome;
        this.senha = senha;
        this.login = login;
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

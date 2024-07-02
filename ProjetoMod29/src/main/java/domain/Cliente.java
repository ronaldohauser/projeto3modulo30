package domain;

public class Cliente {

    private Long id;
    private String codigo;
    private String nome;
    private String email; // Novo campo adicionado
    private String cpf;

    public Cliente() {

    }

    public Cliente(Long id, String codigo, String nome, String email, String cpf) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

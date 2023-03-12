package entidades;

public class Usuario {
    private int codigo;
    private String email;
    private String senha;

    public Usuario() {
    }
    
    public Usuario(int codigo, String email, String senha) {
        this.codigo = codigo;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
    



    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }


    public int getCodigo() {
        return codigo;
    }



    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public void setSenha(String senha) {
        this.senha = senha;
    }
    

    
}
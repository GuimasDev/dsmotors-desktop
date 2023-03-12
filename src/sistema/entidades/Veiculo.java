package entidades;

public class Veiculo {
    private int codigo;
    private String modelo;
    private String ano;
    private String placa;
    private Usuario usuario;

    public Veiculo() {
    }

    public Veiculo(int codigo, String modelo, String ano, String placa, Usuario usuario) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.usuario = usuario;
    }

    public Veiculo(String modelo, String ano, String placa, Usuario usuario) {
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.usuario = usuario;
    }

    

    public int getCodigo() {
        return codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public String getAno() {
        return ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}

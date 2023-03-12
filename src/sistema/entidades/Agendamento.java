package entidades;

import java.sql.Date;

public class Agendamento {
    private int codigo;
    private Date data;
    private String objetivo;
    private Veiculo veiculo;

    public Agendamento(int codigo, Date data, String objetivo, Veiculo veiculo) {
        this.codigo = codigo;
        this.data = data;
        this.objetivo = objetivo;
        this.veiculo = veiculo;
    }

    public Agendamento() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(String data) {
        this.data = Date.valueOf(data);
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

}

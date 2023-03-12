package repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entidades.Agendamento;
import entidades.Veiculo;

public class AgendamentoDao extends Dao {

    private final static String INSERT_SQL = "insert into agendamento (data,objetivo,id_veiculo) values (?, ?, ?)";

    private final static String CONSULTAR_POR_ID_SQL = "select * from agendamento where codigo = ?";

    private final static String CONSULTAR_POR_VEICULO = "select * from agendamento where id_veiculo = ?";

    private final static String CONSULTAR_TODOS_SQL = "select * from agendamento";

    private final static String EXCLUIR_SQL = "delete from agendamento where codigo = ?";

    private final static String ALTERAR_SQL = " update agendamento " +
            " set data = ?, " +
            "     objetivo = ?, " +
            "     id_veiculo = ? " +
            " where codigo = ? ";

    public void alterar(Agendamento a) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = conn.prepareStatement(ALTERAR_SQL);
        ps.setDate(1, a.getData());
        ps.setString(2, a.getObjetivo());
        ps.setInt(3, a.getVeiculo().getCodigo());
        ps.setInt(4, a.getCodigo());
        ps.execute();
    }

    public void excluir(int id) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = conn.prepareStatement(EXCLUIR_SQL);
        ps.setInt(1, id);
        ps.execute();
    }

    public List<Agendamento> consultarTodos() throws ClassNotFoundException, SQLException {
        List<Agendamento> petList = new ArrayList<>();

        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        ps = conn.prepareStatement(CONSULTAR_TODOS_SQL);
        rs = ps.executeQuery();

        while (rs.next()) {
            Agendamento agendamento = new Agendamento();
            agendamento.setCodigo(rs.getInt("codigo"));
            agendamento.setData(rs.getString("data"));
            agendamento.setObjetivo(rs.getString("objetivo"));

            int id_veiculo = rs.getInt("id_veiculo");
            VeiculoDao veiculoDao = new VeiculoDao();
            for (Veiculo veiculo : veiculoDao.consultarTodos()) {
                if (veiculo.getCodigo() == id_veiculo) {
                    agendamento.setVeiculo(veiculo);
                }
            }

            petList.add(agendamento);
        }

        return petList;
    }

    public Agendamento consultarPorId(int id) throws ClassNotFoundException, SQLException {
        Agendamento agendamento = null;

        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        ps = conn.prepareStatement(CONSULTAR_POR_ID_SQL);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        if (rs.next()) {
            agendamento = new Agendamento();
            agendamento.setCodigo(rs.getInt("codigo"));
            agendamento.setData(rs.getString("data"));
            agendamento.setObjetivo(rs.getString("objetivo"));

            int id_veiculo = rs.getInt("id_veiculo");
            VeiculoDao veiculoDao = new VeiculoDao();
            for (Veiculo veiculo : veiculoDao.consultarTodos()) {
                if (veiculo.getCodigo() == id_veiculo) {
                    agendamento.setVeiculo(veiculo);
                }
            }

        }

        return agendamento;
    }

    public List<Agendamento> consultarPorVeiculo(int id) throws ClassNotFoundException, SQLException {
        List<Agendamento> petList = new ArrayList<>();

        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        ps = conn.prepareStatement(CONSULTAR_POR_VEICULO);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.next()) {
            Agendamento agendamento = new Agendamento();
            agendamento.setCodigo(rs.getInt("codigo"));
            agendamento.setData(rs.getString("data"));
            agendamento.setObjetivo(rs.getString("objetivo"));

            int id_veiculo = rs.getInt("id_veiculo");
            VeiculoDao veiculoDao = new VeiculoDao();
            for (Veiculo veiculo : veiculoDao.consultarTodos()) {
                if (veiculo.getCodigo() == id_veiculo) {
                    agendamento.setVeiculo(veiculo);
                }
            }

            petList.add(agendamento);
        }

        return petList;
    }

    public void inserir(Agendamento a) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
        ps.setDate(1, a.getData());
        ps.setString(2, a.getObjetivo());
        ps.setInt(3, a.getVeiculo().getCodigo());
        ps.execute();
    }

}

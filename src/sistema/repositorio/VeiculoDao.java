package repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entidades.Usuario;
import entidades.Veiculo;

public class VeiculoDao extends Dao{

    private final static String INSERT_SQL = "insert into veiculo (modelo,ano,placa,id_usuario) values (?, ?, ?, ?)";

    private final static String CONSULTAR_POR_ID_SQL = "select * from veiculo where codigo = ?";

    private final static String CONSULTAR_POR_USUARIO = "select * from veiculo where id_usuario = ?";

    private final static String CONSULTAR_POR_PLACA = "select * from veiculo where placa = ?";

    private final static String CONSULTAR_TODOS_SQL = "select * from veiculo";

    private final static String EXCLUIR_SQL = "delete from veiculo where codigo = ?";

    private final static String ALTERAR_SQL = " update veiculo " +
            " set modelo = ?, " +
            "     ano = ?, " +
            "     placa = ?, " +
            "     id_usuario = ? " +
            " where codigo = ? ";

    public void alterar(Veiculo v) throws ClassNotFoundException, SQLException{

        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = conn.prepareStatement(ALTERAR_SQL);
        ps.setString(1, v.getModelo());
        ps.setString(2, v.getAno());
        ps.setString(3, v.getPlaca());
        ps.setInt(4, v.getUsuario().getCodigo());
        ps.setInt(5, v.getCodigo());
        ps.execute();
    }

    public void excluir(int id) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = conn.prepareStatement(EXCLUIR_SQL);
        ps.setInt(1, id);
        ps.execute();
    }

    public List<Veiculo> consultarTodos() throws ClassNotFoundException, SQLException {
        List<Veiculo> petList = new ArrayList<>();

        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        ps = conn.prepareStatement(CONSULTAR_TODOS_SQL);
        rs = ps.executeQuery();

        while (rs.next()) {
            Veiculo veiculo = new Veiculo();
            veiculo.setCodigo(rs.getInt("codigo"));
            veiculo.setModelo(rs.getString("modelo"));
            veiculo.setAno(rs.getString("ano"));
            veiculo.setPlaca(rs.getString("placa"));

            int id_usuario = rs.getInt("id_usuario");
            UsuarioDao usuarioDao = new UsuarioDao();
            for (Usuario usuario : usuarioDao.consultarTodos()) {
                if (usuario.getCodigo() == id_usuario) {
                    veiculo.setUsuario(usuario);
                }
            }

            petList.add(veiculo);
        }
        return petList;
    }

    public Veiculo consultarPorId(int id) throws ClassNotFoundException, SQLException {
        Veiculo veiculo = null;

        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        ps = conn.prepareStatement(CONSULTAR_POR_ID_SQL);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        if (rs.next()) {
            veiculo = new Veiculo();
            veiculo.setCodigo(rs.getInt("codigo"));
            veiculo.setModelo(rs.getString("modelo"));
            veiculo.setAno(rs.getString("ano"));
            veiculo.setPlaca(rs.getString("placa"));

            int id_usuario = rs.getInt("id_usuario");
            UsuarioDao usuarioDao = new UsuarioDao();
            for (Usuario usuario : usuarioDao.consultarTodos()) {
                if (usuario.getCodigo() == id_usuario) {
                    veiculo.setUsuario(usuario);
                }
            }

        }

        return veiculo;
    }

    public List<Veiculo> consultarPorUsuario(int id) throws ClassNotFoundException, SQLException {
        List<Veiculo> petList = new ArrayList<>();

        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        ps = conn.prepareStatement(CONSULTAR_POR_USUARIO);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.next()) {
            Veiculo veiculo = new Veiculo();
            veiculo.setCodigo(rs.getInt("codigo"));
            veiculo.setModelo(rs.getString("modelo"));
            veiculo.setAno(rs.getString("ano"));
            veiculo.setPlaca(rs.getString("placa"));

            int id_usuario = rs.getInt("id_usuario");
            UsuarioDao usuarioDao = new UsuarioDao();
            for (Usuario usuario : usuarioDao.consultarTodos()) {
                if (usuario.getCodigo() == id_usuario) {
                    veiculo.setUsuario(usuario);
                }
            }

            petList.add(veiculo);
        }

        return petList;
    }

    public Veiculo consultarPorPlaca(String placa) throws ClassNotFoundException, SQLException {
        Veiculo veiculo = null;

        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        ps = conn.prepareStatement(CONSULTAR_POR_PLACA);
        ps.setString(1, placa);
        rs = ps.executeQuery();

        if (rs.next()) {
            veiculo = new Veiculo();
            veiculo.setCodigo(rs.getInt("codigo"));
            veiculo.setModelo(rs.getString("modelo"));
            veiculo.setAno(rs.getString("ano"));
            veiculo.setPlaca(rs.getString("placa"));

            int id_usuario = rs.getInt("id_usuario");
            UsuarioDao usuarioDao = new UsuarioDao();
            for (Usuario usuario : usuarioDao.consultarTodos()) {
                if (usuario.getCodigo() == id_usuario) {
                    veiculo.setUsuario(usuario);
                }
            }

        }

        return veiculo;
    }

    public void inserir(Veiculo v) throws ClassNotFoundException, SQLException{
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, v.getModelo());
        ps.setString(2, v.getAno());
        ps.setString(3, v.getPlaca());
        ps.setInt(4, v.getUsuario().getCodigo());
        ps.execute();
    }
}

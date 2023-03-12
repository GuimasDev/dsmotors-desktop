package repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entidades.Usuario;

public class UsuarioDao extends Dao {

    private final static String INSERT_SQL = "insert into usuario (email, senha) values (?, ?)";

    private final static String CONSULTAR_POR_ID_SQL = "select * from usuario where codigo = ?";

    private final static String CONSULTAR_TODOS_SQL = "select * from usuario";

    private final static String EXCLUIR_SQL = "delete from usuario where codigo = ?";

    private final static String ALTERAR_SQL = " update usuario " +
            " set email = ?, " +
            "     senha = ? " +
            " where codigo = ? ";

    public void alterar(Usuario u) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = conn.prepareStatement(ALTERAR_SQL);
        ps.setString(1, u.getEmail());
        ps.setString(2, u.getSenha());
        ps.execute();

    }

    public void excluir(int id) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = conn.prepareStatement(EXCLUIR_SQL);
        ps.setInt(1, id);
        ps.execute();

    }

    public List<Usuario> consultarTodos() throws ClassNotFoundException, SQLException {
        List<Usuario> petList = new ArrayList<>();

        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        ps = conn.prepareStatement(CONSULTAR_TODOS_SQL);
        rs = ps.executeQuery();

        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setCodigo(rs.getInt("codigo"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));

            petList.add(usuario);
        }

        return petList;
    }

    public Usuario consultarPorId(int id) throws ClassNotFoundException, SQLException {
        Usuario usuario = null;

        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        ps = conn.prepareStatement(CONSULTAR_POR_ID_SQL);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        if (rs.next()) {
            usuario = new Usuario();
            usuario.setCodigo(rs.getInt("codigo"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
        }

        return usuario;
    }

    public int inserir(Usuario u) throws ClassNotFoundException, SQLException {
        int ret = 0;

        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, u.getEmail());
        ps.setString(2, u.getSenha());
        ps.execute();

        rs = ps.getGeneratedKeys();
        if (rs.next()) {
            ret = rs.getInt(1);
        }

        return ret;
    }

    public Usuario login(String email, String senha) throws ClassNotFoundException, SQLException{
        for (Usuario usuario : consultarTodos()) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

}

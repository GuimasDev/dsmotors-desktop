package controle;

import java.sql.SQLException;
import java.util.List;

import entidades.Usuario;
import repositorio.UsuarioDao;
import view.JanelaAviso;
import view.JanelaCriarConta;
import view.JanelaLogin;

public class ControleUsuario {
    private UsuarioDao dao;
    private JanelaLogin janela;

    public ControleUsuario() {
        dao = new UsuarioDao();
    }

    public Usuario fazerLogin(String email, String senha) {
        try {
            if (email != null && senha != null) {
                Usuario usuario = dao.login(email, senha);
                if (usuario != null) {
                    new JanelaAviso("Usuario logado com sucesso!!!");
                    return usuario;
                }
            }
        } catch (NullPointerException e) {
            new JanelaAviso("Email/Senha inválidos:" + e.getMessage());
        } catch (SQLException e) {
            new JanelaAviso("Erro ao consultar no banco de dados." + e.getMessage());
        } catch (ClassNotFoundException e) {
            new JanelaAviso("Erro ao importar lib mysql/connector: " + e.getMessage());
        }
        return null;
    }

    public Usuario login() {
        try {
            boolean flag = false;
            do {
                JanelaLogin login = new JanelaLogin();

                if (login.getLogin() != null) {
                    flag = false;
                    Usuario u = fazerLogin(login.getLogin().getEmail(), login.getLogin().getSenha());
                    if (u != null)
                        return u;
                    else
                        flag = true;

                } else if (login.getBotao() != null && login.getBotao().equals("Criar")) {
                    System.out.println("Abrir janela criar conta");
                    inserir();
                    flag = true;
                }
            } while (flag);
        } catch (NullPointerException e) {
            new JanelaAviso("Falha no login: Usuario inválido:" + e.getMessage());
        }
        return null;
    }

    public void inserir(Usuario u) {
        try {
            if (u != null && dao.inserir(u) > 0) {
                new JanelaAviso("Usuario cadastrado com sucesso!!!");
            } else
                new JanelaAviso("Falha em cadastrar usuario!!!");
        } catch (NullPointerException e) {
            new JanelaAviso("Usuario inválido:" + e.getMessage());
        } catch (SQLException e) {
            new JanelaAviso("Erro ao inserir no banco de dados." + e.getMessage());
        } catch (ClassNotFoundException e) {
            new JanelaAviso("Erro ao importar lib mysql/connector: " + e.getMessage());
        }
    }

    public void inserir() {
        try {
            JanelaCriarConta janelaCriarConta = new JanelaCriarConta();
            if (janelaCriarConta.getBotao() != null && janelaCriarConta.getBotao().equals("Criar")) {
                Usuario u = janelaCriarConta.getUsuario();

                inserir(u);
            }
        } catch (NullPointerException e) {
            new JanelaAviso("Usuario inválido:" + e.getMessage());
        }
    }

    public Usuario listar(int id) {
        try {
            return dao.consultarPorId(id);
        } catch (SQLException e) {
            new JanelaAviso("Erro ao consultar no banco de dados." + e.getMessage());
        } catch (ClassNotFoundException e) {
            new JanelaAviso("Erro ao importar lib mysql/connector: " + e.getMessage());
        }
        return null;
    }

    public void listar() {
        try {
            List<Usuario> lista = dao.consultarTodos();
            if (lista != null) {
                if (janela != null) {
                    janela.dispose();
                }
                janela = new JanelaLogin(lista);
            } else
                new JanelaAviso("Não existem agendamentos realizados");
        } catch (SQLException e) {
            new JanelaAviso("Erro ao consultar no banco de dados." + e.getMessage());
        } catch (ClassNotFoundException e) {
            new JanelaAviso("Erro ao importar lib mysql/connector: " + e.getMessage());
        }
    }

}

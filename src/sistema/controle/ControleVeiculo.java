package controle;

import java.sql.SQLException;
import java.util.List;

import entidades.Usuario;
import entidades.Veiculo;
import repositorio.VeiculoDao;
import view.JanelaAviso;
import view.JanelaConfirmar;
import view.JanelaVeiculo;

public class ControleVeiculo {
    private VeiculoDao dao;
    private JanelaVeiculo janela;

    public ControleVeiculo() {
        dao = new VeiculoDao();
    }

    public void inserir(Usuario usuario) {
        try {
            janela = new JanelaVeiculo(dao.consultarPorUsuario(usuario.getCodigo()), usuario);
            Veiculo v = janela.getVeiculo();
            try {
                dao.inserir(v);
            } catch (NullPointerException e) {
                new JanelaAviso("Falha em cadastrar veiculo, veiculo inválido:" + e.getMessage());
            } catch (SQLException e) {
                new JanelaAviso("Erro ao inserir no banco de dados." + e.getMessage());
            }
        } catch (SQLException e) {
            new JanelaAviso("Erro ao consultar no banco de dados." + e.getMessage());
            return;
        } catch (ClassNotFoundException e) {
            new JanelaAviso("Erro ao importar lib mysql/connector: " + e.getMessage());
        }
    }

    public void inserir(Veiculo v) {
        try {
            dao.inserir(v);
        } catch (NullPointerException e) {
            new JanelaAviso("Falha em cadastrar veiculo, veiculo inválido:" + e.getMessage());
        } catch (SQLException e) {
            new JanelaAviso("Erro ao inserir no banco de dados." + e.getMessage());
        } catch (ClassNotFoundException e) {
            new JanelaAviso("Erro ao importar lib mysql/connector: " + e.getMessage());
        }
    }

    public void alterar(Veiculo v) {
        int confirm = JanelaConfirmar.confirm("Tem certeza que você deseja alterar esse item?");
        if (confirm == 0) {
            try {
                dao.alterar(v);
            } catch (NullPointerException e) {
                new JanelaAviso("Falha em alterar veiculo, veiculo inválido:" + e.getMessage());
            } catch (SQLException e) {
                new JanelaAviso("Erro ao alterar no banco de dados." + e.getMessage());
            } catch (ClassNotFoundException e) {
                new JanelaAviso("Erro ao importar lib mysql/connector: " + e.getMessage());
            }
        }
    }


    public void excluir(Veiculo v) {
        int confirm = JanelaConfirmar.confirm("Tem certeza que você deseja excluir esse item?");
        if (confirm == 0) {
            try {
                dao.excluir(v.getCodigo());
            } catch (NullPointerException e) {
                new JanelaAviso("Falha em cadastrar veiculo, veiculo inválido:" + e.getMessage());
            } catch (SQLException e) {
                new JanelaAviso("Erro ao inserir no banco de dados." + e.getMessage());
            } catch (ClassNotFoundException e) {
                new JanelaAviso("Erro ao importar lib mysql/connector: " + e.getMessage());
            }
        }
    }

    public Veiculo listar(int id) {
        try {
            return dao.consultarPorId(id);
        } catch (SQLException e) {
            new JanelaAviso("Erro ao consultar no banco de dados." + e.getMessage());
        } catch (ClassNotFoundException e) {
            new JanelaAviso("Erro ao importar lib mysql/connector: " + e.getMessage());
        }
        
        return null;
    }

    public void listar(Usuario usuario) {
        boolean editar = false;
        Veiculo veiculoEdit = null;
        try {
            do {
                List<Veiculo> lista = dao.consultarPorUsuario(usuario.getCodigo());
                if (lista != null) {
                    if (janela != null) {
                        janela.dispose();
                    }
                    janela = (editar) ? new JanelaVeiculo(lista, usuario, veiculoEdit) : new JanelaVeiculo(lista, usuario);
                    editar = false;

                    if (janela.getBotao() != null) {
                        if (janela.getBotao().equals("Add")) {
                            inserir(janela.getVeiculo());
                        } else if (janela.getBotao().equals("Editar")) {
                            veiculoEdit = janela.getVeiculo();
                            editar = true;
                        } else if (janela.getBotao().equals("EditarLinha")) {
                            alterar(janela.getVeiculo());
                        } else if (janela.getBotao().equals("Excluir")) {
                            excluir(janela.getVeiculo());
                        }
                    }
                } else
                    new JanelaAviso("Não existem veiculos cadastrados");
            } while (janela.getBotao() != null && !janela.getBotao().equals("Fechar"));

        } catch (NullPointerException e) {
            new JanelaAviso("Falha na listagem, usuario inválido:" + e.getMessage());
        } catch (SQLException e) {
            new JanelaAviso("Erro ao consultar no banco de dados." + e.getMessage());
        } catch (ClassNotFoundException e) {
            new JanelaAviso("Erro ao importar lib mysql/connector: " + e.getMessage());
        }
    }

    public List<Veiculo> getLista(int id) {
        try {
            return dao.consultarPorUsuario(id);
        } catch (SQLException e) {
            new JanelaAviso("Erro ao consultar no banco de dados." + e.getMessage());

        } catch (ClassNotFoundException e) {
            new JanelaAviso("Erro ao importar lib mysql/connector: " + e.getMessage());
        }
        return null;
    }

}

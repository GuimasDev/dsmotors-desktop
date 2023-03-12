package controle;

import java.sql.SQLException;
import java.util.List;

import entidades.Agendamento;
import entidades.Veiculo;
import repositorio.AgendamentoDao;
import view.JanelaAgendamento;
import view.JanelaAviso;
import view.JanelaConfirmar;

public class ControleAgendamento {
    private AgendamentoDao dao;
    private JanelaAgendamento janela;

    public ControleAgendamento() {
        dao = new AgendamentoDao();
    }

    public void inserir(List<Veiculo> veiculos) {
        try {
            janela = new JanelaAgendamento(dao.consultarTodos(), veiculos);
            Agendamento a = janela.getAgendamento();
            try {
                dao.inserir(a);
            } catch (NullPointerException e) {
                new JanelaAviso("Agendamento inválido:" + e.getMessage());
            } catch (SQLException e) {
                new JanelaAviso("Erro ao inserir no banco de dados." + e.getMessage());
            }

        } catch (SQLException e) {
            new JanelaAviso("Erro ao inserir no banco de dados." + e.getMessage());
        } catch (ClassNotFoundException e) {
            new JanelaAviso("Erro ao importar lib mysql/connector: " + e.getMessage());
        }
    }

    public void inserir(Agendamento a) {
        try{
            dao.inserir(a);
        } catch (NullPointerException e) {
            new JanelaAviso("Agendamento inválido:" + e.getMessage());
        } catch (SQLException e) {
            new JanelaAviso("Erro ao inserir no banco de dados." + e.getMessage());
        } catch (ClassNotFoundException e) {
            new JanelaAviso("Erro ao importar lib mysql/connector: " + e.getMessage());
        }
    }

    public void alterar(Agendamento a) {
        int confirm = JanelaConfirmar.confirm("Tem certeza que você deseja alterar esse item?");
        if (confirm == 0) {
            try {
                dao.alterar(a);
            } catch (NullPointerException e) {
                new JanelaAviso("Agendamento inválido:" + e.getMessage());
            } catch (SQLException e) {
                new JanelaAviso("Erro ao alterar no banco de dados." + e.getMessage());
            } catch (ClassNotFoundException e) {
                new JanelaAviso("Erro ao importar lib mysql/connector: " + e.getMessage());
            }
        }
    }

    public void excluir(Agendamento a) {
        int confirm = JanelaConfirmar.confirm("Tem certeza que você deseja excluir esse item?");
        if (confirm == 0) {
            try {
                dao.excluir(a.getCodigo());
            } catch (NullPointerException e) {
                new JanelaAviso("Agendamento inválido:" + e.getMessage());
            } catch (SQLException e) {
                new JanelaAviso("Erro ao excluir no banco de dados." + e.getMessage());
            } catch (ClassNotFoundException e) {
                new JanelaAviso("Erro ao importar lib mysql/connector: " + e.getMessage());
            }
        }
    }

    public Agendamento listar(int id) {
        try {
            return dao.consultarPorId(id);
        } catch (SQLException e) {
            new JanelaAviso("Erro ao consultar no banco de dados." + e.getMessage());
        } catch (ClassNotFoundException e) {
            new JanelaAviso("Erro ao importar lib mysql/connector: " + e.getMessage());
        }
        return null;
    }

    public void listar(List<Veiculo> veiculos) {
        boolean editar = false;
        Agendamento agendamentoEdit = null;
        try {
            do {
                List<Agendamento> lista = dao.consultarTodos();
                if (lista != null) {
                    if (janela != null) {
                        janela.dispose();
                    }

                    janela = (editar) ? new JanelaAgendamento(lista, veiculos, agendamentoEdit) : new JanelaAgendamento(lista, veiculos);
                    editar = false;

                    if (janela.getBotao() != null) {
                        if (janela.getBotao().equals("Add")) {
                            inserir(janela.getAgendamento());
                        } else if (janela.getBotao().equals("Editar")) {
                            agendamentoEdit = janela.getAgendamento();
                            editar = true;
                        } else if (janela.getBotao().equals("EditarLinha")) {
                            alterar(janela.getAgendamento());
                        } else if (janela.getBotao().equals("Excluir")) {
                            excluir(janela.getAgendamento());
                        }
                    }
                } else
                    new JanelaAviso("Não existem agendamentos realizados");
            } while (janela.getBotao() != null && !janela.getBotao().equals("Fechar"));

        } catch (NullPointerException e) {
            new JanelaAviso("Falha na listagem, agendamento inválido:" + e.getMessage());
        } catch (SQLException e) {
            new JanelaAviso("Erro ao consultar no banco de dados." + e.getMessage());
        } catch (ClassNotFoundException e) {
            new JanelaAviso("Erro ao importar lib mysql/connector: " + e.getMessage());
        }
    }
}

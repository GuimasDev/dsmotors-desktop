package controle;

import entidades.Usuario;
import view.JanelaMenu;

public class Sistema {
	private Usuario usuario;
	private ControleUsuario controleUsuario;
	private ControleVeiculo controleVeiculo;
	private ControleAgendamento controleAgendamento;

	static Sistema instance;

	private Sistema() {
		controleUsuario = new ControleUsuario();
		controleVeiculo = new ControleVeiculo();
		controleAgendamento = new ControleAgendamento();

	}

	public static Sistema getInstance() {
		if (instance == null) {
			instance = new Sistema();
		}
		return instance;
	}

	public void iniciar() {
		if (usuario == null) {
			usuario = controleUsuario.login();
		}

		if (usuario != null) {
			int option;
			do {
				JanelaMenu menu = new JanelaMenu(usuario);
				option = menu.getOption();

				switch (option) {
					case 1: // janela veiculos
						controleVeiculo.listar(usuario);
						break;
					case 2: // janela agendamentos
						controleAgendamento.listar(controleVeiculo.getLista(usuario.getCodigo()));
						break;
					case 3: // logout
						usuario = null;
						usuario = controleUsuario.login();
						break;
				}

				if (usuario == null || option == 0)
					option = 555;

			} while (option != 555);
		}

	}

}
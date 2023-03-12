package view;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controle.Util;
import entidades.Agendamento;

public class AgendamentoTableModel extends AbstractTableModel {
	private List<Agendamento> linhas;
	private String[] colunas;

	public AgendamentoTableModel(List<Agendamento> agendamentos) {
		this.linhas = agendamentos;
		colunas = new String[] {"DATA", "OBJETIVO", "VEICULO"};
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Agendamento agendamento = linhas.get(rowIndex);

		switch (columnIndex) {
		case 0: {
			return Util.transformDateToString(agendamento.getData());
		}
		case 1: {
			return agendamento.getObjetivo();
		}
		case 2: {
			return agendamento.getVeiculo().getPlaca();
		}
		default:
			throw new IllegalArgumentException(
					"Unexpected value: " + columnIndex);
		}
	}

}

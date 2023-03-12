package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidades.Veiculo;

public class VeiculoTableModel extends AbstractTableModel {
	private List<Veiculo> linhas;
	private String[] colunas;

	public VeiculoTableModel(List<Veiculo> veiculos) {
		this.linhas = veiculos;
		colunas = new String[] {"MODELO", "PLACA", "ANO"};
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
		Veiculo veiculo = linhas.get(rowIndex);

		switch (columnIndex) {
		case 0: {
			return veiculo.getModelo();
		}
		case 1: {
			return veiculo.getPlaca();
		}
		case 2: {
			return veiculo.getAno();
		}
		default:
			throw new IllegalArgumentException(
					"Unexpected value: " + columnIndex);
		}
	}

}

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.GridLayout;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import entidades.Usuario;
import entidades.Veiculo;

public class JanelaVeiculo extends JDialog implements ActionListener {
	private JTextField tfModelo, tfPlaca, tfAno, tfCodigo;
	private String botao;
	private Veiculo veiculo = null;
	private Usuario usuario;
	private JButton btFechar, btAdd, btEditar;

	public JanelaVeiculo(List<Veiculo> veiculos, Usuario usuario) {
		this.usuario = usuario;
		initGui(veiculos);
		// tornar a janela visivel
		this.setVisible(true);
	}

	public JanelaVeiculo(List<Veiculo> veiculos, Usuario usuario, Veiculo veiculoEditar) {
		this.veiculo = veiculoEditar;
		this.usuario = usuario;
		initGui(veiculos);
		this.setVisible(true);
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public String getBotao() {
		return botao;
	}

	public void initGui(List<Veiculo> veiculos) {
		setTitle("Meus veiculos");
		this.setSize(500, 460);
		// setLayout(null);
		setResizable(false);
		this.setLocationRelativeTo(null); // colocar a janela no meio
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Fechar esta janela fecha a aplicacao
		setModal(true); // Modal faz com que execute em uma unica thread

		// conteiner dos outros componentes
		JPanel panel = (JPanel) getContentPane();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		JPanel panelTop = new JPanel();
		panelTop.setLayout(new GridLayout(2, 1, 10, 10));
		panelTop.setBorder(new EmptyBorder(10, 20, 40, 20));

		// Label titulo

		JPanel panelTitulo = new JPanel();
		JLabel lbTitulo = new JLabel("Meus veiculos");
		lbTitulo.setForeground(Color.BLUE);
		lbTitulo.setFont(new Font("Times new Roman", Font.BOLD, 20));
		panelTitulo.add(lbTitulo);
		panelTop.add(panelTitulo);

		JPanel panelForm = new JPanel();
		panelForm.setLayout(new GridLayout(1, 3, 10, 20));

		
		tfCodigo = (veiculo == null) ? new JTextField("0") : new JTextField(veiculo.getCodigo()+"");

		JPanel campoModelo = new JPanel();
		campoModelo.setLayout(new GridLayout(2, 1));
		JLabel lbModelo = new JLabel("Modelo: ");
		lbModelo.setBounds(50, 75, 100, 35);
		campoModelo.add(lbModelo);
		tfModelo = new JTextField("");
		tfModelo.setBounds(50, 115, 250, 35);
		campoModelo.add(tfModelo);
		panelForm.add(campoModelo);

		JPanel campoPlaca = new JPanel();
		campoPlaca.setLayout(new GridLayout(2, 1));
		JLabel lbPlaca = new JLabel("Placa: ");
		lbPlaca.setBounds(50, 75, 100, 35);
		campoPlaca.add(lbPlaca);
		try {
			MaskFormatter mascara = new MaskFormatter("UUU-###");
			tfPlaca = new JFormattedTextField(mascara);
			add(tfPlaca);
		} catch (Exception e) {
		}
		campoPlaca.add(tfPlaca);
		panelForm.add(campoPlaca);

		JPanel campoAno = new JPanel();
		campoAno.setLayout(new GridLayout(2, 1));
		JLabel lbAno = new JLabel("Ano: ");
		lbAno.setBounds(50, 75, 100, 35);
		campoAno.add(lbAno);
		try {
			MaskFormatter mascara = new MaskFormatter("####");
			tfAno = new JFormattedTextField(mascara);
			campoAno.add(tfAno);
		} catch (Exception e) {
		}
		campoAno.add(tfAno);
		panelForm.add(campoAno);

		if (veiculo == null) {
			btAdd = new JButton("Add");
			btAdd.addActionListener(this);
			btAdd.setBackground(Color.BLUE);
			btAdd.setForeground(Color.WHITE);
			panelForm.add(btAdd);		
		} else {
			btEditar = new JButton("Edit");
			btEditar.addActionListener(this);
			btEditar.setBackground(Color.BLUE);
			btEditar.setForeground(Color.WHITE);
			panelForm.add(btEditar);

			tfAno.setText(getVeiculo().getAno());
			tfModelo.setText(getVeiculo().getModelo());
			tfPlaca.setText(getVeiculo().getPlaca());
		}

		panelTop.add(panelForm);

		panel.add(panelTop, BorderLayout.NORTH);

		// tabela
		JPanel areaTabela = new JPanel();
		areaTabela.setLayout(new BorderLayout(0,0));

		JLabel lbAviso = new JLabel("Clique na linha para editar ou excluir");
		lbAviso.setFont(new Font("Arial", Font.BOLD, 10));
		areaTabela.add(lbAviso, BorderLayout.NORTH);


		VeiculoTableModel tmVeiculo = new VeiculoTableModel(veiculos); // DisciplinaTableModel armazena os dados que serao exibidos na tabela (JTable)
		
		JTable tabela = new JTable(tmVeiculo); // Jtable exibe os dados

		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.getTableHeader().setResizingAllowed(false);

		tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
	
		tabela.addMouseListener((MouseListener) new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				Veiculo vei = veiculos.get(tabela.getSelectedRow()); 
				JanelaOptions janelaOpt = new JanelaOptions(vei.getModelo()+ " - " +vei.getPlaca()+ " - " +vei.getAno());
				if (janelaOpt.getBotao() == "Editar") {
					veiculo = vei;
					botao = "Editar";
					System.out.println("Editar");
					dispose();
				} else if (janelaOpt.getBotao() == "Excluir") {
					veiculo = vei;
					botao = "Excluir";
					System.out.println("Excluir");
					dispose();
				}
			} 
		}); 

		JScrollPane scroll = new JScrollPane(tabela, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		areaTabela.add(scroll);

		getContentPane().add(areaTabela, BorderLayout.CENTER);

		JPanel panelBotoes = new JPanel();
		getContentPane().add(panelBotoes, BorderLayout.SOUTH);

		btFechar = new JButton("Fechar");
		btFechar.addActionListener(this);
		panelBotoes.add(btFechar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAdd) {
			String modelo = tfModelo.getText();
			String placa = tfPlaca.getText();
			String ano = tfAno.getText();
			this.veiculo = new Veiculo(modelo, ano, placa, usuario); 
			botao = "Add";
		} else if (e.getSource() == btFechar) {
			botao = "Fechar";
		} else if (e.getSource() == btEditar) {
			try {
				int codigo = Integer.parseInt(tfCodigo.getText());
				String modelo = tfModelo.getText();
				String placa = tfPlaca.getText();
				String ano = tfAno.getText();
				this.veiculo = new Veiculo(codigo, modelo, ano, placa, usuario); 
			} catch (NumberFormatException exep){
				System.out.println("Número inválido!" + exep);
			}
			botao = "EditarLinha";
		}
		dispose(); // fechar a janela
	}

}

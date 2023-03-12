package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controle.Util;
import entidades.Agendamento;
import entidades.Veiculo;

public class JanelaAgendamento extends JDialog implements ActionListener {
	private JTextField tfData, tfCodigo;
    JComboBox<String> selectVeiculo, selectObjetivo;
	private String botao;
	private Agendamento agendamento = null;
	private List<Veiculo> veiculos;
	private JButton btFechar, btAdd, btEditar;


	public JanelaAgendamento(List<Agendamento> agendamentos, List<Veiculo> veiculos) {
		this.veiculos = veiculos;
		initGui(agendamentos);
		
		this.setVisible(true);
	}

	public JanelaAgendamento(List<Agendamento> agendamentos, List<Veiculo> veiculos, Agendamento agendamentoEditar) {
		this.veiculos = veiculos;
		this.agendamento = agendamentoEditar;
		initGui(agendamentos);

		this.setVisible(true);
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public String getBotao() {
		return botao;
	}

	public void initGui(List<Agendamento> agendamentos) {
        setTitle("Meus agendamentos");
		this.setSize(500, 460);
		setResizable(false);
		this.setLocationRelativeTo(null); // colocar a janela no meio
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Fechar esta janela fecha a aplicacao
		setModal(true); // Modal faz com que execute em uma unica thread

		// conteiner dos outros componentes
		JPanel panel = (JPanel) getContentPane();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		
        JPanel panelTop = new JPanel();
		panelTop.setLayout(new GridLayout(2,1, 10, 10));
		panelTop.setBorder(new EmptyBorder(10, 20, 40, 20));

		
        //Label titulo
		
        JPanel panelTitulo = new JPanel();
		JLabel lbTitulo = new JLabel("Meus agendamentos");
        lbTitulo.setForeground(Color.BLUE);
        lbTitulo.setFont(new Font("Times new Roman", Font.BOLD, 20));
        panelTitulo.add(lbTitulo);
		panelTop.add(panelTitulo);
        
		JPanel panelForm = new JPanel();
		panelForm.setLayout(new GridLayout(1,3, 10, 20));

		tfCodigo = (agendamento == null) ? new JTextField("0") : new JTextField(agendamento.getCodigo()+"");

        JPanel campoData = new JPanel();
		campoData.setLayout(new GridLayout(2,1));
        JLabel lbData = new JLabel("Data: ");
        lbData.setBounds(50,75,100,35);
        campoData.add(lbData);
        try {
            MaskFormatter mascara= new MaskFormatter("## / ## / ####");
            mascara.setValidCharacters("0123456789");
            tfData = new JFormattedTextField(mascara);
            add(tfData);
        }
        catch(Exception e){}


        campoData.add(tfData);
		panelForm.add(campoData);


		JPanel campoVeiculo = new JPanel();
		campoVeiculo.setLayout(new GridLayout(2,1));
        JLabel lbVeiculo = new JLabel("Veiculo: ");
        lbVeiculo.setBounds(50,75,100,35);
        campoVeiculo.add(lbVeiculo);
        JTextField tfVeiculo = new JTextField("");
        tfVeiculo.setBounds(50,115,250,35);

        selectVeiculo = new JComboBox<String>();
        selectVeiculo.setBackground(Color.WHITE);


		for (Veiculo veiculo : veiculos) {
			selectVeiculo.addItem(veiculo.getPlaca());
		}

        campoVeiculo.add(selectVeiculo);
		panelForm.add(campoVeiculo);

		JPanel campoObjetivo = new JPanel();
		campoObjetivo.setLayout(new GridLayout(2,1));
        JLabel lbObjetivo = new JLabel("Objetivo: ");
        lbObjetivo.setBounds(50,75,100,35);
        campoObjetivo.add(lbObjetivo);

        selectObjetivo = new JComboBox<String>();
        selectObjetivo.setBackground(Color.WHITE);
        selectObjetivo.addItem("Revisão");
        selectObjetivo.addItem("Manutenção");
        campoObjetivo.add(selectObjetivo);
		panelForm.add(campoObjetivo);

		if (agendamento == null) {
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

			String data = Util.transformDateToString(getAgendamento().getData());

			tfData.setText(data);
			selectVeiculo.setSelectedItem(getAgendamento().getVeiculo().getPlaca());
			selectObjetivo.setSelectedItem(getAgendamento().getObjetivo());
		}

		panelTop.add(panelForm);


        panel.add(panelTop, BorderLayout.NORTH);

        // tabela
		JPanel areaTabela = new JPanel();
		areaTabela.setLayout(new BorderLayout(0,0));
		JLabel lbAviso = new JLabel("Clique na linha para editar ou excluir");
		lbAviso.setFont(new Font("Arial", Font.BOLD, 10));
		areaTabela.add(lbAviso, BorderLayout.NORTH);

		AgendamentoTableModel tmAgendamento = new AgendamentoTableModel(agendamentos); // DisciplinaTableModel armazena os dados que serao exibidos na tabela (JTable)
		
		JTable tabela = new JTable(tmAgendamento);  // Jtable exibe os dados
		
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.getTableHeader().setResizingAllowed(false);
		
		tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
		
		tabela.addMouseListener((MouseListener) new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				Agendamento agend = agendamentos.get(tabela.getSelectedRow()); 
				JanelaOptions janelaOpt = new JanelaOptions(agend.getData()+ " - " +agend.getVeiculo().getModelo()+ " - " +agend.getObjetivo());
				if (janelaOpt.getBotao() == "Editar") {
					agendamento = agend;
					botao = "Editar";
					System.out.println("Editar");
					dispose();
				} else if (janelaOpt.getBotao() == "Excluir") {
					agendamento = agend;
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

	} // fim do initGui

	private Veiculo consultarPorPlaca(String placa) {
		for (Veiculo veiculo : veiculos) {
			if (veiculo.getPlaca().equals(placa)) {
				return veiculo;
			}
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAdd) {
			String data = tfData.getText();
			String placa = selectVeiculo.getSelectedItem().toString();
			Veiculo veiculo = consultarPorPlaca(placa);
			String objetivo = selectObjetivo.getSelectedItem().toString();
            this.agendamento = new Agendamento(0, Util.transformStringToDate(data), objetivo, veiculo);
            botao = "Add";
		} else if (e.getSource() == btFechar){
            botao = "Fechar";
        } else if (e.getSource() == btEditar) {
			try {
				int codigo = Integer.parseInt(tfCodigo.getText());
				String data = tfData.getText();
				String placa = selectVeiculo.getSelectedItem().toString();
				Veiculo veiculo = consultarPorPlaca(placa);
				String objetivo = selectObjetivo.getSelectedItem().toString();
				this.agendamento = new Agendamento(codigo, Util.transformStringToDate(data), objetivo, veiculo); 
			} catch (NumberFormatException exep){
				System.out.println("Número inválido!" + exep);
			}
			botao = "EditarLinha";
		}
		dispose(); // fechar a janela
	}

}

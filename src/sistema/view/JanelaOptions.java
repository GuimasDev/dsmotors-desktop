package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class JanelaOptions extends JDialog implements ActionListener {
    private JLabel lbMensagem;
    private JButton btEditar, btExcluir;
    private String botao;


	public JanelaOptions(String item) {
		setTitle("Aviso!!!");
		this.setSize(550, 200);
        setLayout(null);
		setResizable(false);
		this.setLocationRelativeTo(null); // colocar a janela no meio
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Fechar esta janela fecha a aplicacao
		setModal(true); // Modal faz com que execute em uma unica thread

        
        //Label titulo
        lbMensagem = new JLabel("O que você deseja fazer com o item: " + item);
        lbMensagem.setBounds(50,25,500,35);
        add(lbMensagem);
        
        // Botoes
		btEditar = new JButton("Editar");
		btEditar.addActionListener(this);
		btEditar.setFocusPainted(false);
        btEditar.setBackground(Color.BLUE);
        btEditar.setForeground(Color.WHITE);
        btEditar.setBounds(260,90,120,35);
		add(btEditar);

        btExcluir = new JButton("Excluir");
		btExcluir.addActionListener(this);
		btExcluir.setFocusPainted(false);
        btExcluir.setBackground(Color.BLUE);
        btExcluir.setForeground(Color.WHITE);
        btExcluir.setBounds(400,90,120,35);
		add(btExcluir);
        
        
        this.setVisible(true); // tornar a janela visivel

	}

    public String getBotao() {
        return botao;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btEditar) {
            this.botao = "Editar";
        } else if (e.getSource() == btExcluir){
            this.botao = "Excluir";
        }
		dispose(); // fechar a janela
	}

	public static void main(String[] args) {  // apenas para testar a Janela

		//JanelaOptions janela = new JanelaOptions("Teste teste teste");
	}


}
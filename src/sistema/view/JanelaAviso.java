package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class JanelaAviso extends JDialog implements ActionListener {
    private JButton btOk;


	public JanelaAviso(String msg) {
		setTitle("Aviso!!!");
		this.setSize(550, 230);
		this.getContentPane().setBackground(Color.WHITE);
        setLayout(null);
		setResizable(false);
		this.setLocationRelativeTo(null); // colocar a janela no meio
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Fechar esta janela fecha a aplicacao
		setModal(true); // Modal faz com que execute em uma unica thread

        
        //Label titulo

		JTextArea lbMensagem = new JTextArea(msg); 
		lbMensagem.setEditable(false); 
		lbMensagem.setBounds(50,25,400,90); 
		lbMensagem.setLineWrap(true);
		add(lbMensagem);

        // Botoes
		btOk = new JButton("Ok");
		btOk.addActionListener(this);
		btOk.setFocusPainted(false);
        btOk.setBackground(Color.BLUE);
        btOk.setForeground(Color.WHITE);
        btOk.setBounds(360,120,120,35);
		add(btOk);
        
        this.setVisible(true); // tornar a janela visivel

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose(); // fechar a janela
	}

	public static void main(String[] args) {  // apenas para testar a Janela

		//JanelaAviso janela = new JanelaAviso("Teste teste teste");
	}


}

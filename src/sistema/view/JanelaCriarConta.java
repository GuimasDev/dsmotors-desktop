package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import entidades.Usuario;

public class JanelaCriarConta extends JDialog implements ActionListener {
    private Usuario usuario;    
    private String botao;
    private JLabel lbTitulo,lbEmail,lbSenha; 
    private JTextField tfEmail, tfSenha;
	private JButton btCriar, btVoltar;

    public JanelaCriarConta() {
		initGui();

        this.setVisible(true); // tornar a janela visivel
	}
    public String getBotao() {
        return botao;
    }

	public void initGui() {
        setTitle("Criar conta");
		this.setSize(350, 460);
        setLayout(null);
		setResizable(false);
		this.setLocationRelativeTo(null); // colocar a janela no meio
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Fechar esta janela fecha a aplicacao
		setModal(true); // Modal faz com que execute em uma unica thread

        //Label titulo
        lbTitulo = new JLabel("Nova conta");
        lbTitulo.setForeground(Color.BLUE);
        lbTitulo.setFont(new Font("Times new Roman", Font.BOLD, 20));
        lbTitulo.setBounds(130,25,100,35);
        add(lbTitulo);
        
		//Label Email
        lbEmail = new JLabel("Informe seu email: ");
        lbEmail.setBounds(50,75,150,35);
        add(lbEmail);

		//Text Email
        tfEmail = new JTextField("");
        tfEmail.setBounds(50,115,250,35);
        add(tfEmail);

		//Label Senha
        lbSenha = new JLabel("Informe uma senha: ");
        lbSenha.setBounds(50,155,150,35);
        add(lbSenha);

        //Text Senha
        tfSenha = new JTextField("");
        tfSenha.setBounds(50,195,250,35);
        add(tfSenha);


        // Botoes
		btCriar = new JButton("Criar conta");
		btCriar.addActionListener(this);
        btCriar.setBackground(Color.BLUE);
        btCriar.setForeground(Color.WHITE);
        btCriar.setBounds(110,305,120,35);
		add(btCriar);


        btVoltar = new JButton("Voltar");
        btVoltar.addActionListener(this);
        btVoltar.setForeground(Color.RED);
        btVoltar.setBorder(null);
        btVoltar.setContentAreaFilled(false);
        btVoltar.setBounds(110,355,120,35);
        add(btVoltar);

	}

    public Usuario getUsuario() {
        return usuario;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btCriar) {
			String email = tfEmail.getText();
			String senha = tfSenha.getText();
            this.usuario = new Usuario(email, senha);
            botao = "Criar";
		} else if (e.getSource() == btVoltar) {
            botao = "Voltar";
        }
		dispose(); // fechar a janela
	}

	public static void main(String[] args) {  // apenas para testar a Janela

        // JanelaCriarConta janela = new JanelaCriarConta();
	}


}

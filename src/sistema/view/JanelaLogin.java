package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entidades.Usuario;

public class JanelaLogin extends JDialog implements ActionListener {
    private Usuario login;
    private String botao;
    private JLabel lbTitulo,lbEmail,lbSenha; 
    private JTextField tfEmail, tfSenha;
	private JButton btEntrar, btCriar;

    public JanelaLogin(List<Usuario> lista) {
		initGui();

        this.setVisible(true); // tornar a janela visivel
	}

	public JanelaLogin() {
		initGui();

        this.setVisible(true); // tornar a janela visivel
	}

    public Usuario getLogin() {
        return login;
    }

    public String getBotao() {
        return botao;
    }
	
	public void initGui() {
        setTitle("Login");
		this.setSize(350, 460);
        setLayout(null);
		setResizable(false);
		this.setLocationRelativeTo(null); // colocar a janela no meio
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Fechar esta janela fecha a aplicacao
		setModal(true); // Modal faz com que execute em uma unica thread

        //Label titulo
        lbTitulo = new JLabel("Login");
        lbTitulo.setForeground(Color.BLUE);
        lbTitulo.setFont(new Font("Times new Roman", Font.BOLD, 20));
        lbTitulo.setBounds(150,25,100,35);
        add(lbTitulo);
        
		//Label Email
        lbEmail = new JLabel("Email: ");
        lbEmail.setBounds(50,75,100,35);
        add(lbEmail);

		//Text Email
        tfEmail = new JTextField("");
        tfEmail.setBounds(50,115,250,35);
        add(tfEmail);

		//Label Senha
        lbSenha = new JLabel("Senha: ");
        lbSenha.setBounds(50,155,100,35);
        add(lbSenha);

        //Text Senha
        tfSenha = new JPasswordField();
        tfSenha.setBounds(50,195,250,35);
        add(tfSenha);


        // Botoes
		btEntrar = new JButton("Entrar");
		btEntrar.addActionListener(this);
        btEntrar.setBackground(Color.BLUE);
        btEntrar.setForeground(Color.WHITE);
        btEntrar.setBounds(110,305,120,35);
		add(btEntrar);


        btCriar = new JButton("Criar conta");
        btCriar.addActionListener(this);
        btCriar.setForeground(Color.RED);
        btCriar.setBorder(null);
        btCriar.setContentAreaFilled(false);
        btCriar.setBounds(110,355,120,35);
        add(btCriar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btEntrar) {
			String email = tfEmail.getText();
			String senha = tfSenha.getText();
            this.login = new Usuario(0, email, senha);
            botao = "Entrar";
		} else if (e.getSource() == btCriar){
            botao = "Criar";
        }
		dispose(); // fechar a janela
	}

	public static void main(String[] args) {  // apenas para testar a Janela

		// JanelaLogin janela = new JanelaLogin();
	}


}

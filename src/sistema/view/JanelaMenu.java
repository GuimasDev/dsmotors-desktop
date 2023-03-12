package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import entidades.Usuario;

public class JanelaMenu extends JDialog implements ActionListener {
    private int option;
    private JLabel lbTitulo, lbUserHeader;
	private JButton btVeiculos, btAgendamentos, btSair;


	public JanelaMenu(Usuario usuario) {
		initGui(usuario);

        this.setVisible(true); // tornar a janela visivel
	}

    public int getOption() {
		return option;
	}

	public void initGui(Usuario usuario) {
        setTitle("Menu");
		this.setSize(500, 460);
        setLayout(null);
		setResizable(false);
		this.setLocationRelativeTo(null); // colocar a janela no meio
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Fechar esta janela fecha a aplicacao
		setModal(true); // Modal faz com que execute em uma unica thread

        //Label titulo
        lbTitulo = new JLabel("Menu", null, JLabel.CENTER);
        lbTitulo.setForeground(Color.BLUE);
        lbTitulo.setFont(new Font("Times new Roman", Font.BOLD, 30));
        lbTitulo.setBounds(0,25,500,35);
        add(lbTitulo);

		//Label Usuario
		ImageIcon icon = createImageIcon("imgs/imguser.png", "User icon");     
		lbUserHeader = new JLabel(usuario.getEmail(), icon, JLabel.LEFT);
        lbUserHeader.setForeground(Color.black);
        lbUserHeader.setFont(new Font("Arial", Font.BOLD, 10));
        lbUserHeader.setBounds(30,80,200,35);
        add(lbUserHeader);

		// Opçoes
		ImageIcon iconCar = createImageIcon("imgs/imgcar.png", "Car icon"); 
		btVeiculos = new JButton("Meus veículos");
		btVeiculos.setIcon(iconCar);
		btVeiculos.addActionListener(this);
		btVeiculos.setFocusPainted(false);
        btVeiculos.setBackground(Color.blue);
        btVeiculos.setForeground(Color.WHITE);
		btVeiculos.setFont(new Font("Arial", Font.BOLD, 15));
        btVeiculos.setBounds(30,130,430,100);
		add(btVeiculos);

		ImageIcon iconAgend = createImageIcon("imgs/imgagend.png", "Agendamento icon"); 
        btAgendamentos = new JButton("Meus agendamentos");
		btAgendamentos.setIcon(iconAgend);
        btAgendamentos.addActionListener(this);
        btAgendamentos.setFocusPainted(false);
        btAgendamentos.setBackground(Color.blue);
        btAgendamentos.setForeground(Color.WHITE);
		btAgendamentos.setFont(new Font("Arial", Font.BOLD, 15));
        btAgendamentos.setBounds(30,240,430,100);
        add(btAgendamentos);

		// botao logout
		btSair = new JButton("Sair");
        btSair.addActionListener(this);
        btSair.setForeground(Color.RED);
        btSair.setBorder(null);
        btSair.setContentAreaFilled(false);
        btSair.setBounds(210,360,70,35);
        add(btSair);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btVeiculos)
			option = 1;
		else if (e.getSource() == btAgendamentos) 
			option = 2;
		else if (e.getSource() == btSair) 
			option = 3;
		dispose(); // fechar a janela
	}

	private ImageIcon createImageIcon(String path, String description) {
    java.net.URL imgURL = getClass().getResource(path);
    if (imgURL != null) {
        return new ImageIcon(imgURL, description);
    } else {
        System.err.println("Couldn't find file: " + path);
        return null;
    }
}

	public static void main(String[] args) {  // apenas para testar a Janela

		// JanelaMenu janela = new JanelaMenu(new Usuario(0, "aa@dev.com", "123"));
	}


}

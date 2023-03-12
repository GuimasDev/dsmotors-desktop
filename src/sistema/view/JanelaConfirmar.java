package view;

import javax.swing.JOptionPane;

public class JanelaConfirmar {
    public static int confirm(String str) {
        return JOptionPane.showConfirmDialog(null, str, "Aviso", 0);
    }

}

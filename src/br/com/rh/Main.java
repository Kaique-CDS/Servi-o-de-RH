package br.com.rh;

import br.com.rh.gui.JanelaPrincipal;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Garante que a interface gráfica será executada na Event Dispatch Thread do Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JanelaPrincipal janela = new JanelaPrincipal();
                janela.setVisible(true);
            }
        });
    }
}

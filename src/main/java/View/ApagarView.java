package View;

import javax.swing.*;
import Controller.ApagarConta;
import java.awt.*;

public class ApagarView {
    public static void apagar(int idUsuario, JFrame telaPrincipal, JFrame telaConta){
        JFrame telaApagar = new JFrame();
        telaApagar.setSize(700, 700);
        telaApagar.setLocationRelativeTo(null);
        telaApagar.setResizable(false);
        telaApagar.setBackground(Color.LIGHT_GRAY);

        JPanel areaTexto = new JPanel();
        areaTexto.setBackground(Color.LIGHT_GRAY);
        areaTexto.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Você tem certeza?");
        titulo.setFont(new Font("Verdana", Font.BOLD, 20)); 
        gbc.gridx = 0;
        gbc.gridy = 0;
        areaTexto.add(titulo, gbc);

        JLabel certeza = new JLabel("Seu saldo será perdido automaticamente quando apagar a conta! ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        areaTexto.add(certeza, gbc);

        JButton confirmar = new JButton("APAGAR");
        gbc.gridx = 0;
        gbc.gridy = 2;
        confirmar.setBackground(Color.BLACK);
        confirmar.setForeground(Color.WHITE);
        confirmar.setFocusPainted(false);
        confirmar.setBorderPainted(false);
        confirmar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmar.addActionListener(e -> ApagarConta.apagar(idUsuario, telaConta, telaPrincipal, telaApagar, telaApagar));
        areaTexto.add(confirmar, gbc);
        
        telaApagar.add(areaTexto);

        telaApagar.setVisible(true);
    }
}

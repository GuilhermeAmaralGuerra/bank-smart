package View;

import javax.swing.*;

import Controller.EfeturarDeposito;

import java.awt.*;

public class DepositoView {
    public static void deposito(int idUsuario, JFrame telaPrincipal){
        JFrame telaDeposito = new JFrame("Tela de Depósito");
        telaDeposito.setSize(450, 300);
        telaDeposito.setLocationRelativeTo(null);
        telaDeposito.setResizable(false);
        telaDeposito.setBackground(Color.LIGHT_GRAY);

        JPanel areaTexto = new JPanel();
        areaTexto.setBackground(Color.LIGHT_GRAY);
        areaTexto.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Depósito");
        titulo.setFont(new Font("Verdana", Font.BOLD, 20)); 
        gbc.gridx = 1;
        gbc.gridy = 0;
        areaTexto.add(titulo, gbc);

        JLabel inserirDeposito = new JLabel("Insira o valor do depósito:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        areaTexto.add(inserirDeposito, gbc);

        JTextField valorDeposito = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1; 
        areaTexto.add(valorDeposito, gbc);

        JButton realizar = new JButton("Realizar");
        gbc.gridx = 2;
        gbc.gridy = 2;
        realizar.setBackground(Color.BLACK);
        realizar.setForeground(Color.WHITE);
        realizar.setFocusPainted(false);
        realizar.setBorderPainted(false);
        realizar.addActionListener(e -> EfeturarDeposito.fazerDeposito(telaDeposito, telaDeposito, telaPrincipal, valorDeposito.getText(), idUsuario));
        areaTexto.add(realizar, gbc);

        telaDeposito.add(areaTexto);

        telaDeposito.setVisible(true);
    }
}

package View;

import javax.swing.*;

import Controller.EfetuarSaque;

import java.awt.*;

import Model.ContaBancaria;

public class SaqueView {
    public static void saque(int idUsuario, JFrame telaPrincipal){
        JFrame telaSaque = new JFrame("Tela de Depósito");
        telaSaque.setSize(450, 300);
        telaSaque.setLocationRelativeTo(null);
        telaSaque.setResizable(false);
        telaSaque.setBackground(Color.LIGHT_GRAY);

        JPanel areaTexto = new JPanel();
        areaTexto.setBackground(Color.LIGHT_GRAY);
        areaTexto.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Saque");
        titulo.setFont(new Font("Verdana", Font.BOLD, 20)); 
        gbc.gridx = 1;
        gbc.gridy = 0;
        areaTexto.add(titulo, gbc);

        float verSaldo = ContaBancaria.pegarSaldo(idUsuario, telaSaque);

        JLabel saldo = new JLabel("Seu saldo: " + verSaldo);
        gbc.gridx = 0;
        gbc.gridy = 1;
        areaTexto.add(saldo, gbc);

        JLabel inserirSaque = new JLabel("Insira o valor do saque:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        areaTexto.add(inserirSaque, gbc);

        JTextField valorSaque = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2; 
        areaTexto.add(valorSaque, gbc);

        JButton realizar = new JButton("Realizar");
        gbc.gridx = 2;
        gbc.gridy = 3;
        realizar.setBackground(Color.BLACK);
        realizar.setForeground(Color.WHITE);
        realizar.setFocusPainted(false);
        realizar.setBorderPainted(false);
        realizar.addActionListener(e -> EfetuarSaque.fazerSaque(telaSaque, telaSaque, valorSaque.getText(), verSaldo, idUsuario, telaPrincipal));
        areaTexto.add(realizar, gbc);

        telaSaque.add(areaTexto);

        telaSaque.setVisible(true);
    }
}

    

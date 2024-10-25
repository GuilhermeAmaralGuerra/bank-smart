package View;

import javax.swing.*;

import Controller.EfetuarEmprestimo;

import java.awt.*;

public class EmprestimoView {
    public static void emprestimo(int idUsuario, JFrame telaPrincipal){
        JFrame telaEmprestimo = new JFrame("Empréstimo");
        telaEmprestimo.setSize(600, 300);
        telaEmprestimo.setLocationRelativeTo(null);
        telaEmprestimo.setResizable(false);
        telaEmprestimo.setBackground(Color.LIGHT_GRAY);

        JPanel areaTexto = new JPanel();
        areaTexto.setBackground(Color.LIGHT_GRAY);
        areaTexto.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Empréstimo");
        titulo.setFont(new Font("Verdana", Font.BOLD, 20)); 
        gbc.gridx = 1;
        gbc.gridy = 0;
        areaTexto.add(titulo, gbc);

        JLabel escolher = new JLabel("Escolha o empréstimo");
        gbc.gridx = 0;
        gbc.gridy = 1;
        areaTexto.add(escolher, gbc);
        
        JRadioButton primeiraEscolha = new JRadioButton("500");
        JRadioButton segundaEscolha = new JRadioButton("1000");
        JRadioButton terceiraEscolha = new JRadioButton("2500");
        JRadioButton quartaEscolha = new JRadioButton("5000");
        JRadioButton quintaEscolha = new JRadioButton("10000");
        JRadioButton sextaEscolha = new JRadioButton("25000");
        JRadioButton setimaEscolha = new JRadioButton("50000");
        JRadioButton oitavaEscolha = new JRadioButton("100000");

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(primeiraEscolha);
        grupo.add(segundaEscolha);
        grupo.add(terceiraEscolha);
        grupo.add(quartaEscolha);
        grupo.add(quintaEscolha);
        grupo.add(sextaEscolha);
        grupo.add(setimaEscolha);
        grupo.add(oitavaEscolha);

        primeiraEscolha.setBackground(Color.LIGHT_GRAY);
        primeiraEscolha.setForeground(Color.BLACK);
        primeiraEscolha.setFocusPainted(false);
        primeiraEscolha.setBorderPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        areaTexto.add(primeiraEscolha, gbc);

        segundaEscolha.setBackground(Color.LIGHT_GRAY);
        segundaEscolha.setForeground(Color.BLACK);
        segundaEscolha.setFocusPainted(false);
        segundaEscolha.setBorderPainted(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        areaTexto.add(segundaEscolha, gbc);

        terceiraEscolha.setBackground(Color.LIGHT_GRAY);
        terceiraEscolha.setForeground(Color.BLACK);
        terceiraEscolha.setFocusPainted(false);
        terceiraEscolha.setBorderPainted(false);
        gbc.gridx = 2;
        gbc.gridy = 2;
        areaTexto.add(terceiraEscolha, gbc);

        quartaEscolha.setBackground(Color.LIGHT_GRAY);
        quartaEscolha.setForeground(Color.BLACK);
        quartaEscolha.setFocusPainted(false);
        quartaEscolha.setBorderPainted(false);
        gbc.gridx = 3;
        gbc.gridy = 2;
        areaTexto.add(quartaEscolha, gbc);

        quintaEscolha.setBackground(Color.LIGHT_GRAY);
        quintaEscolha.setForeground(Color.BLACK);
        quintaEscolha.setFocusPainted(false);
        quintaEscolha.setBorderPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        areaTexto.add(quintaEscolha, gbc);

        sextaEscolha.setBackground(Color.LIGHT_GRAY);
        sextaEscolha.setForeground(Color.BLACK);
        sextaEscolha.setFocusPainted(false);
        sextaEscolha.setBorderPainted(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        areaTexto.add(sextaEscolha, gbc);

        setimaEscolha.setBackground(Color.LIGHT_GRAY);
        setimaEscolha.setForeground(Color.BLACK);
        setimaEscolha.setFocusPainted(false);
        setimaEscolha.setBorderPainted(false);
        gbc.gridx = 2;
        gbc.gridy = 3;
        areaTexto.add(setimaEscolha, gbc);

        oitavaEscolha.setBackground(Color.LIGHT_GRAY);
        oitavaEscolha.setForeground(Color.BLACK);
        oitavaEscolha.setFocusPainted(false);
        oitavaEscolha.setBorderPainted(false);
        gbc.gridx = 3;
        gbc.gridy = 3;
        areaTexto.add(oitavaEscolha, gbc);

        JButton realizar = new JButton("Realizar");
        gbc.gridx = 3;
        gbc.gridy = 4;
        realizar.setBackground(Color.BLACK);
        realizar.setForeground(Color.WHITE);
        realizar.setFocusPainted(false);
        realizar.setBorderPainted(false);
        realizar.addActionListener(e -> {
            float escolha;

            if (primeiraEscolha.isSelected()) {
                escolha = 500;
            } else if (segundaEscolha.isSelected()) {
                escolha = 1000;
            } else if (terceiraEscolha.isSelected()) {
                escolha = 2500;
            } else if (quartaEscolha.isSelected()) {
                escolha = 5000;
            } else if (quintaEscolha.isSelected()) {
                escolha = 10000;
            } else if (sextaEscolha.isSelected()) {
                escolha = 25000;
            } else if (setimaEscolha.isSelected()) {
                escolha = 50000;
            } else if (oitavaEscolha.isSelected()) {
                escolha = 100000;
            } else {
                JOptionPane.showMessageDialog(telaEmprestimo, "Por favor, selecione um tipo de transação.");
                return;
            }

            EfetuarEmprestimo.fazerEmprestimo(telaEmprestimo, telaEmprestimo, escolha, idUsuario, telaPrincipal);
        });
        areaTexto.add(realizar, gbc);

        telaEmprestimo.add(areaTexto);

        telaEmprestimo.setVisible(true);
    }
}

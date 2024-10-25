package View;

import javax.swing.*;
import Controller.PagarEmprestimo;
import java.awt.*;

public class EmprestimoPagarView {
    public static void pagar(int idUsuario, JFrame telaPrincipal, float valorJuros){
        JFrame telaPagar = new JFrame("Pagar Empréstimo");
        telaPagar.setSize(600, 300);
        telaPagar.setLocationRelativeTo(null);
        telaPagar.setResizable(false);
        telaPagar.setBackground(Color.LIGHT_GRAY);
        
        JPanel areaTexto = new JPanel();
        areaTexto.setBackground(Color.LIGHT_GRAY);
        areaTexto.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Pagar empréstimo");
        titulo.setFont(new Font("Verdana", Font.BOLD, 20)); 
        gbc.gridx = 1;
        gbc.gridy = 0;
        areaTexto.add(titulo, gbc);

        JLabel mostrarEmprestimo = new JLabel("Valor do empréstimo: " + valorJuros);
        gbc.gridx = 0;
        gbc.gridy = 1;
        areaTexto.add(mostrarEmprestimo, gbc);

        JLabel pagarParteTexto = new JLabel("Insira o valor a seguir: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        areaTexto.add(pagarParteTexto, gbc);

        JTextField pagarParte = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        areaTexto.add(pagarParte, gbc);

        JButton pagarIncompleto = new JButton("Pagar uma parte");
        gbc.gridx = 0;
        gbc.gridy = 3;
        pagarIncompleto.setBackground(Color.BLACK);
        pagarIncompleto.setForeground(Color.WHITE);
        pagarIncompleto.setFocusPainted(false);
        pagarIncompleto.setBorderPainted(false);
        pagarIncompleto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pagarIncompleto.addActionListener(e -> PagarEmprestimo.pagarParte(idUsuario, telaPagar, telaPagar, valorJuros, pagarParte.getText(), telaPrincipal));
        areaTexto.add(pagarIncompleto, gbc);

        JButton pagarTudo = new JButton("Pagar Tudo");
        gbc.gridx = 2;
        gbc.gridy = 3;
        pagarTudo.setBackground(Color.BLACK);
        pagarTudo.setForeground(Color.WHITE);
        pagarTudo.setFocusPainted(false);
        pagarTudo.setBorderPainted(false);
        pagarTudo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pagarTudo.addActionListener(e -> PagarEmprestimo.pagarTudo(idUsuario, telaPagar, telaPagar, valorJuros, telaPrincipal));
        areaTexto.add(pagarTudo, gbc);

        telaPagar.add(areaTexto);

        telaPagar.setVisible(true);
    }
}

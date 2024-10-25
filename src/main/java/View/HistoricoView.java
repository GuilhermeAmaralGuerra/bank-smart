package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Model.DepositoModel;
import Model.Emprestimo;
import Model.SaqueModel;
import Model.Transacao;
import java.awt.*;

public class HistoricoView {
    public static void historico(int idUsuario){

        JFrame historicoTela = new JFrame();
        historicoTela.setSize(750, 200);
        historicoTela.setLocationRelativeTo(null);
        historicoTela.setResizable(false);
        historicoTela.setBackground(Color.LIGHT_GRAY);

        JPanel areaHistorico = new JPanel();
        areaHistorico.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        areaHistorico.setBackground(Color.gray);
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        JLabel titulo = new JLabel("Historico");
        titulo.setFont(new Font("Verdana", Font.BOLD, 20));
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 2;
        gbc.gridy = 0;
        areaHistorico.add(titulo, gbc);

        JButton historicoTransferencia = new JButton("Historico de transferência");
        gbc.gridx = 0;
        gbc.gridy = 1;
        historicoTransferencia.setBackground(Color.BLACK);
        historicoTransferencia.setForeground(Color.WHITE);
        historicoTransferencia.setFocusPainted(false);
        historicoTransferencia.setBorderPainted(false);
        historicoTransferencia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        historicoTransferencia.addActionListener(e -> HistoricoView.transferencia(idUsuario, historicoTela));
        areaHistorico.add(historicoTransferencia, gbc);

        JButton historicoSaque = new JButton("Historico de saque");
        gbc.gridx = 1;
        gbc.gridy = 1;
        historicoSaque.setBackground(Color.BLACK);
        historicoSaque.setForeground(Color.WHITE);
        historicoSaque.setFocusPainted(false);
        historicoSaque.setBorderPainted(false);
        historicoSaque.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        historicoSaque.addActionListener(e -> HistoricoView.saque(idUsuario, historicoTela));
        areaHistorico.add(historicoSaque, gbc);

        JButton historicoDeposito = new JButton("Historico de depósito");
        gbc.gridx = 2;
        gbc.gridy = 1;
        historicoDeposito.setBackground(Color.BLACK);
        historicoDeposito.setForeground(Color.WHITE);
        historicoDeposito.setFocusPainted(false);
        historicoDeposito.setBorderPainted(false);
        historicoDeposito.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        historicoDeposito.addActionListener(e -> HistoricoView.deposito(idUsuario,historicoTela));
        areaHistorico.add(historicoDeposito, gbc);

        JButton historicoEmprestimo = new JButton("Historico de empréstimo");
        gbc.gridx = 3;
        gbc.gridy = 1;
        historicoEmprestimo.setBackground(Color.BLACK);
        historicoEmprestimo.setForeground(Color.WHITE);
        historicoEmprestimo.setFocusPainted(false);
        historicoEmprestimo.setBorderPainted(false);
        historicoEmprestimo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        historicoEmprestimo.addActionListener(e -> HistoricoView.emprestimo(idUsuario, historicoTela));
        areaHistorico.add(historicoEmprestimo, gbc);

        historicoTela.add(areaHistorico);

        historicoTela.setVisible(true);
    }

    public static void transferencia(int idUsuario, JFrame historicoTela){

        JFrame mostrarTransferencia = new JFrame();
        mostrarTransferencia.setSize(750, 500);
        mostrarTransferencia.setLocationRelativeTo(null);
        mostrarTransferencia.setResizable(false);
        mostrarTransferencia.setBackground(Color.LIGHT_GRAY);

        JPanel areaHistorico = new JPanel();
        areaHistorico.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        areaHistorico.setBackground(Color.gray);
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        JLabel titulo = new JLabel("Historico de transferência");
        titulo.setFont(new Font("Verdana", Font.BOLD, 20));
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        areaHistorico.add(titulo, gbc);

        JTable tabela = new JTable();
        gbc.gridx = 0;
        gbc.gridy = 1;
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Tipo de transação");
        modelo.addColumn("Valor");
        modelo.addColumn("Data Empréstimo");
        modelo.addColumn("Status");
        modelo.addColumn("destinatario");
        
        Transacao.imprimirTabelaTransferencia(modelo, idUsuario, mostrarTransferencia, mostrarTransferencia);
        
        tabela.setModel(modelo);

        JScrollPane scroll = new JScrollPane(tabela);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;  

        areaHistorico.add(scroll, gbc);

        mostrarTransferencia.add(areaHistorico);

        mostrarTransferencia.setVisible(true);

    }

    public static void saque(int idUsuario, JFrame historicoTela){
        JFrame mostrarSaque = new JFrame();
        mostrarSaque.setSize(750, 500);
        mostrarSaque.setLocationRelativeTo(null);
        mostrarSaque.setResizable(false);
        mostrarSaque.setBackground(Color.LIGHT_GRAY);

        JPanel areaHistorico = new JPanel();
        areaHistorico.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        areaHistorico.setBackground(Color.gray);
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        JLabel titulo = new JLabel("Historico de Saque");
        titulo.setFont(new Font("Verdana", Font.BOLD, 20));
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        areaHistorico.add(titulo, gbc);

        JTable tabela = new JTable();
        gbc.gridx = 0;
        gbc.gridy = 1;
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Valor");
        modelo.addColumn("Data Saque");
        
        SaqueModel.imprimirTabelaSaque(modelo, idUsuario, mostrarSaque, mostrarSaque);

        tabela.setModel(modelo);

        JScrollPane scroll = new JScrollPane(tabela);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;  

        areaHistorico.add(scroll, gbc);

        mostrarSaque.add(areaHistorico);

        mostrarSaque.setVisible(true);
    }

    public static void deposito(int idUsuario, JFrame historicoTela){
        JFrame mostrarDeposito = new JFrame();
        mostrarDeposito.setSize(750, 500);
        mostrarDeposito.setLocationRelativeTo(null);
        mostrarDeposito.setResizable(false);
        mostrarDeposito.setBackground(Color.LIGHT_GRAY);

        JPanel areaHistorico = new JPanel();
        areaHistorico.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        areaHistorico.setBackground(Color.gray);
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        JLabel titulo = new JLabel("Historico de Deposito");
        titulo.setFont(new Font("Verdana", Font.BOLD, 20));
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        areaHistorico.add(titulo, gbc);

        JTable tabela = new JTable();
        gbc.gridx = 0;
        gbc.gridy = 1;
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Valor");
        modelo.addColumn("Data Deposito");
        
        DepositoModel.imprimirTabelaDeposito(modelo, idUsuario, mostrarDeposito, mostrarDeposito);

        tabela.setModel(modelo);

        JScrollPane scroll = new JScrollPane(tabela);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;  

        areaHistorico.add(scroll, gbc);

        mostrarDeposito.add(areaHistorico);

        mostrarDeposito.setVisible(true);
    }

    public static void emprestimo(int idUsuario, JFrame historicoTela){
        JFrame mostrarEmprestimo = new JFrame();
        mostrarEmprestimo.setSize(750, 500);
        mostrarEmprestimo.setLocationRelativeTo(null);
        mostrarEmprestimo.setResizable(false);
        mostrarEmprestimo.setBackground(Color.LIGHT_GRAY);

        JPanel areaHistorico = new JPanel();
        areaHistorico.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        areaHistorico.setBackground(Color.gray);
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        JLabel titulo = new JLabel("Historico de Deposito");
        titulo.setFont(new Font("Verdana", Font.BOLD, 20));
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        areaHistorico.add(titulo, gbc);

        JTable tabela = new JTable();
        gbc.gridx = 0;
        gbc.gridy = 1;
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Valor");
        modelo.addColumn("Data emprestimo");
        modelo.addColumn("Data pagamento");
        modelo.addColumn("Data status");
        modelo.addColumn("pagamento");
        
        Emprestimo.imprimirTabelaEmprestimo(modelo, idUsuario, mostrarEmprestimo, mostrarEmprestimo);

        tabela.setModel(modelo);

        JScrollPane scroll = new JScrollPane(tabela);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;  

        areaHistorico.add(scroll, gbc);

        mostrarEmprestimo.add(areaHistorico);

        mostrarEmprestimo.setVisible(true);
    }
}

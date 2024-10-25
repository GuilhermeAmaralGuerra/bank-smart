package View;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import Model.*;

public class PrincipalView {

    public static void telaBanco(int idUsuario, String nome){
        JFrame telaPrincipal = new JFrame("Tela principal");
        telaPrincipal.setSize(900, 350);
        telaPrincipal.setLocationRelativeTo(null);
        telaPrincipal.setResizable(false);
        telaPrincipal.setBackground(Color.LIGHT_GRAY);
        telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel areaPrincipal = new JPanel();
        areaPrincipal.setBackground(Color.LIGHT_GRAY);
        gbc.gridx = 0;
        gbc.gridy = 0;
        areaPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbcPrincipal = new GridBagConstraints();
        gbcPrincipal.insets = new Insets(10, 10, 10, 10); 
        gbcPrincipal.weightx = 1.0;
        gbcPrincipal.weighty = 1.0;
        gbcPrincipal.fill = GridBagConstraints.HORIZONTAL;

        JPanel areaTexto = new JPanel();
        areaTexto.setBackground(Color.LIGHT_GRAY);
        gbcPrincipal.gridx = 0;
        gbcPrincipal.gridy = 0;
        areaTexto.setLayout(new GridBagLayout());
        GridBagConstraints gbcTexto = new GridBagConstraints();
        gbcTexto.insets = new Insets(10, 10, 10, 150); 
        gbcTexto.weightx = 1.0;
        gbcTexto.weighty = 1.0;
        gbcTexto.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Bem vindo " + nome);
        titulo.setFont(new Font("Verdana", Font.BOLD, 20)); 
        gbcTexto.gridx = 0;
        gbcTexto.gridy = 0;
        areaTexto.add(titulo, gbcTexto);

        float verSaldo = ContaBancaria.pegarSaldo(idUsuario, telaPrincipal);

        JLabel saldo = new JLabel("Seu saldo é:" + verSaldo);
        gbcTexto.gridx = 0;
        gbcTexto.gridy = 1;
        areaTexto.add(saldo, gbcTexto);

        float verEmprestimo = Emprestimo.pegarTotalEmprestimosJuros(idUsuario, telaPrincipal);
        DecimalFormat df = new DecimalFormat("#.00");
        String totalJurosFormatado = df.format(verEmprestimo);
        String valorFormatado = totalJurosFormatado.replace(",", ".");
        float valorJuros = Float.parseFloat(valorFormatado);
        
        JLabel mostrarEmprestimo;
        if (verEmprestimo == 0.0) {
            mostrarEmprestimo = new JLabel("Sem empréstimos pendentes");
        }else{
            mostrarEmprestimo = new JLabel("Seu valor total de empréstimo é: " + valorJuros);
        }
        gbcTexto.gridx = 0;
        gbcTexto.gridy = 2;
        areaTexto.add(mostrarEmprestimo, gbcTexto);

        JButton pagarEmprestimo = new JButton("Pagar empréstimo");
        gbcTexto.gridx = 0;
        gbcTexto.gridy = 3;
        pagarEmprestimo.setBackground(Color.BLACK);
        pagarEmprestimo.setForeground(Color.WHITE);
        pagarEmprestimo.setFocusPainted(false);
        pagarEmprestimo.setBorderPainted(false);
        pagarEmprestimo.addActionListener(null);
        pagarEmprestimo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pagarEmprestimo.addActionListener(e -> EmprestimoPagarView.pagar(idUsuario, telaPrincipal, valorJuros));
        areaTexto.add(pagarEmprestimo, gbcTexto);

        JButton conta = new JButton("Sua conta");
        gbcTexto.gridx = 0;
        gbcTexto.gridy = 4;
        conta.setBackground(Color.BLACK);
        conta.setForeground(Color.WHITE);
        conta.setFocusPainted(false);
        conta.setBorderPainted(false);
        conta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        conta.addActionListener(e -> ContaView.mostrarConta(idUsuario, telaPrincipal));
        areaTexto.add(conta, gbcTexto);

        JButton historico = new JButton("Historico");
        gbcTexto.gridx = 0;
        gbcTexto.gridy = 5;
        historico.setBackground(Color.BLACK);
        historico.setForeground(Color.WHITE);
        historico.setFocusPainted(false);
        historico.setBorderPainted(false);
        historico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        historico.addActionListener(e -> HistoricoView.historico(idUsuario));
        areaTexto.add(historico, gbcTexto);

        areaPrincipal.add(areaTexto);

        JPanel areaBotao = new JPanel();
        areaBotao.setBackground(Color.LIGHT_GRAY);
        gbcPrincipal.gridx = 0;
        gbcPrincipal.gridy = 1;
        areaBotao.setLayout(new GridBagLayout());
        GridBagConstraints gbcBotao = new GridBagConstraints();
        gbcBotao.insets = new Insets(10, 150, 10, 10); 
        gbcBotao.weightx = 1.0;
        gbcBotao.weighty = 1.0;
        gbcBotao.fill = GridBagConstraints.HORIZONTAL;

        JButton transferir = new JButton("Transferir");
        gbcBotao.gridx = 0;
        gbcBotao.gridy = 0;
        transferir.setBackground(Color.BLACK);
        transferir.setForeground(Color.WHITE);
        transferir.setFocusPainted(false);
        transferir.setBorderPainted(false);
        transferir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        transferir.addActionListener(e -> TransferenciaView.telaTransferencia(idUsuario));
        areaBotao.add(transferir, gbcBotao);

        JButton saque = new JButton("Sacar");
        gbcBotao.gridx = 0;
        gbcBotao.gridy = 1;
        saque.setBackground(Color.BLACK);
        saque.setForeground(Color.WHITE);
        saque.setFocusPainted(false);
        saque.setBorderPainted(false);
        saque.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saque.addActionListener(e -> SaqueView.saque(idUsuario, telaPrincipal));
        areaBotao.add(saque, gbcBotao);
        
        JButton deposito = new JButton("Depositar");
        gbcBotao.gridx = 0;
        gbcBotao.gridy = 2;
        deposito.setBackground(Color.BLACK);
        deposito.setForeground(Color.WHITE);
        deposito.setFocusPainted(false);
        deposito.setBorderPainted(false);
        deposito.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deposito.addActionListener(e -> DepositoView.deposito(idUsuario, telaPrincipal));
        areaBotao.add(deposito, gbcBotao);

        JButton emprestimo = new JButton("Empréstimo");
        gbcBotao.gridx = 0;
        gbcBotao.gridy = 3;
        emprestimo.setBackground(Color.BLACK);
        emprestimo.setForeground(Color.WHITE);
        emprestimo.setFocusPainted(false);
        emprestimo.setBorderPainted(false);
        emprestimo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        emprestimo.addActionListener(e -> EmprestimoView.emprestimo(idUsuario, telaPrincipal));
        areaBotao.add(emprestimo, gbcBotao);

        areaPrincipal.add(areaBotao);

        telaPrincipal.add(areaPrincipal, gbc);

        telaPrincipal.setVisible(true);
    }
}
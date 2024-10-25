package View;

import Controller.EfetuarTransacao;
import javax.swing.*;
import java.awt.*;
import Model.*;

public class TransferenciaView {
    public static void telaTransferencia(int idUsuario) {
        JFrame telaTransfer = new JFrame("Tela de Transferência");
        telaTransfer.setSize(450, 300);
        telaTransfer.setLocationRelativeTo(null);
        telaTransfer.setResizable(false);
        telaTransfer.setBackground(Color.LIGHT_GRAY);

        JPanel areaTexto = new JPanel();
        areaTexto.setBackground(Color.LIGHT_GRAY);
        areaTexto.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        long codigo = ContaBancaria.pegarCodigo(idUsuario, telaTransfer);

        JLabel mostrarCodigo = new JLabel("Código de transferência: " + codigo);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        areaTexto.add(mostrarCodigo, gbc);

        JLabel digitarCodigo = new JLabel("Digite o código da outra conta: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        areaTexto.add(digitarCodigo, gbc);

        JTextField inserirDigitarCodigo = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        areaTexto.add(inserirDigitarCodigo, gbc);

        JLabel tipoTransacao = new JLabel("Escolha o tipo de transação: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        areaTexto.add(tipoTransacao, gbc);

        JRadioButton escolhaPessoal = new JRadioButton("Pessoal");
        JRadioButton escolhaEmpresarial = new JRadioButton("Empresarial");

        ButtonGroup grupoTransacao = new ButtonGroup();
        grupoTransacao.add(escolhaPessoal);
        grupoTransacao.add(escolhaEmpresarial);

        gbc.gridx = 1;
        gbc.gridy = 2;
        areaTexto.add(escolhaPessoal, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        areaTexto.add(escolhaEmpresarial, gbc);

        JLabel valor = new JLabel("Digite o valor da transação: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        areaTexto.add(valor, gbc);

        JTextField inserirValor = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 4;
        areaTexto.add(inserirValor, gbc);

        JLabel descricao = new JLabel("Digite a descrição da transação: ");
        gbc.gridx = 0;
        gbc.gridy = 5;
        areaTexto.add(descricao, gbc);

        JTextField inserirDescricao = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 5;
        areaTexto.add(inserirDescricao, gbc);

        JButton efetuar = new JButton("Efetuar");
        gbc.gridx = 2;
        gbc.gridy = 6;
        efetuar.setBackground(Color.BLACK);
        efetuar.setForeground(Color.WHITE);
        efetuar.setFocusPainted(false);
        efetuar.setBorderPainted(false);

        efetuar.addActionListener(e -> {
            String transacaoSelecionada;

            if (escolhaPessoal.isSelected()) {
                transacaoSelecionada = "Pessoal";
            } else if (escolhaEmpresarial.isSelected()) {
                transacaoSelecionada = "Empresarial";
            } else {
                JOptionPane.showMessageDialog(telaTransfer, "Por favor, selecione um tipo de transação.");
                return;
            }

            EfetuarTransacao.fazerTransacao(telaTransfer, idUsuario, inserirDigitarCodigo.getText(), transacaoSelecionada, inserirValor.getText(), inserirDescricao.getText(), telaTransfer);
        });

        areaTexto.add(efetuar, gbc);

        telaTransfer.add(areaTexto);

        telaTransfer.setVisible(true);
    }
}

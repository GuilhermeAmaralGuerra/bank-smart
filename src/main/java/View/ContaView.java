package View;

import javax.swing.*;
import java.awt.*;
import Model.*;
import java.time.LocalDate;

public class ContaView {
    public static void mostrarConta(int idUsuario, JFrame telaPrincipal){
        JFrame telaConta = new JFrame();
        telaConta.setSize(700, 700);
        telaConta.setLocationRelativeTo(null);
        telaConta.setResizable(false);
        telaConta.setBackground(Color.LIGHT_GRAY);

        JPanel areaTexto = new JPanel();
        areaTexto.setBackground(Color.LIGHT_GRAY);
        areaTexto.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Detalhes da conta");
        titulo.setFont(new Font("Verdana", Font.BOLD, 20)); 
        gbc.gridx = 1;
        gbc.gridy = 0;
        areaTexto.add(titulo, gbc);

        // parte usuario

        JLabel nome = new JLabel("Seu nome:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        areaTexto.add(nome, gbc);

        JLabel nomeCompleto = new JLabel("Seu nome completo:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        areaTexto.add(nomeCompleto, gbc);

        JLabel dataNascimento = new JLabel("Sua data de nascimento:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        areaTexto.add(dataNascimento, gbc);
        
        // parte conta bancario (data e codigo de transferencia)

        LocalDate dataAbertura = ContaBancaria.pegarDataAbertura(telaConta, idUsuario);

        JLabel dataCriacao = new JLabel("Data de criação da conta: " + dataAbertura);
        gbc.gridx = 0;
        gbc.gridy = 4;
        areaTexto.add(dataCriacao, gbc);

        long codigo = ContaBancaria.pegarCodigo(idUsuario, telaConta);

        JLabel codigoTransferencia = new JLabel("Código de transferência: " + codigo);
        gbc.gridx = 0;
        gbc.gridy = 5;
        areaTexto.add(codigoTransferencia, gbc);

        // parte endereco
    
        JLabel rua = new JLabel("Rua: ");
        gbc.gridx = 0;
        gbc.gridy = 6;
        areaTexto.add(rua, gbc);

        JLabel numero = new JLabel("Numero:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        areaTexto.add(numero, gbc);

        JLabel complemento = new JLabel("Complemento:");
        gbc.gridx = 0;
        gbc.gridy = 8;
        areaTexto.add(complemento, gbc);

        JLabel cidade = new JLabel("Cidade");
        gbc.gridx = 0;
        gbc.gridy = 9;
        areaTexto.add(cidade, gbc);
        
        JLabel estado = new JLabel("Estado");
        gbc.gridx = 0;
        gbc.gridy = 10;
        areaTexto.add(estado, gbc);
        
        JLabel cep = new JLabel("CEP:");
        gbc.gridx = 0;
        gbc.gridy = 11;
        areaTexto.add(cep, gbc);

        JButton deletar = new JButton("Deletar conta");
        gbc.gridx = 0;
        gbc.gridy = 12;
        deletar.setBackground(Color.BLACK);
        deletar.setForeground(Color.WHITE);
        deletar.setFocusPainted(false);
        deletar.setBorderPainted(false);
        deletar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deletar.addActionListener(e -> ApagarView.apagar(idUsuario, telaPrincipal, telaConta));
        areaTexto.add(deletar, gbc);

        JButton alterar = new JButton("alterar dados");
        gbc.gridx = 2;
        gbc.gridy = 12;
        alterar.setBackground(Color.BLACK);
        alterar.setForeground(Color.WHITE);
        alterar.setFocusPainted(false);
        alterar.setBorderPainted(false);
        alterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        alterar.addActionListener(e -> AlterarView.alterar(idUsuario, telaPrincipal, telaConta));
        areaTexto.add(alterar, gbc);

        ContaView.preencherEndereco(idUsuario, telaConta, telaConta, areaTexto, rua, numero, complemento, cidade, estado, cep);
        ContaView.preencherUsuario(idUsuario, telaConta, telaConta, nome, nomeCompleto, dataNascimento);

        telaConta.add(areaTexto);

        telaConta.setVisible(true);
    }

    public static void preencherEndereco(int idUsuario, JFrame telaConta, Component parentComponent, JPanel areaTexto, JLabel rua,
                                         JLabel numero, JLabel complemento, JLabel cidade, JLabel estado, JLabel cep){
        Endereco endereco = Endereco.pegarTodasInfo(idUsuario, telaConta);

        if (endereco != null) {
            rua.setText("Rua: " + endereco.getRua());
            numero.setText("Numero: " + endereco.getNumero());
            complemento.setText("Complemento: " + endereco.getComplemento());
            cidade.setText("Cidade: " + endereco.getCidade());
            estado.setText("Estado: " + endereco.getEstado());
            cep.setText("CEP: " + endereco.getCep());
        } else {
            JOptionPane.showMessageDialog(parentComponent, "Endereço não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void preencherUsuario(int idUsuario, JFrame telaConta, Component parentComponent, JLabel nome, 
                                        JLabel nomeComplemento, JLabel dataNascimento){
        UsuarioModel usuario = UsuarioModel.pegarTodasInfo(idUsuario, parentComponent);

        if (usuario != null) {
            nome.setText("Nome: " + usuario.getNome());
            nomeComplemento.setText("Nome Completo: " + usuario.getNomeCompleto());
            dataNascimento.setText("Data de nascimento: " + usuario.getDataNascimento());
        } else {
            JOptionPane.showMessageDialog(parentComponent, "Usuário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

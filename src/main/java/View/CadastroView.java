package View;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;

import Controller.Criacao;

public class CadastroView {
    public static void cadastro(){
        JFrame cadastroTela = new JFrame("Cadastro");
        cadastroTela.setSize(600, 500);
        cadastroTela.setLocationRelativeTo(null);
        cadastroTela.setResizable(true);
        cadastroTela.setLayout(new GridBagLayout());

        JPanel areaCadastro = new JPanel();
        areaCadastro.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        areaCadastro.setBackground(Color.gray);
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        JLabel titulo = new JLabel("Cadastro");
        titulo.setFont(new Font("Verdana", Font.BOLD, 20));
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 0;
        areaCadastro.add(titulo, gbc);

        JLabel inserirNome = new JLabel("Insira seu nome aqui: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        areaCadastro.add(inserirNome, gbc);

        JTextField nome = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        areaCadastro.add(nome, gbc);

        JLabel inserirNomeCompleto = new JLabel("Insira seu nome completo aqui: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        areaCadastro.add(inserirNomeCompleto, gbc);

        JTextField nomeCompleto = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        areaCadastro.add(nomeCompleto, gbc);

        JLabel inserirSenha = new JLabel("Insira sua Senha aqui: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        areaCadastro.add(inserirSenha, gbc);

        JPasswordField senha = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        areaCadastro.add(senha, gbc);

        JLabel inserirCPF = new JLabel("Insira seu CPF aqui: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        areaCadastro.add(inserirCPF, gbc);

        MaskFormatter cpfMask = null;
        try {
            cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JFormattedTextField cpf = new JFormattedTextField(cpfMask);
        gbc.gridx = 1;
        gbc.gridy = 4;
        areaCadastro.add(cpf, gbc);

        JLabel inserirNascimento = new JLabel("Insira sua data de nascimento (dd/MM/yyyy): ");
        gbc.gridx = 0;
        gbc.gridy = 5;
        areaCadastro.add(inserirNascimento, gbc);

        MaskFormatter nascimentoMask = null;
        try {
            nascimentoMask = new MaskFormatter("##/##/####");
            nascimentoMask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JFormattedTextField dataNascimento = new JFormattedTextField(nascimentoMask);
        gbc.gridx = 1;
        gbc.gridy = 5;
        areaCadastro.add(dataNascimento, gbc);

        JLabel rua = new JLabel("Rua: ");
        gbc.gridx = 0;
        gbc.gridy = 6;
        areaCadastro.add(rua, gbc);

        JTextField inserirRua = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 6;
        areaCadastro.add(inserirRua, gbc);

        JLabel numero = new JLabel("Numero: ");
        gbc.gridx = 0;
        gbc.gridy = 7;
        areaCadastro.add(numero, gbc);

        JTextField inserirNumero = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 7;
        areaCadastro.add(inserirNumero, gbc);

        JLabel cidade = new JLabel("Cidade: ");
        gbc.gridx = 0;
        gbc.gridy = 8;
        areaCadastro.add(cidade, gbc);

        JTextField inserirCidade = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 8;
        areaCadastro.add(inserirCidade, gbc);

        JLabel estado = new JLabel("Estado: ");
        gbc.gridx = 0;
        gbc.gridy = 9;
        areaCadastro.add(estado, gbc);

        JTextField inserirEstado = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 9;
        areaCadastro.add(inserirEstado, gbc);

        JLabel complemento = new JLabel("Complemento: ");
        gbc.gridx = 0;
        gbc.gridy = 10;
        areaCadastro.add(complemento, gbc);

        JTextField inserirComplemento = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 10;
        areaCadastro.add(inserirComplemento, gbc);

        JLabel CEP = new JLabel("CEP:");
        gbc.gridx = 0;
        gbc.gridy = 11;
        areaCadastro.add(CEP, gbc);

        MaskFormatter cepMask = new MaskFormatter();
        try {
            cepMask = new MaskFormatter("#####-###");
            cepMask.setPlaceholderCharacter('_');

        } catch (ParseException e) {
            e.printStackTrace();
        }

        JFormattedTextField inserirCEP = new JFormattedTextField(cepMask);
        gbc.gridx = 1;
        gbc.gridy = 11;
        areaCadastro.add(inserirCEP, gbc);

        JButton cadastrar = new JButton("Cadastro");
        gbc.gridx = 2;
        gbc.gridy = 12;
        cadastrar.setBackground(Color.BLACK);
        cadastrar.setForeground(Color.WHITE);
        cadastrar.setFocusPainted(false);
        cadastrar.setBorderPainted(false);
        cadastrar.addActionListener(e -> {
            try {
                DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate nascimento = LocalDate.parse(dataNascimento.getText(), formatar);
                
                String senhaText = new String(senha.getPassword());

                Criacao.criarUsuario(cadastroTela, nome.getText(), nomeCompleto.getText(), senhaText, cpf.getText(), nascimento,
                                     inserirRua.getText(), inserirNumero.getText(), inserirCidade.getText(), inserirEstado.getText(),
                                     inserirComplemento.getText(), inserirCEP.getText());
            
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(cadastroTela, "Data de nascimento inválida. Use o formato dd/MM/yyyy.");
            }
        });
        areaCadastro.add(cadastrar, gbc);

        cadastroTela.add(areaCadastro, new GridBagConstraints());
        cadastroTela.setVisible(true);
    }
}

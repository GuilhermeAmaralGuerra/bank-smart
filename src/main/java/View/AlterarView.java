package View;

import javax.swing.*;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import Controller.AlterarConta;
import java.awt.*;
import Model.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AlterarView {
    public static void alterar(int idUsuario, JFrame telaPrincipal, JFrame telaConta){
        JFrame telaAlterar = new JFrame();
        telaAlterar.setSize(700, 700);
        telaAlterar.setLocationRelativeTo(null);
        telaAlterar.setResizable(false);
        telaAlterar.setBackground(Color.LIGHT_GRAY);

        JPanel areaTexto = new JPanel();
        areaTexto.setBackground(Color.LIGHT_GRAY);
        areaTexto.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Alterar conta");
        titulo.setFont(new Font("Verdana", Font.BOLD, 20)); 
        gbc.gridx = 1;
        gbc.gridy = 0;
        areaTexto.add(titulo, gbc);

        JLabel novoNome = new JLabel("Novo nome:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        areaTexto.add(novoNome, gbc);

        JTextField inserirNovoNome = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        areaTexto.add(inserirNovoNome, gbc);

        JLabel novoNomeCompleto = new JLabel("Novo nome completo:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        areaTexto.add(novoNomeCompleto, gbc);

        JTextField inserirNomeCompleto = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        areaTexto.add(inserirNomeCompleto, gbc);

        MaskFormatter nascimentoMask = null;
        try {
            nascimentoMask = new MaskFormatter("##/##/####");
            nascimentoMask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JLabel novaDataNascimento = new JLabel("Nova data de nascimento:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        areaTexto.add(novaDataNascimento, gbc);

        JFormattedTextField inserirNovaDataNascimento = new JFormattedTextField(nascimentoMask);
        gbc.gridx = 1;
        gbc.gridy = 3;
        areaTexto.add(inserirNovaDataNascimento, gbc);

        JLabel novaSenha = new JLabel("Nova senha:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        areaTexto.add(novaSenha, gbc);

        JPasswordField inserirNovaSenha = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 4;
        areaTexto.add(inserirNovaSenha, gbc);

        JLabel novaRua = new JLabel("Nova rua: ");
        gbc.gridx = 0;
        gbc.gridy = 5;
        areaTexto.add(novaRua, gbc);

        JTextField inserirNovaRua = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 5;
        areaTexto.add(inserirNovaRua, gbc);

        JLabel novoNumero = new JLabel("Novo numero:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        areaTexto.add(novoNumero, gbc);

        JTextField inserirNovoNumero = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 6;
        areaTexto.add(inserirNovoNumero, gbc);

        JLabel novoComplemento = new JLabel("Novo Complemento:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        areaTexto.add(novoComplemento, gbc);

        JTextField inserirNovoComplemento = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 7;
        areaTexto.add(inserirNovoComplemento, gbc);

        JLabel novaCidade = new JLabel("Nova cidade");
        gbc.gridx = 0;
        gbc.gridy = 8;
        areaTexto.add(novaCidade, gbc);

        JTextField inserirNovaCidade = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 8;
        areaTexto.add(inserirNovaCidade, gbc);
        
        JLabel novoEstado = new JLabel("Novo estado");
        gbc.gridx = 0;
        gbc.gridy = 9;
        areaTexto.add(novoEstado, gbc);

        JTextField inserirNovoEstado = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 9;
        areaTexto.add(inserirNovoEstado, gbc);
        
        JLabel novoCep = new JLabel("Novo CEP:");
        gbc.gridx = 0;
        gbc.gridy = 10;
        areaTexto.add(novoCep, gbc);

        MaskFormatter cepMask = new MaskFormatter();
        try {
            cepMask = new MaskFormatter("#####-###");
            cepMask.setPlaceholderCharacter('_');

        } catch (ParseException e) {
            e.printStackTrace();
        }

        JFormattedTextField inserirNovoCep = new JFormattedTextField(cepMask);
        gbc.gridx = 1;
        gbc.gridy = 10;
        areaTexto.add(inserirNovoCep, gbc);

        JButton alterar = new JButton("Alterar");
        gbc.gridx = 2;
        gbc.gridy = 11;
        alterar.setBackground(Color.BLACK);
        alterar.setForeground(Color.WHITE);
        alterar.setFocusPainted(false);
        alterar.setBorderPainted(false);
        alterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        alterar.addActionListener(e -> {

            try {
                DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate novaData = LocalDate.parse(inserirNovaDataNascimento.getText(), formatar);

                String senhaText = new String(inserirNovaSenha.getPassword());

                AlterarConta.alterar(idUsuario, telaConta,telaAlterar, telaAlterar, telaPrincipal, 
                inserirNovoNome.getText(), inserirNomeCompleto.getText(), novaData, senhaText,
                inserirNovaRua.getText(), inserirNovoNumero.getText(), inserirNovoComplemento.getText(), 
                inserirNovaCidade.getText(), inserirNovoEstado.getText(), inserirNovoCep.getText());
                
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(telaAlterar, "Data de nascimento inválida. Use o formato dd/MM/yyyy.");
            }
                
        });
        
        areaTexto.add(alterar, gbc);

        telaAlterar.add(areaTexto);

        telaAlterar.setVisible(true);

        placeholdeUsuario(idUsuario, telaAlterar, inserirNovoNome, inserirNomeCompleto, inserirNovaDataNascimento, inserirNovaSenha);
        placeholderEndereco(idUsuario, telaAlterar, inserirNovaRua, inserirNovoNumero, inserirNovoComplemento, inserirNovaCidade, inserirNovoEstado, inserirNovoCep);
        
    }

    public static void placeholdeUsuario(int idUsuario, JFrame telaAlterar,
                                        JTextField inserirNovoNome, JTextField inserirNovoNomeCompleto, JFormattedTextField inserirNovaDataNascimento,
                                        JPasswordField inserirNovaSenha){

        UsuarioModel usuario = UsuarioModel.pegarTodasInfo(idUsuario, telaAlterar);
        
        if (usuario != null) {
            inserirNovoNome.setText(usuario.getNome());
            inserirNovoNomeCompleto.setText(usuario.getNomeCompleto());

            if (usuario.getDataNascimento() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataFormatada = usuario.getDataNascimento().format(formatter);
                inserirNovaDataNascimento.setText(dataFormatada);
            }
            inserirNovaSenha.setText(usuario.getSenha());
            
        }

    }

    public static void placeholderEndereco(int idUsuario, JFrame telaAlterar,
                                           JTextField inserirNovaRua, JTextField inserirNovoNumero, JTextField inserirNovoComplemento,
                                           JTextField inserirNovaCidade, JTextField inserirNovoEstado, JTextField inserirNovoCep){
        Endereco endereco = Endereco.pegarTodasInfo(idUsuario, telaAlterar);

        if (endereco != null) {
            inserirNovaRua.setText(endereco.getRua());
            inserirNovoNumero.setText(endereco.getNumero());
            inserirNovoComplemento.setText(endereco.getComplemento());
            inserirNovaCidade.setText(endereco.getCidade());
            inserirNovoEstado.setText(endereco.getEstado());
            inserirNovoCep.setText(endereco.getCep());
        }
    }
}

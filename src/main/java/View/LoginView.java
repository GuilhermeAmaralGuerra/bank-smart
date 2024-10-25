package View;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;

import Controller.Auntenticacao;

public class LoginView extends JFrame {

    public static void main(String[] args) {
        JFrame telaLogin = new JFrame("Login");
        telaLogin.setSize(500, 350);
        telaLogin.setLocationRelativeTo(null);
        telaLogin.setResizable(true);
        telaLogin.setBackground(Color.DARK_GRAY);
        telaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaLogin.setLayout(new GridBagLayout());

        JPanel areaComp = new JPanel();
        areaComp.setLayout(new GridBagLayout());
        areaComp.setBackground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 20, 5); 
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Login");
        titulo.setFont(new Font("Verdana", Font.BOLD, 20));
        gbc.gridx = 1;
        gbc.gridy = 0;
        areaComp.add(titulo, gbc);

        JLabel textoNome = new JLabel("Insira seu primeiro nome aqui: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        areaComp.add(textoNome, gbc);

        JTextField nome = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        areaComp.add(nome, gbc);

        JLabel textoCpf = new JLabel("Insira seu cpf aqui: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        areaComp.add(textoCpf, gbc);

        MaskFormatter cpfMask = null;
        try {
            cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JFormattedTextField cpf = new JFormattedTextField(cpfMask);
        gbc.gridx = 1;
        gbc.gridy = 2;
        areaComp.add(cpf, gbc);

        JLabel textoSenha = new JLabel("Insira sua senha aqui:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        areaComp.add(textoSenha, gbc);

        JPasswordField senha = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        areaComp.add(senha, gbc);

        JButton cadastrar = new JButton("Cadastrar");
        gbc.gridx = 0;
        gbc.gridy = 4;
        cadastrar.setBackground(Color.BLACK);
        cadastrar.setForeground(Color.WHITE);
        cadastrar.setFocusPainted(false);
        cadastrar.setBorderPainted(false);
        cadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cadastrar.addActionListener(e -> CadastroView.cadastro());
        areaComp.add(cadastrar, gbc);

        JButton logar = new JButton("Logar");
        gbc.gridx = 2;
        gbc.gridy = 4;
        logar.setBackground(Color.BLACK);
        logar.setForeground(Color.WHITE);
        logar.setFocusPainted(false);
        logar.setBorderPainted(false);
        logar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logar.addActionListener(e -> Auntenticacao.autenticarUsuario(telaLogin, telaLogin, nome.getText(), cpf.getText(), senha.getPassword()));
        areaComp.add(logar, gbc);

        telaLogin.add(areaComp, new GridBagConstraints());

        telaLogin.setVisible(true);
    }

    
}

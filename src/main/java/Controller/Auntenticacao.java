package Controller;

import javax.swing.JFrame;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Component;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

import Model.ConnectionFactory;
import Model.UsuarioModel;
import View.PrincipalView;

public class Auntenticacao {

    private static int idUsuario = -1;

    public static void autenticarUsuario(Component parentComponent, JFrame telaLogin, String nome, String cpf, char[] senha){
        try(Connection conn = ConnectionFactory.getConexao()) {

            String senhaFornecida = new String(senha);

            PreparedStatement stmtAutenticar = conn.prepareStatement("SELECT senha FROM usuario WHERE nome = ? AND cpf = ? AND status = 'ativo'");

            String nomeSemEspaco = nome.trim();

            stmtAutenticar.setString(1, nomeSemEspaco);
            stmtAutenticar.setString(2, cpf);

            try (ResultSet rs = stmtAutenticar.executeQuery()){
                if (rs.next()) {
                    String senhaSaltHashArmazenada = rs.getString("senha");

                    idUsuario = UsuarioModel.armazenarUsuario(parentComponent, cpf);

                    if (verificarSenha(senhaFornecida, senhaSaltHashArmazenada)) {
                        idUsuario = UsuarioModel.armazenarUsuario(parentComponent, cpf);
    
                        if (idUsuario != -1) {
                            PrincipalView.telaBanco(idUsuario, nome);
                            telaLogin.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(parentComponent, "Usuário não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(parentComponent, "Login ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(parentComponent, "Usuário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(parentComponent, "Erro", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static boolean verificarSenha(String senhaFornecida, String senhaSaltHashArmazenado) {
        
        String[] partes = senhaSaltHashArmazenado.split(":");
        String saltArmazenado = partes[0];
        String hashArmazenado = partes[1];

        String hashGerado = criptografarSenhaComSalt(senhaFornecida, saltArmazenado);

        return hashGerado.equals(hashArmazenado);
    }

    public static String criptografarSenhaComSalt(String senha, String salt) {
        try {
            
            String senhaComSalt = senha + salt;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(senhaComSalt.getBytes());

            StringBuilder hexSenha = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexSenha.append('0');
                hexSenha.append(hex);
            }

            return hexSenha.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}

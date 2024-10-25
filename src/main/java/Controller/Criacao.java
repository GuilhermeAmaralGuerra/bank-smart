package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.awt.Component;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.swing.JOptionPane;
import java.util.Base64;
import java.util.Random;

import Model.ConnectionFactory;

public class Criacao {
    public static void criarUsuario(Component parentComponent, String nome, String nomeCompleto, String senha, String cpf,
                                    LocalDate nascimento, String rua, String numero, String cidade, String estado, String complemento,
                                    String cep){
        
        if (!Criacao.isCPF(cpf)) {
            JOptionPane.showMessageDialog(parentComponent, "CPF inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try (Connection conn = ConnectionFactory.getConexao()) {
            conn.setAutoCommit(false);

            PreparedStatement stmtUsuario = conn.prepareStatement(
                    "INSERT INTO usuario(nome, nome_completo, senha, data_nascimento, CPF) VALUES (?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            PreparedStatement stmtEndereco = conn.prepareStatement(
                    "INSERT INTO endereco(usuario_id, rua, numero, complemento, cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            PreparedStatement stmtContaBancaria = conn.prepareStatement(
                    "INSERT INTO conta_bancaria(usuario_id, numero_conta, data_abertura, codigo_transferencia) VALUES (?, ?, ?, ?)"
            );

            String salt = Criacao.gerarSalt();
            String senhaCriptografada = Criacao.criptografarSenhaComSalt(senha, salt);
            String senhaVerdadeira = Criacao.combinarSaltEHash(salt, senhaCriptografada);

            String nomeSemEspaco = nome.trim();

            stmtUsuario.setString(1, nomeSemEspaco);
            stmtUsuario.setString(2, nomeCompleto);
            stmtUsuario.setString(3, senhaVerdadeira);
            stmtUsuario.setDate(4, java.sql.Date.valueOf(nascimento));
            stmtUsuario.setString(5, cpf);

            int linhasUsuarios = stmtUsuario.executeUpdate();

            int usuarioId = -1;
            if (linhasUsuarios > 0) {
                ResultSet generatedKeys = stmtUsuario.getGeneratedKeys();
                if (generatedKeys.next()) {
                    usuarioId = generatedKeys.getInt(1);
                }
            }

            stmtEndereco.setInt(1, usuarioId); 
            stmtEndereco.setString(2, rua);
            stmtEndereco.setString(3, numero);
            stmtEndereco.setString(4, complemento);
            stmtEndereco.setString(5, cidade);
            stmtEndereco.setString(6, estado);
            stmtEndereco.setString(7, cep);

            int linhasEndereco = stmtEndereco.executeUpdate();

            Random random = new Random();
            int randomNumero = 100000 + random.nextInt(900000);

            long randomCodigo = 1000000000L + (long)(random.nextDouble() * (9999999999L - 1000000000L));

            stmtContaBancaria.setInt(1, usuarioId);
            stmtContaBancaria.setInt(2, randomNumero);
            stmtContaBancaria.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            stmtContaBancaria.setLong(4, randomCodigo);
            
            int linhasContaBancaria = stmtContaBancaria.executeUpdate();

            if (linhasUsuarios > 0 && linhasEndereco > 0 && linhasContaBancaria > 0) {
                conn.commit();
                JOptionPane.showMessageDialog(parentComponent, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                conn.rollback();
                JOptionPane.showMessageDialog(parentComponent, "Falha ao realizar o cadastro.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static String gerarSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
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

    public static String combinarSaltEHash(String salt, String hash) {
        return salt + ":" + hash;
    }

    public static boolean isCPF(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");
    
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
    
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int primeiroDigitoVerificador = 11 - (soma % 11);
        if (primeiroDigitoVerificador > 9) {
            primeiroDigitoVerificador = 0;
        }
    
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int segundoDigitoVerificador = 11 - (soma % 11);
        if (segundoDigitoVerificador > 9) {
            segundoDigitoVerificador = 0;
        }
    
        return (primeiroDigitoVerificador == (cpf.charAt(9) - '0')) &&
               (segundoDigitoVerificador == (cpf.charAt(10) - '0'));
    }

}

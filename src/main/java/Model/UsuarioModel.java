package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Component;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class UsuarioModel {
    private int id;
    private String nome;
    private String nomeCompleto;
    private String senha;
    private LocalDate dataNascimento;
    private String cpf;
    
    public UsuarioModel(int id, String nome, String nomeCompleto, String senha, LocalDate dataNascimento, String cpf) {
        this.id = id;
        this.nome = nome;
        this.nomeCompleto = nomeCompleto;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNomeCompleto() {
        return nomeCompleto;
    }
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public static UsuarioModel pegarTodasInfo(int idUsuario, Component parentComponent){
        UsuarioModel usuario = null;

        try (Connection conn = ConnectionFactory.getConexao()){
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuario WHERE id = ?");

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String nomeCompleto = rs.getString("nome_completo");
                String senha = rs.getString("senha");
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
                String cpf = rs.getString("cpf");

            usuario = new UsuarioModel(id, nome, nomeCompleto, senha, dataNascimento, cpf);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return usuario;
    }

    public static int armazenarUsuario(Component parentComponent, String cpf){
        int idUsuario = -1;

        try (Connection conn = ConnectionFactory.getConexao()){
            PreparedStatement stmt = conn.prepareStatement("SELECT id FROM usuario WHERE cpf = ?");

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    idUsuario = rs.getInt("id");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(parentComponent, "Erro ao armazenar usuário", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return idUsuario;
    }

    public static String pegarNome(Component parentComponent, int idUsuario){
        String nome = "";

        try (Connection conn = ConnectionFactory.getConexao()){
            PreparedStatement stmt = conn.prepareStatement("SELECT nome FROM usuario WHERE id = ?");

            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    nome = rs.getString("nome");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(parentComponent, "Erro ao armazenar usuário", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return nome;
    }

}

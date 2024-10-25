package Controller;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.Component;
import java.time.LocalDate;
import Model.ConnectionFactory;
import Model.UsuarioModel;
import View.PrincipalView;

public class AlterarConta {
    public static void alterar(int idUsuario, JFrame telaConta,JFrame telaAlterar, Component parentComponent, JFrame telaPrincipal,
                               String inserirNovoNome, String inserirNomeCompleto, LocalDate novaData, String novaSenha,
                               String inserirNovaRua, String inserirNovoNumero, String inserirNovoComplemento, 
                               String inserirNovaCidade, String inserirNovoEstado, String inserirNovoCep){

        try (Connection conn = ConnectionFactory.getConexao()){

            PreparedStatement stmtUsuario = conn.prepareStatement(
                "UPDATE usuario SET nome = ?, nome_completo = ?, data_nascimento = ?, senha = ? WHERE id = ?"
                );

            PreparedStatement stmtEndereco = conn.prepareStatement(
                "UPDATE endereco SET rua = ?, numero = ?, complemento = ?, cidade = ?, estado = ?, cep = ? WHERE usuario_id = ?"
                );

            stmtUsuario.setString(1, inserirNovoNome);
            stmtUsuario.setString(2, inserirNomeCompleto);
            stmtUsuario.setDate(3, java.sql.Date.valueOf(novaData));
            stmtUsuario.setString(4, novaSenha);
            stmtUsuario.setInt(5, idUsuario);
            int linhasUsuario = stmtUsuario.executeUpdate();

            if (linhasUsuario > 0) {
                stmtEndereco.setString(1, inserirNovaRua);
                stmtEndereco.setString(2, inserirNovoNumero);
                stmtEndereco.setString(3, inserirNovoComplemento);
                stmtEndereco.setString(4, inserirNovaCidade);
                stmtEndereco.setString(5, inserirNovoEstado);
                stmtEndereco.setString(6, inserirNovoCep);
                stmtEndereco.setInt(7, idUsuario);
                int linhasEmprestimo = stmtEndereco.executeUpdate();

                if (linhasEmprestimo > 0) {
                    JOptionPane.showMessageDialog(parentComponent, "Alteração salva com sucesso.", "Deu certo!", JOptionPane.INFORMATION_MESSAGE);
                    telaPrincipal.setVisible(false);
                    telaAlterar.setVisible(false);
                    telaConta.setVisible(false);
                    String nome = UsuarioModel.pegarNome(parentComponent, idUsuario);
                    PrincipalView.telaBanco(idUsuario, nome);
                }
                else {
                    JOptionPane.showMessageDialog(parentComponent, "Nenhuma alteração foi feita no usuário.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(parentComponent, "Nenhuma alteração foi feita no usuário.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

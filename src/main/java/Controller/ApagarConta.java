package Controller;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Component;

import Model.ConnectionFactory;
import Model.ContaBancaria;

public class ApagarConta {
    public static void apagar(int idUsuario, JFrame telaConta, JFrame telaPrincipal, JFrame telaApagar,Component parentComponent){

        int idConta = ContaBancaria.armazenarConta(parentComponent, idUsuario);

        try (Connection conn = ConnectionFactory.getConexao()){
            PreparedStatement stmtApagar = conn.prepareStatement("UPDATE usuario SET status = 'inativo' WHERE id = ? AND status = 'ativo' ");
            PreparedStatement stmtEmprestimo = conn.prepareStatement("SELECT * FROM emprestimo WHERE usuario_id = ? AND status = 'pendente'");
            PreparedStatement stmtSaldo = conn.prepareStatement("UPDATE conta_bancaria SET saldo = '0.00' WHERE id = ?");

            stmtEmprestimo.setInt(1, idUsuario);
            ResultSet rs = stmtEmprestimo.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(parentComponent, "Pague os empréstimos antes de apagar a conta", "Erro", JOptionPane.INFORMATION_MESSAGE);
            }else{
                stmtApagar.setInt(1, idUsuario);
                int linhasApagar = stmtApagar.executeUpdate();

                stmtSaldo.setInt(1, idConta);
                int linhasSaldo = stmtSaldo.executeUpdate();

            if (linhasApagar > 0 && linhasSaldo > 0) {
                JOptionPane.showMessageDialog(parentComponent, "Usuário inativado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                telaPrincipal.setVisible(false);
                telaApagar.setVisible(false);
                telaConta.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(parentComponent, "Usuário não encontrado ou já inativado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

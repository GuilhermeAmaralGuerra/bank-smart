package Controller;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.Component;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import Model.ConnectionFactory;
import Model.ContaBancaria;
import Model.UsuarioModel;
import View.PrincipalView;


public class EfetuarSaque {
    public static void fazerSaque(JFrame telaSaque, Component parentComponent, String valorSaque, float verSaldo, int idUsuario, JFrame telaPrincipal){
        try (Connection conn = ConnectionFactory.getConexao()){

            float saque = Float.parseFloat(valorSaque);
            int idConta = ContaBancaria.armazenarConta(parentComponent, idUsuario);
            LocalDateTime dataTransferencia = LocalDateTime.now();
            Timestamp dataHora = Timestamp.valueOf(dataTransferencia);

            if (saque > verSaldo) {
                JOptionPane.showMessageDialog(parentComponent, "seu saldo é inferior ao valor do saque", "Operação inválida", JOptionPane.ERROR_MESSAGE);
            } else{
                PreparedStatement stmtContaBancaria = conn.prepareStatement("UPDATE conta_bancaria SET saldo = saldo - ? WHERE id = ?");

                stmtContaBancaria.setFloat(1, saque);
                stmtContaBancaria.setInt(2, idConta);
                int linhas = stmtContaBancaria.executeUpdate();

                if (linhas == 0) {
                    throw new Exception("Erro ao somar saldo da conta remetente.");
                }

                PreparedStatement stmtSaque = conn.prepareStatement("INSERT INTO saque(conta_id, valor_saque, data_saque) VALUES (?, ?, ?)");

                stmtSaque.setInt(1, idConta);
                stmtSaque.setFloat(2, saque);
                stmtSaque.setTimestamp(3, dataHora);
                int linhasSaque = stmtSaque.executeUpdate();

                if (linhasSaque > 0) {
                    JOptionPane.showMessageDialog(parentComponent, "Saque realizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    telaSaque.setVisible(false);
                    String nome = UsuarioModel.pegarNome(parentComponent, idUsuario);
                    telaPrincipal.setVisible(false);
                    PrincipalView.telaBanco(idUsuario, nome);
                }
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

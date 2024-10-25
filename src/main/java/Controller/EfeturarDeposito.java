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

public class EfeturarDeposito {
    public static void fazerDeposito(JFrame telaDeposito, Component parentComponent, JFrame telaPrincipal, String valorDeposito, int idUsuario){
        try (Connection conn = ConnectionFactory.getConexao()){

            float deposito = Float.parseFloat(valorDeposito);
            int idConta = ContaBancaria.armazenarConta(parentComponent, idUsuario);
            LocalDateTime dataTransferencia = LocalDateTime.now();
            Timestamp dataHora = Timestamp.valueOf(dataTransferencia);

            PreparedStatement stmtContaBancaria = conn.prepareStatement("UPDATE conta_bancaria SET saldo = saldo + ? WHERE id = ?");

            stmtContaBancaria.setFloat(1, deposito);
            stmtContaBancaria.setInt(2, idConta);
            int linhasConta = stmtContaBancaria.executeUpdate();

            if (linhasConta == 0) {
                throw new Exception("Erro ao somar saldo da conta remetente.");
            }

            PreparedStatement stmtDeposito = conn.prepareStatement("INSERT INTO deposito(conta_id, valor_deposito, data_deposito) VALUES(?, ? ,?)");

            stmtDeposito.setInt(1, idConta);
            stmtDeposito.setFloat(2, deposito);
            stmtDeposito.setTimestamp(3, dataHora);
            int linhasDeposito = stmtDeposito.executeUpdate();

                if (linhasDeposito > 0) {
                    JOptionPane.showMessageDialog(parentComponent, "Depósito realizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    telaDeposito.setVisible(false);
                    String nome = UsuarioModel.pegarNome(parentComponent, idUsuario);
                    telaPrincipal.setVisible(false);
                    PrincipalView.telaBanco(idUsuario, nome);
                }


        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }
}

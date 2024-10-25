package Controller;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Component;
import java.time.LocalDateTime;
import java.sql.Timestamp;

import Model.*;
import View.PrincipalView;

public class EfetuarTransacao {
    public static void fazerTransacao(JFrame telaTransfer, int idUsuario, String inserirDigitarCodigo, String transacaoSelecionada, String inserirValor, String inserirDescricao, Component parentComponent){

        try (Connection conn = ConnectionFactory.getConexao()){

            long codigo = Long.parseLong(inserirDigitarCodigo);
            double valor = Double.parseDouble(inserirValor);
            double valorSaldo = ContaBancaria.pegarSaldo(idUsuario, telaTransfer);
            int idConta = ContaBancaria.armazenarConta(parentComponent, idUsuario);
            LocalDateTime dataTransferencia = LocalDateTime.now();
            Timestamp dataHora = Timestamp.valueOf(dataTransferencia);

            if (valor > valorSaldo) {
                JOptionPane.showMessageDialog(parentComponent, "seu saldo é inferior ao valor da transferência", "Operação inválida", JOptionPane.ERROR_MESSAGE);
            } else{
                PreparedStatement stmtVerificarContaDestino = conn.prepareStatement("SELECT id FROM conta_bancaria WHERE codigo_transferencia = ?");
                stmtVerificarContaDestino.setLong(1, codigo);
                ResultSet rsContaDestino = stmtVerificarContaDestino.executeQuery();

                if (!rsContaDestino.next()) {
                    JOptionPane.showMessageDialog(parentComponent, "Código de transferência inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            PreparedStatement stmtSubtrair = conn.prepareStatement(
            "UPDATE conta_bancaria SET saldo = saldo - ? WHERE id = ?"
            );

            stmtSubtrair.setDouble(1, valor);
            stmtSubtrair.setInt(2, idConta);
            int linhasSubtrair = stmtSubtrair.executeUpdate();

            if (linhasSubtrair == 0) {
                throw new Exception("Erro ao subtrair saldo da conta remetente.");
            }

            PreparedStatement stmtSomar = conn.prepareStatement(
            "UPDATE conta_bancaria SET saldo = saldo + ? WHERE codigo_transferencia = ?"
            );

            stmtSomar.setDouble(1, valor);
            stmtSomar.setLong(2, codigo);
            int linhasSomar = stmtSomar.executeUpdate();

            if (linhasSomar == 0) {
                throw new Exception("Erro ao somar saldo da conta remetente.");
            }

            PreparedStatement stmtCriar = conn.prepareStatement(
                "INSERT INTO transacao(conta_id, tipo_transacao, valor, data_transacao, descricao) VALUES (?, ?, ?, ?, ?)"
            );

            stmtCriar.setInt(1, idConta);
            stmtCriar.setString(2, transacaoSelecionada);
            stmtCriar.setDouble(3, valor);
            stmtCriar.setTimestamp(4, dataHora);
            stmtCriar.setString(5, inserirDescricao);

            int linhasAfetadas = stmtCriar.executeUpdate();

            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(parentComponent, "Transação realizada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                telaTransfer.setVisible(false);
                String nome = UsuarioModel.pegarNome(parentComponent, idUsuario);
                PrincipalView.telaBanco(idUsuario, nome);
            } else {
                JOptionPane.showMessageDialog(parentComponent, "Erro ao realizar a transação.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}


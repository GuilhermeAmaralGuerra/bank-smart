package Controller;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.awt.Component;
import Model.*;
import View.PrincipalView;

public class EfetuarEmprestimo {

    public static void fazerEmprestimo(JFrame telaEmprestimo, Component parentComponent, float escolha, int idUsuario, JFrame telaPrincipal){
        try (Connection conn = ConnectionFactory.getConexao()){

            float valorJuros = escolha + (escolha * (5f / 100f));
            int idConta = ContaBancaria.armazenarConta(parentComponent, idUsuario);

            PreparedStatement stmtVerificar = conn.prepareStatement("SELECT * FROM emprestimo WHERE usuario_id = ? AND status = 'pendente'");
            stmtVerificar.setInt(1, idUsuario);
            ResultSet rsVerificar = stmtVerificar.executeQuery();

            if (rsVerificar.next()) { 
                JOptionPane.showMessageDialog(parentComponent, "Você já tem um empréstimo pendente. Não é possível realizar um novo.", "Empréstimo Pendente", JOptionPane.WARNING_MESSAGE);
                return; 
            }

            PreparedStatement stmtEmprestimo = conn.prepareStatement("INSERT INTO emprestimo (usuario_id, valor, valor_juros, data_emprestimo) VALUES (?, ?, ?, ?)");
            PreparedStatement stmtSomar = conn.prepareStatement("UPDATE conta_bancaria SET saldo = saldo + ? WHERE id = ?");

            stmtSomar.setFloat(1, escolha);
            stmtSomar.setInt(2, idConta);
            

            stmtEmprestimo.setInt(1, idUsuario);
            stmtEmprestimo.setFloat(2, escolha);
            stmtEmprestimo.setFloat(3, valorJuros);
            stmtEmprestimo.setDate(4, java.sql.Date.valueOf(LocalDate.now()));

            int linhas = stmtEmprestimo.executeUpdate();

            if (linhas > 0) {
                
                int linhasConta = stmtSomar.executeUpdate();

                if (linhasConta == 0) {
                    throw new Exception("Erro ao somar saldo da conta remetente.");
                } else{
                    JOptionPane.showMessageDialog(parentComponent, "Emprestimo realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    String nome = UsuarioModel.pegarNome(parentComponent, idUsuario);
                    telaEmprestimo.setVisible(false);
                    telaPrincipal.setVisible(false);
                    PrincipalView.telaBanco(idUsuario, nome);
                }
            } else {
                JOptionPane.showMessageDialog(parentComponent, "Falha ao realizar o emprestimo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

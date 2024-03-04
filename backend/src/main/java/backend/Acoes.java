package backend;
import java.util.*;
import java.sql.*;

public class Acoes {
    private Scanner ler = new Scanner(System.in);

    public void criarConta(Connection conexao) {
        System.out.println("Insira seu nome de usuário e senha.");
        System.out.println("Nome de usuário: ");
        String user = ler.next();
        System.out.println("\nSenha: ");
        String password = ler.next();
        createAccountWithUserAndPassword(conexao, user, password);
    }

    public void login(Connection conexao) {
        System.out.println("Insira seu nome de usuário e senha.");
        System.out.println("Nome de usuário: ");
        String user = ler.next();
        System.out.println("\nSenha: ");
        String password = ler.next();
        boolean loginResult = loginAccountWithUserAndPassword(conexao, user, password);
        if (loginResult) {
            int id = getIDUser(conexao, user, password);
            double saldo = getSaldo(conexao, id);
            MainScreen screen = new MainScreen();
            screen.Screen(conexao, id, user, password, saldo);
        } else {
            System.out.println("Falha no login. Verifique suas credenciais.");
        }
    }
    public void addSaldo(Connection conexao, int id) {
    	System.out.println("Quanto quer adicionar?");
    	Double newSaldo = ler.nextDouble();
    	addSaldoForTheUserWithID(conexao, id, newSaldo);
    	
    }
    public void extrairSaldo(Connection conexao, int id) {
    	System.out.println("Quanto quer extrair?");
    	Double newSaldo = ler.nextDouble();
    	extrairSaldoForTheUserWithID(conexao, id, newSaldo);
    	
    }

    private void createAccountWithUserAndPassword(Connection conexao, String user, String password) {
        try {
            String sql = "INSERT INTO usuariosbanco (nome, senha) VALUES (?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, password);
            statement.executeUpdate();
            System.out.println("Conta criada com sucesso para o usuário: " + user);
        } catch (SQLException err) {
            System.out.println("Erro ao criar conta. Detalhes: " + err.getMessage());
            err.printStackTrace();
        }
    }

    private boolean loginAccountWithUserAndPassword(Connection conexao, String user, String password) {
        try {
            String sql = "SELECT * FROM usuariosbanco WHERE nome = ? AND senha = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, password);
            ResultSet resultset = statement.executeQuery();
            return resultset.next();
        } catch (SQLException err) {
            System.out.println("Erro ao tentar fazer login. Detalhes: " + err.getMessage());
            err.printStackTrace();
            return false;
        }
    }

    private double getSaldo(Connection conexao, int id) {
        try {
            String sql = "SELECT saldo FROM usuariosbanco WHERE id = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultset = statement.executeQuery();
            return resultset.next() ? resultset.getDouble("saldo") : 0.0;
        } catch (SQLException err) {
            System.out.println("Erro ao tentar obter o saldo. Detalhes: " + err.getMessage());
            err.printStackTrace();
            return 0.0;
        }
    }

    private int getIDUser(Connection conexao, String user, String password) {
        try {
            String sql = "SELECT id FROM usuariosbanco WHERE nome = ? AND senha = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, password);
            ResultSet resultset = statement.executeQuery();
            return resultset.next() ? resultset.getInt("id") : -1;
        } catch (SQLException err) {
            System.out.println("Erro ao tentar obter o ID do usuário. Detalhes: " + err.getMessage());
            err.printStackTrace();
            return -1;
        }
    }
    private void addSaldoForTheUserWithID(Connection conexao, int id, double newSaldo) {
        try {
         
            String sql = "SELECT saldo FROM usuariosbanco WHERE id = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultset = statement.executeQuery();
            
            if (resultset.next()) {
                double oldSaldo = resultset.getDouble("saldo");
                double saldoAtualizado = oldSaldo + newSaldo;

              
                String sqlUpdate = "UPDATE usuariosbanco SET saldo = ? WHERE id = ?";
                PreparedStatement statementUpdate = conexao.prepareStatement(sqlUpdate);
                statementUpdate.setDouble(1, saldoAtualizado);
                statementUpdate.setInt(2, id);
                statementUpdate.executeUpdate();
                System.out.println("Saldo atualizado com sucesso para o usuário com ID: " + id);
            }
        } catch (SQLException err) {
            System.out.println("Erro ao tentar adicionar saldo. Detalhes: " + err.getMessage());
            err.printStackTrace();
        }
    }
    private void extrairSaldoForTheUserWithID(Connection conexao, int id, double valorExtracao) {
        try {
            String sql = "SELECT saldo FROM usuariosbanco WHERE id = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultset = statement.executeQuery();
            
            if (resultset.next()) {
                double saldoAtual = resultset.getDouble("saldo");
                if (valorExtracao <= saldoAtual) {
                    double saldoAtualizado = saldoAtual - valorExtracao;

                    String sqlUpdate = "UPDATE usuariosbanco SET saldo = ? WHERE id = ?";
                    PreparedStatement statementUpdate = conexao.prepareStatement(sqlUpdate);
                    statementUpdate.setDouble(1, saldoAtualizado);
                    statementUpdate.setInt(2, id);
                    statementUpdate.executeUpdate();
                    System.out.println("Saldo atualizado com sucesso para o usuário com ID: " + id);
                } else {
                    System.out.println("Erro ao tentar extrair saldo. O valor de extração é maior do que o saldo disponível.");
                }
            }
        } catch (SQLException err) {
            System.out.println("Erro ao tentar extrair saldo. Detalhes: " + err.getMessage());
            err.printStackTrace();
        }
    }

}

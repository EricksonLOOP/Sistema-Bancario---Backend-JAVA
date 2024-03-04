package backend;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Acoes acoes = new Acoes();
        String response;
        Connection conexao = null;
        
        try {
            Conection conection = new Conection();
            conexao = conection.getConnection();
            
            do {
                System.out.println("Tela inicial");
                System.out.println(" 1. Criar conta \n 2. Login \n 3. Sair");
                response = ler.next();
                
                switch(response) {
                    case "1":
                        acoes.criarConta(conexao);
                        break;
                    case "2":
                        acoes.login(conexao);
                        break;
                    case "3":
                    	System.out.println("Saindo...");
                    default: 
                        System.out.println("Comando desconhecido. Tente novamente.");
                }
            } while(!response.equals("3"));
        } catch (SQLException err) {
            System.out.println("Erro ao iniciar a conexão. " +  err.getMessage());
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar a conexão. " + e.getMessage());
                }
            }
            ler.close();
        }
    }
}

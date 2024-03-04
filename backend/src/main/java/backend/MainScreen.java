package backend;
import java.sql.Connection;
import java.util.Scanner;
public class MainScreen {
	String response;
	Scanner ler = new Scanner(System.in);
	Acoes acoes = new Acoes();

	public void Screen(Connection conexao, int id, String user, String password, double saldo) {
		do {
			System.out.println("Olá, "+ user + "!");
			System.out.println(" Nome: " + user + "\n Senha: "+password+"\n Id: "+id+"\n Saldo: R$"+saldo);
			System.out.println("O que deseja fazer agora?");
			System.out.println(" 1. Adicionar Saldo \n 2. Extrair Saldo \n 3. Sair");
			response = ler.next();
			switch(response) {
			case "1": 
				acoes.addSaldo(conexao, id);;
				break;
			case "2": 
				acoes.extrairSaldo(conexao, id);
				break;
			case "3":
				System.out.println("Foi bom te ter aqui. Até mais, "+user+"!");
				break;
			default: System.out.println("Comando desconhecido!");
			}
		}while(!response.equals("3"));
		
	}
	

}

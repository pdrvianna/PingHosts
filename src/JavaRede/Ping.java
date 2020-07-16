package JavaRede;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JOptionPane;



public class Ping {
	
	static public void main (String rede[]) {
		
		//Scanner tcl = new Scanner(System.in);
		
		
		try {
			
			//String endereco = JOptionPane.showMessageDialog (null, "Digite o endereço do host a ser veificado.");
			//System.out.println("Digite o endereço ?");
			 
			//String endereco = tcl.nextLine();
			//tcl.close();
			String endereco = JOptionPane.showInputDialog("Digite o host: ");
			
			String arquivo = "comandoPing.txt";
			
			BufferedWriter saida = new BufferedWriter(new FileWriter(arquivo,false));
			saida.write("Endereco ip, Status");
			//saida.newLine();
			
			Process processo = Runtime.getRuntime().exec("ping -W3000 "+endereco);

				InputStream in = processo.getInputStream();
				BufferedReader entrada = new BufferedReader(new InputStreamReader(in));
				String linha = null;
				for (int i=0 ; i<2; i++) {
					linha = entrada.readLine();
		
				}	
	
				String ip=null;
				String[] str = linha.split(" ");
				for (int pro=0;pro<str.length;pro++) {
					if (str[pro].contains("from")) {
						ip=str[pro+1].replace(":", "");
					}
				}
				
		 if (ip != null) {		
				saida.write(endereco+","+ip);
				System.out.println(endereco+";"+ ip);
				JOptionPane.showMessageDialog(null,endereco+";"+ip);
				entrada.close();
			} else {
				saida.write(endereco+", Timeout");
				System.out.println(endereco+";Timeout");
				JOptionPane.showMessageDialog(null, endereco+"; Timeout");
			}
			saida.close();

		}
		catch (IOException exc) {
			System.err.println("Erro de E/S");
		}
		catch (Exception exc) {
			System.err.println ("Erro geral: "+exc.toString());
		}
		
	}

}

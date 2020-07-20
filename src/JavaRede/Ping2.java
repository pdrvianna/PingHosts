package JavaRede;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Ping2 {
	
	public static void main(String[] args) {	

	String str;
	int i=0;

	String arqIps = "ips.txt";
	String arqIpsOut = "outIps.txt";
	lerArquivo tela = new lerArquivo();

	

	try {	

		BufferedReader ent = new BufferedReader(new FileReader(arqIps)); 
		
		//Gravacao em Array
		ArrayList<String> lstIps = new ArrayList<String>();
		str = ent.readLine();
		
		if (str != null) {
			
			do {
				lstIps.add(i,str);
				i++;
			}while((str = ent.readLine()) !=  null);
			
		} else {
			JOptionPane.showMessageDialog(null,"O arquivo "+arqIps+" está vazio ou não existe! Crie o arquivo com os hosts.","Informacao",JOptionPane.INFORMATION_MESSAGE);
		}
		
		ent.close();
		
		//Lendo/lendo do Array e fazendo ping. 
		for(int cont=0; cont<lstIps.size()-1;cont++) {
			String ret = ping2(lstIps.get(cont));
			System.out.println(ret);
			lstIps.set(cont,ret);
			tela.listar(ret);
			
		}
		
		//Escrevendo no arquivo
		if (escreve(lstIps,arqIpsOut)){

			JOptionPane.showMessageDialog(null, "Fim! Arquivo salvo em "+ arqIpsOut,"Informacao",JOptionPane.INFORMATION_MESSAGE);
			
		}else {

			JOptionPane.showMessageDialog(null, "Deu erro ao salvar!","Save erro",JOptionPane.INFORMATION_MESSAGE);
		}
		 
		
	}catch(java.io.FileNotFoundException ef) {
		JOptionPane.showMessageDialog(null, "O arquivo "+arqIps+" está vazio ou não existe! Crie o arquivo com os hosts.","File não encontrado",JOptionPane.INFORMATION_MESSAGE);
	}
	
	catch (Exception e){
		JOptionPane.showMessageDialog(null, "Erro geral: "+ e.toString(), "Informacao",JOptionPane.INFORMATION_MESSAGE);
	}
	}	

public static boolean escreve(ArrayList<String> ips, String arquivo) {
	
	try {	
		BufferedWriter saida = new BufferedWriter(new FileWriter(arquivo,false));
		saida.write("Endereco ip;Status;ip");
		saida.newLine();
		
		for (int i=0; i < ips.size(); i++ ) {
			saida.write(ips.get(i));
			saida.newLine();
			}
			saida.close();

		return true;
	} catch (Exception e) {
		return false;
	}

}
public static String ping2(String endereco) throws UnknownHostException, IOException {
	 
	String status; 
   InetAddress inet = InetAddress.getByName(endereco);
   if(inet.isReachable(null,64,5000)) {
   	status = "Reachable";    	
   }else {
   	status = "Timeout";
   }
   endereco = endereco+","+status+","+inet.getHostAddress();
  // System.out.println("retorno da ping2: "+endereco);
   
   return endereco;
}
/*
public static String ping1(String endereco) {
	String timeOut = "3000";
		try {
			Process processo = Runtime.getRuntime().exec("ping -W"+timeOut+" "+endereco);
		
			InputStream in = processo.getInputStream();
			BufferedReader entrada = new BufferedReader(new InputStreamReader(in));
			String linha0,linha1;
			linha0 = entrada.readLine();
			linha1 = entrada.readLine();
	
		
			String ip0 = null;
			String[] str0 = linha0.split(" ");
			ip0 = str0[2].substring(1).replace(")", "").replace("]", "");

			String ip=null;
			String[] str = linha1.split(" ");
			for (int pro=0;pro<str.length;pro++) {
				if (str[pro].contains("from")) {
					ip=str[pro+1].replace(":", "");
				}
			}
						
			
			if (ip != null) {		
				entrada.close();
				endereco = endereco + ",alcançou,"+ ip;
			
			} else {
				entrada.close();
				endereco = endereco +",timeout,"+ip0;
			}
			//saida.close();
			}
			catch (IOException exc) {
			System.err.println("Erro de E/S");
			}
			catch (Exception exc) {
			System.err.println ("Erro geral: "+exc.toString());
			}
		return endereco;
		
 
 }*/

}

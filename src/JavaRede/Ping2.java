package JavaRede;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class Ping2 {
	
	public static void main(String[] args) {	
	//String[] host = {"127.0.0.1","128.0.2.1","www.google.com","www.globo.com"};

	//Recomendo no minimo 3 segundos
	int timeOut = 3000;
	//int port = 8080;
	String str;
	int i=0;

	String arqIps = "ips.txt";
	String arqIpsOut = "outIps.txt";

	try {	
		int linhas=0;
		BufferedReader ent = new BufferedReader(new FileReader(arqIps)); 
		BufferedReader entc = new BufferedReader(new FileReader(arqIps)); 
		
		//Contagem de linhas para abrir o Array
		while ((str = entc.readLine()) != null) {
			linhas += 1;
		}
		
		String lstIps[] = new String[linhas];	
		//gravacao em Array
		str = ent.readLine();
		if (str != null) {
			
			do {
				lstIps[i] = str;
				//System.out.println("ip lido: "+str);
				i++;
						
			}while((str = ent.readLine()) !=  null);
		} else {
			System.out.println("o arquivo ips.txt está vazio ou não existe!");
		}
		ent.close();
		
		//Percorrengo o Array e testando cada ip
		for(int cont=0; cont<lstIps.length-1;cont++) {
			String ret = ping1(lstIps[cont]);
			System.out.println("Nova func: "+ ret );
			lstIps[cont]= ret;
		/* if(lstIps != null) {	
				if (doPing(lstIps[cont],timeOut)) {
					System.out.println(lstIps[cont]+";Ligado");
					lstIps[cont]=lstIps[cont]+";Ligado";
				} else{
					System.out.println(lstIps[cont]+";desligado");
					lstIps[cont]=lstIps[cont]+";Deligado";
				}
		 	}*/
		}
		
		if (escreve(lstIps,arqIpsOut)){
			System.out.println("Salvo em "+ arqIpsOut);
			//System.out.println("teste da ultima funcao: "+ ping1("www.globo.com"));
		}else {
			System.out.println("Deu erro ao salvar!");
		}
		 
		
	}catch(java.io.FileNotFoundException ef) {
		System.out.println("o arquivo "+ arqIps +" está vazio ou não existe!");
	}
	
	catch (Exception e){
		System.err.println ("Erro geral: "+ e.toString());
	}
	
	

/*	String h = host[2];
	
	if (doPing(h,timeOut)) {
		System.out.println(h+",Ligado");
	} else{
		System.out.println(h+",desligado");
	}*/
	
	
}	
	
public static boolean doPing(String host,int timeOut)
	{
		try {	
		//System.out.println("pegando o nome host: "+ InetAddress.getByName(host));
			
			return InetAddress.getByName(host).isReachable(timeOut);
					
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		return false;
		}
	}

public static boolean escreve(String[] ips, String arquivo) {
	
	try {	
		//String arquivo = "outPing.txt";
		BufferedWriter saida = new BufferedWriter(new FileWriter(arquivo,false));
		saida.write("Endereco ip;Status;ip");
		saida.newLine();
		
		for (int i=0; i < ips.length; i++ ) {
			saida.write(ips[i]);
			saida.newLine();
			}
			saida.close();

		return true;
	} catch (Exception e) {
		return false;
	}

}

public static String ping1(String endereco) {
	
		try {
			Process processo = Runtime.getRuntime().exec("ping -W3000 "+endereco);
		
			InputStream in = processo.getInputStream();
			BufferedReader entrada = new BufferedReader(new InputStreamReader(in));
			String linha0,linha1 = null;
			linha0=null;
			for (int i=0 ; i<2; i++) {
				if (i==0) { linha0 = entrada.readLine();}
				if (i==1) { linha1 = entrada.readLine();}
		
			}	
		
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
			//	saida.write(endereco+","+ip);
			//	System.out.println(endereco+";"+ ip);
				entrada.close();
				endereco = endereco + ";alcançou;"+ ip;
			
			} else {
		//		saida.write(endereco+", Timeout");
				//System.out.println(endereco+";Timeout");
				entrada.close();
				endereco = endereco +";timeout;"+ip0;
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
		
 
 }

}

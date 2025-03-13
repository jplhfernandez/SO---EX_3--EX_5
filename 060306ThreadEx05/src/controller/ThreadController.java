package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ThreadController extends Thread{
	
	private String endereco;
	private String nomeSite;
	private String[] tempoMedio;
	
	public ThreadController(String endereco) {
		this.endereco = endereco;
		if(endereco.contains("uol")) {
			nomeSite = "UOL";
		}
		else if(endereco.contains("terra")) {
			nomeSite = "TERRA";
		}
		else if(endereco.contains("google")) {
			nomeSite = "GOOGLE";
		}
	}
	
	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	@Override
	public void run() {
		String os = os();
		String processo = "ping -4 -c 10 " + endereco;
		try{
			if(!os.contains("Linux")){
				System.out.println("O sistema operacional não é Linux!");
			}
			else {
				String[] processoArray = processo.split(" ");
				Process p = Runtime.getRuntime().exec(processoArray);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while(linha != null){
					if(linha.contains("time=")) {
						System.out.println(nomeSite + ": " + linha + "\n");
					}
					if(linha.contains("avg")) {
						tempoMedio = linha.split("/");
						System.out.println("Tempo médio " + nomeSite + ": " + tempoMedio[4]);
					}
					linha =  buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			}
			
		}
		catch(Exception e1){
			System.err.println(e1.getMessage());
		}
	}
}

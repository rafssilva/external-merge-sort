import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;


public class OrdenaBlocos {
	
	
	public static void ordenar (int numRegistros,
			int numRegistrosBloco, String arquivoEntrada) throws IOException {
		
		
		BufferedReader entrada = new BufferedReader(new FileReader(arquivoEntrada));
		
		String arquivoSaida1 = "temp1.txt";
		String arquivoSaida2 = "temp2.txt";

		PrintWriter saida1 = new PrintWriter(arquivoSaida1, "ISO-8859-1");
		PrintWriter saida2 = new PrintWriter(arquivoSaida2, "ISO-8859-1");
		
		int registrosRestantes = numRegistros % numRegistrosBloco;
		int contador = 0;
		int numArquivoSaida = 1;
		
		ArrayList<CEP> listaCeps;
		
		String linha;
		
		
		while(contador < (numRegistros / numRegistrosBloco)) {
			
			listaCeps = new ArrayList<CEP>();
			
			for (int i = 0; i < numRegistrosBloco; i++) {
				
				if((linha = entrada.readLine()) != null) {
					
					String logradouro = linha.substring(0, 72).trim();
		            String bairro = linha.substring(72, 144).trim();
		            String cidade = linha.substring(144, 216).trim();
		            String uf = linha.substring(216, 288).trim();
		            String sigla = linha.substring(288, 290).trim();
		            String cep = linha.substring(290, 299).trim();
					
					listaCeps.add(new CEP(logradouro, bairro, cidade, uf,
							sigla, Long.parseLong(cep)));
				}
			}
			
			Collections.sort(listaCeps);
			
			
			if (numArquivoSaida == 1) {
				
				for (int i = 0; i < listaCeps.size(); i++) {

					saida1.write(listaCeps.get(i).toString());
					saida1.println();
				}
				
				numArquivoSaida = 2;
			}
			else {
				
				for (int i = 0; i < listaCeps.size(); i++) {
					
					saida2.write(listaCeps.get(i).toString());
					saida2.println();
				}
				
				numArquivoSaida = 1;
			}
			
			contador++;
		}
		
		if (registrosRestantes == 1) {
			
			listaCeps = new ArrayList<CEP>();
			
			if ((linha = entrada.readLine()) != null) {
				
				String logradouro = linha.substring(0, 72).trim();
				String bairro = linha.substring(72, 144).trim();
				String cidade = linha.substring(144, 216).trim();
				String uf = linha.substring(216, 288).trim();
				String sigla = linha.substring(288, 290).trim();
				String cep = linha.substring(290, 299).trim();
			
				listaCeps.add(new CEP(logradouro, bairro, cidade, uf,
						sigla, Long.parseLong(cep)));

				if (numArquivoSaida == 1) {
					
					saida1.write(listaCeps.get(0).toString());
					saida1.println();
				}
				else {

					saida2.write(listaCeps.get(0).toString());
					saida2.println();
				}
			}
		}
		else
			if (registrosRestantes < 1) {
			
				listaCeps = new ArrayList<CEP>();
			
				for (int i = 0; i < registrosRestantes; i++) {
				
					if((linha = entrada.readLine()) != null) {
					
						String logradouro = linha.substring(0, 72).trim();
						String bairro = linha.substring(72, 144).trim();
						String cidade = linha.substring(144, 216).trim();
						String uf = linha.substring(216, 288).trim();
						String sigla = linha.substring(288, 290).trim();
						String cep = linha.substring(290, 299).trim();
					
						listaCeps.add(new CEP(logradouro, bairro, cidade, uf,
								sigla, Long.parseLong(cep)));
					}
				}
			
				Collections.sort(listaCeps);
			
				if (numArquivoSaida == 1) {
			
					for (int i = 0; i < listaCeps.size(); i++) {
				
						saida1.write(listaCeps.get(i).toString());
						saida1.println();
					}
				}
				else {
				
					for (int i = 0; i < listaCeps.size(); i++) {
					
						saida2.write(listaCeps.get(i).toString());
						saida2.println();
					}
				}
		}
		
		entrada.close();
		saida1.close();
		saida2.close();
		
		TwoWayMerge.merge(arquivoSaida1, arquivoSaida2, numRegistrosBloco);
	}
}
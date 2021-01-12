import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;


public class TwoWayMerge {
	
	
	public static void merge(String arquivoEntrada1,
			String arquivoEntrada2, int numRegistrosBloco) throws IOException {
		
		
		String arquivo1 = arquivoEntrada1;
		String arquivo2 = arquivoEntrada2;
		String arquivo3 = "temp3.txt";
		String arquivo4 = "temp4.txt";
		
		BufferedReader entrada1;
		BufferedReader entrada2;
		
		PrintWriter saida1;
		PrintWriter saida2;
		
		ArrayList<CEP> listaCeps;
		
		int numArquivoSaida = 3;
		
		
		while (Files.size(Paths.get(arquivo2)) != 0) {
			
			entrada1 = new BufferedReader(new FileReader(arquivo1));
			entrada2 = new BufferedReader(new FileReader(arquivo2));
			
			saida1 = new PrintWriter(arquivo3, "ISO-8859-1");
			saida2 = new PrintWriter(arquivo4, "ISO-8859-1");

			numRegistrosBloco *= 2;
			
			String linha = "";
			
			
			while (linha != null) {
				
				listaCeps = new ArrayList<CEP>();
			
				for (int i = 0; i < numRegistrosBloco; i++) {
			
					if((linha = entrada1.readLine()) != null) {
				
						String[] array = linha.split("\\s{2,}");
						
						listaCeps.add(new CEP(array[0], array[1], array[2],
								array[3], array[4], Long.parseLong(array[5])));
					}
				}
		
				for (int i = 0; i < numRegistrosBloco; i++) {
			
					if((linha = entrada2.readLine()) != null) {
				
						String[] array = linha.split("\\s{2,}");
						
						listaCeps.add(new CEP(array[0], array[1], array[2],
								array[3], array[4], Long.parseLong(array[5])));
					}
				}
		
				Collections.sort(listaCeps);
			
				
				if (numArquivoSaida == 3) {
		
					for (int i = 0; i < listaCeps.size(); i++) {
			
						saida1.write(listaCeps.get(i).toString());
						saida1.println();
					}
				
					numArquivoSaida = 4;
				}
				else {
				
					for (int i = 0; i < listaCeps.size(); i++) {
					
						saida2.write(listaCeps.get(i).toString());
						saida2.println();
					}
				
					numArquivoSaida = 3;
				}
			}
			
			entrada1.close();
			entrada2.close();
			saida1.close();
			saida2.close();
					
			if (arquivo1.equals("temp1.txt")) {
				
				arquivo1 = "temp3.txt";
				arquivo2 = "temp4.txt";
				arquivo3 = "temp1.txt";
				arquivo4 = "temp2.txt";
			}
			else {
				
				arquivo1 = "temp1.txt";
				arquivo2 = "temp2.txt";
				arquivo3 = "temp3.txt";
				arquivo4 = "temp4.txt";
			}
		}
		
		System.out.println("Os registros ficaram organizados"
				+ " no arquivo: " + arquivo1);
	}
}
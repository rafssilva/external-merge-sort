import java.io.IOException;

public class Principal {
	
	
	public static void main(String[] args) throws IOException {
		
		int numRegistros = 500;
		int numRegistrosBloco = 3;
		
		OrdenaBlocos.ordenar(numRegistros, numRegistrosBloco, "cep.dat");
	}
}
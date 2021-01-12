
public class CEP implements Comparable<CEP> {
	
	private String logradouro;
	private String bairro;
	private String cidade;
	private String uf;
	private String sigla;
	private long cep;
	
	
	public CEP(String logradouro, String bairro, String cidade,
			String uf, String sigla, long cep) {
		
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.sigla = sigla;
		this.cep = cep;
	}
	
	
	public long getCep() {
		
		return this.cep;
	}
	
	
	@Override
	public String toString() {
		
		return logradouro + "  " + bairro + "  " + cidade + "  " + uf +
				"  " + sigla + "  " + cep;
	}
	
	
	@Override
	public int compareTo(CEP cep) {
		
		if(this.cep < cep.getCep()) {
			
			return -1;
		}
		
		if(this.cep > cep.getCep()) {
			
			return 1;
		}
		
		return 0;
	}
}
package Model;

public enum Mes {
	um("01"), dois("02"), tres("03"), quatro("04"), cinco("05"), seis("06"),
	sete("07"), oito("08"), nove("09"), dez("10"), onze("11"),
	Doze("12");

	Mes(String string) {
		this.nome = string;
	}

	private String nome;

	public String toString() { // devolvendo nome
		return nome;
	}
}

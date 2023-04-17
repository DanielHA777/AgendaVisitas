package Model;

public enum Dias {
	um("01"), dois("02"), tres("03"), quatro("04"), cinco("05"), seis("06"),
	sete("07"), oito("08"), nove("09"), dez("10"), onze("11"),
	Doze("12"),treze("13"),quatroze("14"),onze1("15"),um1("16"), dois1("17"), tres1("18"), quatro1("19"), cinco1("20"), seis1("21"),
	sete1("22"), oito1("23"), nove1("24"), dez1("25"), onze2("26"),
	Doze1("27"),treze1("28"),quatroze1("29"),onze11("30"),onze12("31");

	Dias(String string) {
		this.nome = string;
	}

	private String nome;

	public String toString() { // devolvendo nome
		return nome;
	}
}

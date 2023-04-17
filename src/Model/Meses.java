package Model;

public enum Meses {
	Janeiro("Janeiro"), Fevereiro("Fevereiro"), Março("Março"), Abril("Abril"), Maio("Maio"), Junho("Junho"),
	Julho("Julho"), Agosto("Agosto"), Setembro("Setembro"), Outubro("Outubro"), Novembro("Novembro"),
	Dezembro("Dezembro");

	Meses(String string) {
		this.nome = string;
	}

	private String nome;

	public String toString() { // devolvendo nome
		return nome;
	}

}

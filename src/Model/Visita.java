package Model;

public class Visita {

	private int id;
	private String data;
	private String hora; 
	private String visitante1;
	private String visitante2;
	private String visitante3;
	private String visitante4;
	private Visitante visitante;
	private Residente residente;
	private Dias dia;
	private String nomeResi;
	private String observacao;
	private Meses meses;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getVisitante1() {
		return visitante1;
	}
	public void setVisitante1(String visitante1) {
		this.visitante1 = visitante1;
	}
	public String getVisitante2() {
		return visitante2;
	}
	public void setVisitante2(String visitante2) {
		this.visitante2 = visitante2;
	}
	public String getVisitante3() {
		return visitante3;
	}
	public void setVisitante3(String visitante3) {
		this.visitante3 = visitante3;
	}
	public String getVisitante4() {
		return visitante4;
	}
	public void setVisitante4(String visitante4) {
		this.visitante4 = visitante4;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Meses getMeses() {
		return meses;
	}
	public void setMeses(Meses meses) {
		this.meses = meses;
	}
	public Dias getDia() {
		return dia;
	}
	public void setDia(Dias dias) {
		this.dia = dias;
	}
	public String getNomeResi() {
		return nomeResi;
	}
	public void setNomeResi(String nomeResi) {
		this.nomeResi = nomeResi;
	}
	public Residente getResidente() {
		return residente;
	}
	public void setResidente(Residente residente) {
		this.residente = residente;
	}
	public Visitante getVisitante() {
		return visitante;
	}
	public void setVisitante(Visitante visitante) {
		this.visitante = visitante;
	}
	
	
}

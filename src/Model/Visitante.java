package Model;

public class Visitante {
private int id;
private String nome;
private Residente residente;
private int residente_id;
private String nomeResi;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public Residente getResidente() {
	return residente;
}
public void setResidente(Residente residente) {
	this.residente = residente;
}
public int getResidente_id() {
	return residente_id;
}
public void setResidente_id(int residente_id) {
	this.residente_id = residente_id;
}
public String getNomeResi() {
	return nomeResi;
}
public void setNomeResi(String nomeResi) {
	this.nomeResi = nomeResi;
}


}

package it.polito.tdp.imdb.model;

public class Adiacenza {

	private Director d1;
	private Integer peso;
	public Adiacenza(Director d1, Integer peso) {
		super();
		this.d1 = d1;
		this.peso = peso;
	}
	public Director getD1() {
		return d1;
	}
	public void setD1(Director d1) {
		this.d1 = d1;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return  d1 + ", peso=" + peso+  "\n";
	}
	
	
}

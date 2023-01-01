package model;

import java.io.Serializable;
import java.util.Objects;

public class Animal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String nome;
	
	private String cor;
	
	private String especie;
	
	public Animal() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", nome=" + nome + ", cor=" + cor + ", especie=" + especie + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cor, especie, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return Objects.equals(cor, other.cor) && Objects.equals(especie, other.especie) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}
	

}

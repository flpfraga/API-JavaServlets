package model;

import java.io.Serializable;
import java.util.List;


public class ListaAnimais implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Animal> animais;
	
	public List<Animal> getAnimais(){
		return animais;
	}
	
	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	@Override
	public String toString() {
		return "ListaAnimais [animais=" + animais + "]";
	}
	


}

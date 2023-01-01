package service;

import java.util.List;

import dao.AnimalDAO;
import model.Animal;

public class AnimalService {

	private AnimalDAO dao = new AnimalDAO();
	
	public Animal findById(Long id) {
		try {
			return dao.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Animal findByNome(String nome) {
		try {
			return dao.getByName(nome);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Animal> getAll(){
		try {
			return dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Animal create(Animal animal) {
		try {
			return dao.save(animal);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Animal update(Animal animal, Long id) {
		Animal entity = findById(id);
		entity.setNome(animal.getNome());
		entity.setCor(animal.getCor());
		entity.setEspecie(animal.getEspecie());
		try {
			return dao.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void delete(Long id) {
		try {
			dao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

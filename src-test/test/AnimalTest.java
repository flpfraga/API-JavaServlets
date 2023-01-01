package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.Animal;
import service.AnimalService;

class AnimalTest {

private AnimalService service = new AnimalService();
	
	@Test
	public void testListAnimais() {
		
		List<Animal> animais = service.getAll();
		assertNotNull(animais);
		
		assertTrue(animais.size()>0);
		
		Animal animal = service.findByNome("Marrie");
		assertEquals("Marrie", animal.getNome());
		
	}
	
	@Test
	public void testeSalvarDeletarAnimal() {
		Animal animal = new Animal();
		animal.setNome("testeNome");
		animal.setCor("testeCor");
		animal.setEspecie("testeEspecie");
		
		animal = service.create(animal);
		
		Long id = animal.getId();
		
		assertNotNull(id);
		
		animal = service.findById(id);
		assertEquals("testeNome", animal.getNome());
		assertEquals("testeCor", animal.getCor());
		assertEquals("testeEspecie", animal.getEspecie());
		
		animal.setNome("testeNomeUpdate");
		service.create(animal);
		animal = service.findById(id);
		assertEquals("testeNomeUpdate", animal.getNome());
		
		service.delete(id);
		animal = service.findById(id);
		
		assertNull(animal);
		
	}
	


}

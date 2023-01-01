package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Animal;
import model.ListaAnimais;
import service.AnimalService;
import util.RegexUtil;
import util.ServletUtil;

@WebServlet("/animal/*")
public class AnimalServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AnimalService service = new AnimalService();

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String requestUri = req.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);
		
		service.delete(id);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String requestUri = req.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);

		if (id != null) {
			Animal animal = service.findById(id);

			if (animal != null) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(animal);
				ServletUtil.writeJSON(resp, json);
			} else {
				resp.sendError(404, "Animal não encontrado");
			}
		} else {
			List<Animal> animais = service.getAll();
			ListaAnimais lista = new ListaAnimais();
			lista.setAnimais(animais);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(lista);

			ServletUtil.writeJSON(resp, json);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Animal animal = getAnimalFromRequest(req);
		
		service.create(animal);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(animal);
		ServletUtil.writeJSON(resp, json);
		
	}
	
	private Animal getAnimalFromRequest(HttpServletRequest request) {
		Animal a = new Animal();
		String id = request.getParameter("id");
		if (id != null) {
			a = service.findById(Long.parseLong(id));
		}
		
		a.setNome(request.getParameter("nome"));
		a.setCor(request.getParameter("cor"));
		a.setEspecie(request.getParameter("especie"));
		return a;
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Animal animal = getAnimalFromRequest(req);
		String requestUri = req.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);
		animal = service.update(animal, id);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(animal);
		ServletUtil.writeJSON(resp, json);
	}

}

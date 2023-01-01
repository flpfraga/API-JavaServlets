package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Animal;

public class AnimalDAO extends DefaultDAO {

	public Animal getById(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from animais where id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Animal a = createAnimal(rs);
				rs.close();
				return a;
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	public Animal getByName(String nome) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from animais where nome=?");
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Animal a = createAnimal(rs);
				rs.close();
				return a;
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public List<Animal> getAll() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Animal> animais = new ArrayList<>();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from animais");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Animal a = createAnimal(rs);
				animais.add(a);
			}
			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return animais;
	}

	public Animal save(Animal animal) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (animal.getId() == null) {
				stmt = conn.prepareStatement("insert into animais (nome, cor, especie) VALUES (?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement("update animais set nome=?, cor=?,especie=? where id=?",
						Statement.RETURN_GENERATED_KEYS);
			}

			stmt.setString(1, animal.getNome());
			stmt.setString(2, animal.getCor());
			stmt.setString(3, animal.getEspecie());
			if (animal.getId() != null) {
				stmt.setLong(4, animal.getId());
			}

			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao tentar inserir o animal");
			}

			if (animal.getId() == null) {
				Long id = getGeneratedId(stmt);
				animal.setId(id);
			}
			return animal;

		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public boolean delete(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("delete from animais where id=?");
			stmt.setLong(1, id);
			int count = stmt.executeUpdate();
			return ((count > 0) ? true : false);

		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

	}

	public static Long getGeneratedId(Statement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			Long id = rs.getLong(1);
			return id;
		}
		return 0L;
	}

	public Animal createAnimal(ResultSet rs) throws SQLException {
		Animal a = new Animal();
		a.setId(rs.getLong("id"));
		a.setNome(rs.getString("nome"));
		a.setCor(rs.getString("cor"));
		a.setEspecie(rs.getString("especie"));
		return a;
	}

}

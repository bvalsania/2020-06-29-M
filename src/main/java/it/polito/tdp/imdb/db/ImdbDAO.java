package it.polito.tdp.imdb.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.imdb.model.Actor;
import it.polito.tdp.imdb.model.Coppia;
import it.polito.tdp.imdb.model.Director;
import it.polito.tdp.imdb.model.Movie;

public class ImdbDAO {
	
	public List<Actor> listAllActors(){
		String sql = "SELECT * FROM actors";
		List<Actor> result = new ArrayList<Actor>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Actor actor = new Actor(res.getInt("id"), res.getString("first_name"), res.getString("last_name"),
						res.getString("gender"));
				
				result.add(actor);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Movie> listAllMovies(){
		String sql = "SELECT * FROM movies";
		List<Movie> result = new ArrayList<Movie>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Movie movie = new Movie(res.getInt("id"), res.getString("name"), 
						res.getInt("year"), res.getDouble("rank"));
				
				result.add(movie);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void listAllDirectors(Map<Integer, Director> idMap){
		String sql = "SELECT * FROM directors";
		//List<Director> result = new ArrayList<Director>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				if(!idMap.containsKey(res.getInt("id"))) {
					Director director = new Director(res.getInt("id"), res.getString("first_name"), res.getString("last_name"));
				
				idMap.put(res.getInt("id"), director);
				}
			}
			conn.close();
		//	return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return ;
		}
	}
	

	public List<Director> getv(int anno, Map<Integer, Director> idMap){
		String sql ="SELECT Distinct d.director_id AS d "
				+ "FROM movies_directors d, movies m "
				+ "WHERE d.movie_id=m.id "
				+ "AND m.year= ? ";
		List<Director> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.add(idMap.get(res.getInt("d")));
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	public List<Coppia> getarchi(int anno, Map<Integer, Director> idMap){
		String sql = "SELECT DISTINCT d1.director_id AS d1, d2.director_id AS d2, COUNT(r1.actor_id) AS peso "
				+ "FROM movies_directors d1, movies_directors d2, roles r1,roles r2, movies m1, movies m2 "
				+ "WHERE d1.movie_id=m1.id "
				+ "	and d1.director_id>d2.director_id "
				+ "		AND d2.movie_id=m2.id "
				+ "AND m1.year=? "
				+ "AND m1.year=m2.year "
				+ "AND m1.id=r1.movie_id "
				+ "AND m2.id=r2.movie_id "
				+ "AND r1.actor_id=r2.actor_id "
				+ "GROUP BY d1,d2";
		List<Coppia> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.add(new Coppia(idMap.get(res.getInt("d1")), idMap.get(res.getInt("d2")), res.getInt("peso")));
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public List<Director> getVertici(Integer anno, Map<Integer, Director > idMap){
		String sql ="SELECT distinct d.director_id as id "
				+ "FROM movies m, movies_directors d "
				+ "WHERE m.id=d.movie_id AND m.year = ? ";
		List<Director> result = new ArrayList<Director>();
			Connection conn = DBConnect.getConnection();

			try {
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, anno);
				ResultSet res = st.executeQuery();
				while (res.next()) {

					result.add(idMap.get(res.getInt("id")));
					
				}
				conn.close();
				return result;
				
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	}
	 
	public List<Coppia> getArchi(Integer anno, Map<Integer, Director> idMap){
		String sql="SELECT d1.director_id d1, d2.director_id d2, COUNT(r1.actor_id) AS peso "
				+ "FROM movies_directors d1, movies_directors d2, movies m1,movies m2, roles r1, roles r2 "
				+ "WHERE d1.director_id<>d2.director_id "
				+ "		AND d1.director_id>d2.director_id "
				+ "		AND m1.year = ? "
				+ "		AND m2.year = ? "
				+ "		AND d1.movie_id = m1.id "
				+ "		AND d2.movie_id = m2.id "
				+ "		AND m1.id= r1.movie_id "
				+ "		AND m2.id=r2.movie_id "
				+ "		AND r1.actor_id=r2.actor_id "
				+ "GROUP BY d1.director_id, d2.director_id ";
		
		List<Coppia> result = new ArrayList<Coppia>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			st.setInt(2, anno);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.add(new Coppia(idMap.get(res.getInt("d1")),idMap.get(res.getInt("d2")),
						res.getInt("peso")));
				
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	*/
	
}

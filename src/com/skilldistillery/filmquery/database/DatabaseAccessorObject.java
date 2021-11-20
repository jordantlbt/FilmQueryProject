package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static Connection conn;
	private static final String user = "student";
	private static final String pass = "student";

	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {

			conn = DriverManager.getConnection(URL, user, pass);
			
			String sql = "SELECT * FROM film WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();

			while( filmResult.next() ) {

				film = new Film();
				film.setId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setLanguageId(filmResult.getInt("language_id"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setSpecialFeatures(filmResult.getString("special_features"));
				film.setActors(findActorsByFilmId(filmId));
				
				return film;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return film;

	}

	@Override
	public Actor findActorById(int actorId) {

		Actor actor = null;

		try {

			conn = DriverManager.getConnection(URL, user, pass);
			
			String sql = "SELECT * FROM actor WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();

			while (actorResult.next()) {

				actor = new Actor();
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actor;

	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {

		List<Actor> actors = new ArrayList<>();
		Actor actor = null;

		try {
			
			conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT actor.id, actor.first_name, actor.last_name "
						+ "FROM actor JOIN film_actor ON actor.id = film_actor.actor_id "
						+ "JOIN film ON film_actor.film_id = film.id WHERE film.id = ?"; 
			

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				
				actors.add(actor);				

			}
	
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
		
	}

	
	@Override
	public List<Film> findFilmByKeyWord(String keyWord) {
		
		List<Film> films = new ArrayList<>();
		Film film = null;

		try {

			conn = DriverManager.getConnection(URL, user, pass);
			
			String sql = "SELECT * FROM film WHERE description LIKE ? OR title LIKE ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyWord + "%");
			stmt.setString(2, "%" + keyWord + "%");
			ResultSet filmResult = stmt.executeQuery();

			while( filmResult.next() ) {

				film = new Film();
				film.setId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setLanguageId(filmResult.getInt("language_id"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setSpecialFeatures(filmResult.getString("special_features"));
				film.setActors(findActorsByFilmId(filmResult.getInt("id")));
				
				films.add(film);
			}
			
		
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return films;

	}
	
	@Override
	public String findLanguageById(int languageId) {

		String language = null;

		try {

			conn = DriverManager.getConnection(URL, user, pass);
			
			String sql = "SELECT name FROM language WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, languageId);
			ResultSet languageResult = stmt.executeQuery();

			while (languageResult.next()) {

				language = languageResult.getString("name");			

			}
			
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return language;

	}
}

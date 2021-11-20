package com.skilldistillery.filmquery.database;

import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
<<<<<<< HEAD
  public Film findFilmById(int filmId);
  public Actor findActorById(int actorId);
  public List<Actor> findActorsByFilmId(int filmId);
  
  
}
=======
	
	
  public Film findFilmById(int filmId);
  public Actor findActorById(int actorId);
  public List<Actor> findActorsByFilmId(int filmId);
  public List<Film> findFilmByKeyWord(String keyWord);
  public String findLanguageById(int languageId);
  
}
>>>>>>> 67d0567b4724708965907f310c458672db5a4e56

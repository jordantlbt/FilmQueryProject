package com.skilldistillery.filmquery.app;

import java.util.List;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();
	}

//	private void test() {
//		Film film = db.findFilmById(1);
//		System.out.println(film);
//	}

	private void launch() {

		Scanner input = new Scanner(System.in);
		startUserInterface(input);
		input.close();

	}

	private void startUserInterface(Scanner input) {

		boolean keepGoing = true;

		while (keepGoing) {

			printMenu();

			Film film;
			int menuChoice = input.nextInt();
			switch (menuChoice) {

			case 1: // search film by film's id

				System.out.println("Please enter the film's ID:");

				int filmID = input.nextInt();
				film = db.findFilmById(filmID);

				if (film != null) {
					System.out.println(film.printString() + "\nLanguage: " + db.findLanguageById(film.getLanguageId()));
					for (Actor actor : film.getActors()) {
						System.out.println("Actors: " + actor.getFirstName() + " " + actor.getLastName());
					}

				} else {

					System.out.println();
					System.err.println("There are no films with that ID");
					System.out.println();
				}

				break;

			case 2: // search film by keyword in either film's title or film's description

				System.out.println("Please enter a keyword you would like to search for:");
				String keyWord = input.next();
				List<Film> films = db.findFilmByKeyWord(keyWord);

				if (films.size() > 0) {

					for (Film filmK : films) {
						// for every film in the array of films print out the printString and the
						// language

						System.out.println(
								filmK.printString() + "\nLanguage: " + db.findLanguageById(filmK.getLanguageId()));
						// for every actor in the array of actors print out the actors first and last
						// name

						for (Actor actor : filmK.getActors()) {
							System.out.println("Actors: " + actor.getFirstName() + " " + actor.getLastName());

						}

						// used to create a space between each movie and movie data and the next movie
						// and movie data
						System.out.println();
						System.out.println();

					}
				} else {

					System.out.println();
					System.err.println("There are no films with that keyword");
					System.out.println();
				}

				break;

			case 3: // search actor by actor id

				System.out.println("Please enter the actor's ID:");
				int actorID = input.nextInt();
				Actor actor = db.findActorById(actorID);

				if (actor != null) {
					System.out.println("Actor: " + actor.getFirstName() + " " + actor.getLastName());

				} else {

					System.out.println();
					System.err.println("There are no actors with that ID");
					System.out.println();
				}

				break;

			case 4:// exit the program

				System.out.println("Thank you for using MockBuster!");
				keepGoing = false;
				break;

			}

		}
	}

	//Print menu for users
	private void printMenu() {

		System.out.println("=====================================");
		System.out.println("Welcome to MockBuster!");
		System.out.println("-------------------------------------");
		System.out.println("");
		System.out.println("Please select an option from the menu:");
		System.out.println("");
		System.out.println("1. Search films by ID");
		System.out.println("2. Search film's title or description by keyword");
		System.out.println("3. Search actors by ID");
		System.out.println("4. Exit");
		System.out.println("");
		System.out.println("=====================================");

	}
}


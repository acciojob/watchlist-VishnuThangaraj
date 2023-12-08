package com.driver.Repository;

import com.driver.Models.Director;
import com.driver.Models.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {

    HashMap<Director, List<Movie>> directorToMovie;
    HashSet<Movie> movies;
    HashSet<Director> directors;

    // Constructor
    public MovieRepository(){
        directorToMovie = new HashMap<>();

        movies = new HashSet<>();
        directors = new HashSet<>();
    }

    // Add a Movie to Database
    public void addMovie(Movie movie){
        movies.add(movie);
    }

    // Add a Director to Database
    public void addDirector(Director director){
        directors.add(director);
        directorToMovie.put(director, new ArrayList<>());
    }

    // Get Movie By Name
    public Movie getMovie(String movieName){
        return movies.stream().filter(movie -> movie.getName().equals(movieName)).
                findFirst().orElse(null);
    }

    // Get Director by Name
    public Director getDirector(String directorName){
        return directors.stream().filter(director -> director.getName().equals(directorName)).
                findFirst().orElse(null);
    }

    // Pair Movie and Director
    public void addMovieDirectorPair(Movie movie, Director director){
        directorToMovie.get(director).add(movie);
    }

    // Get List of movies name for a given director Object
    public List<String> getMoviesByDirectorName(Director director){
        return directorToMovie.get(director).stream().
                map(Movie::getName).collect(Collectors.toList());
    }

    // Get List of all movies added
    public List<String> findAllMovies(){
        return movies.stream().map(Movie::getName).collect(Collectors.toList());
    }

    // Delete a director and its movies from the records
    public void deleteDirector(Director director){
        directors.remove(director);
        directorToMovie.get(director).forEach(movie -> movies.remove(movie));
        directorToMovie.remove(director);
    }

    // Delete all directors and all movies by them from the records
    public void deleteAllDirectors(){
        directors.forEach(director -> {directorToMovie.get(director).forEach(movie -> movies.remove(movie));});
        directorToMovie.clear();
        directors.clear();
    }
}

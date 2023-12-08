package com.driver;

import com.driver.Director;
import com.driver.Movie;
import com.driver.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    // Add a Movie
    public void addMovie(Movie movie){
        movieRepository.addMovie(movie);
    }

    // Add a Director
    public void addDirector(Director director){
        movieRepository.addDirector(director);
    }

    // Pair Movie and Director
    public void addMovieDirectorPair(String movieName, String directorName){
        Movie movie = getMovieByName(movieName);
        Director director = getDirectorByName(directorName);

        movieRepository.addMovieDirectorPair(movie, director);
    }

    // Get Movie by movie name
    public Movie getMovieByName(String movieName){
        return movieRepository.getMovie(movieName);
    }

    // Get Director by director name
    public Director getDirectorByName(String directorName){
        return movieRepository.getDirector(directorName);
    }

    // Get List of movies name for a given director name
    public List<String> getMoviesByDirectorName(String directorName){
        // Get the Director
        Director director = getDirectorByName(directorName);

        return movieRepository.getMoviesByDirectorName(director);
    }

    // Get List of all movies added
    public List<String> findAllMovies(){
        return movieRepository.findAllMovies();
    }

    // Delete a director and its movies from the records
    public void deleteDirectorByName(String directorName){
        Director director = getDirectorByName(directorName);
        movieRepository.deleteDirector(director);
    }

    // Delete all directors and all movies by them from the records
    public void deleteAllDirectors(){
        movieRepository.deleteAllDirectors();
    }
}

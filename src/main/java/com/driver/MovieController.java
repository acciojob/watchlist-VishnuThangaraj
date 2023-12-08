package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    // Add a Movie
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    // Add a Director
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    // Pair Movie and Director
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movieName, String directorName){
        movieService.addMovieDirectorPair(movieName, directorName);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    // Get Movie by movie name
    @GetMapping("/get-movie-by-name")
    public ResponseEntity<Movie> getMovieByName(@RequestParam String movieName){
        Movie movie = movieService.getMovieByName(movieName);
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }

    // Get Director by director name
    @GetMapping("/get-director-by-name")
    public ResponseEntity<Director> getDirectorByName(@RequestParam String directorName){
        Director director = movieService.getDirectorByName(directorName);
        return new ResponseEntity<Director>(director, HttpStatus.OK);
    }

    // Get List of movies name for a given director name
    @GetMapping("/get-movies-by-director-name")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@RequestParam String name){
        List<String> movieName = movieService.getMoviesByDirectorName(name);
        return new ResponseEntity<List<String>>(movieName, HttpStatus.OK);
    }

    // Get List of all movies added
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> movieName = movieService.findAllMovies();
        return new ResponseEntity<List<String>>(movieName, HttpStatus.OK);
    }

    // Delete a director and its movies from the records
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String name){
        movieService.deleteDirectorByName(name);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    // Delete all directors and all movies by them from the records
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
}

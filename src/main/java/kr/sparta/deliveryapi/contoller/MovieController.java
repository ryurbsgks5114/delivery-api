package kr.sparta.deliveryapi.contoller;

import kr.sparta.deliveryapi.model.Movie;
import kr.sparta.deliveryapi.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/year")
    public ResponseEntity<List<Movie>> getMovieYear(@RequestParam int year) {

        List<Movie> movie = movieService.getMovieYear(year);

        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

    @GetMapping("/movies/genre")
    public ResponseEntity<List<Movie>> getMovieGenre(@RequestParam String genre) {

        List<Movie> movie = movieService.getMovieGenre(genre);

        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

}

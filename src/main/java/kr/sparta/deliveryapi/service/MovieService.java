package kr.sparta.deliveryapi.service;

import kr.sparta.deliveryapi.model.Movie;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {

    private static final String URL = "http://14.39.87.120:8083/v1/movies";

    private final RestTemplate restTemplate;

    public MovieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Movie> getMovieYear(int year) {

        Movie[] movie = getMovie();

        return Arrays.stream(movie).filter( (el) -> el.getYear() == year).toList();
    }

    public List<Movie> getMovieGenre(String genre) {

        Movie[] movie = getMovie();

        return Arrays.stream(movie).filter( (el) -> el.getGenre().contains(genre)).toList();
    }

    private Movie[] getMovie() {
        return restTemplate.getForObject(URL, Movie[].class);
    }

}

package kr.sparta.deliveryapi.model;

import lombok.Getter;

@Getter
public class Movie {

    private Long id;
    private String title;
    private int year;
    private String country;
    private String genre;
    private int runtime;
    private String actors;

}

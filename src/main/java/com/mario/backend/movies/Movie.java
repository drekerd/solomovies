package com.mario.backend.movies;

import lombok.Data;

@Data
public class Movie {
    long id;
    String name;
    String description;
    String thumbnailFullLink;

}

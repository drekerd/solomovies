package com.mario.frontend.trending.movies;

import lombok.Data;

@Data
public class TrendingMoviesFromBE {
    long id;
    String name;
    String description;
    String thumbnailFullLink;
}

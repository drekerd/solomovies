package com.mario.frontend.trendingMovies;

import lombok.Data;

@Data
public class TrendingMoviesFromBE {
    long id;
    String name;
    String description;
    String thumbnailFullLink;
}

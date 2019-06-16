package com.mario.backend.trending.movies;

import com.google.gson.annotations.SerializedName;
import com.mario.backend.movies.MovieTrailers;
import lombok.Data;

import java.util.List;

@Data
public class TrendingMoviesStore {

    private long id;
    @SerializedName("title")
    private String name;
    @SerializedName("overview")
    private String description;
    @SerializedName("poster_path")
    private String thumbnailLink;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("results")
    private List<MovieTrailers> movieTrailers;

}

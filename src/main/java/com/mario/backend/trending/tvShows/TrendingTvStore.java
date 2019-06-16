package com.mario.backend.trending.tvShows;

import com.google.gson.annotations.SerializedName;
import com.mario.backend.movies.MovieTrailers;
import lombok.Data;

import java.util.List;

@Data
public class TrendingTvStore {

    private long id;
    @SerializedName("original_name")
    private String name;
    @SerializedName("overview")
    private String description;
    @SerializedName("poster_path")
    private String thumbnailLink;
    @SerializedName("release_date")
    private String releaseDate;
    private String popularity;
    @SerializedName("results")
    private List<MovieTrailers> movieTrailers;

}

package com.mario.backend.movies;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class MovieByID {

    long id;
    @SerializedName("original_title")
    private String name;
    @SerializedName("overview")
    private String description;
    private String popularity;
    @SerializedName("poster_path")
    private String thumbnailFullLink;
    @SerializedName("release_date")
    private String releaseDate;
    private String tagline;
    @SerializedName("vote_average")
    private String voteAverage;
    @SerializedName("results")
    private List<MovieTrailers> movieTrailers;

}

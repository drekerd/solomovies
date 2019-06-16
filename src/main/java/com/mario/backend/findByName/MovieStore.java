package com.mario.backend.findByName;

import com.google.gson.annotations.SerializedName;
import com.mario.backend.movies.MovieTrailers;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
public class MovieStore {

    private long id;
    @SerializedName("title")
    private String name;
    @SerializedName("overview")
    private String description;
    @SerializedName("poster_path")
    private String thumbnailLink;
    private String thumbnailFullLink;
    @SerializedName("results")
    private List<MovieTrailers> movieTrailers;

}

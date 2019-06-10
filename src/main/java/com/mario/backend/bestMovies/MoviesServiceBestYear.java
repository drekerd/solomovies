package com.mario.backend.bestMovies;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MoviesServiceBestYear {

    private long id;

    @SerializedName("title")
    private String name;
    @SerializedName("overview")
    private String description;
    private double popularity;

    @SerializedName("poster_path")
    private String thumbnailLink;
    private String thumbnailFullLink;

}

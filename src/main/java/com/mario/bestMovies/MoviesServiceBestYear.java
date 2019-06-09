package com.mario.bestMovies;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MoviesServiceBestYear {

    @SerializedName("title")
    private String name;
    private String overview;
    private double popularity;

}

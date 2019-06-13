package com.mario.backend.movies;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MovieTrailers {

    @SerializedName("key")
    private String trailerPath;
}

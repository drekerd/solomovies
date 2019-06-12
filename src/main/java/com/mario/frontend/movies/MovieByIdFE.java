package com.mario.frontend.movies;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MovieByIdFE {

    long id;

    @SerializedName("title")
    String name;
    @SerializedName("original_tile")
    String originalTitle;
    @SerializedName("overview")
    String description;

}

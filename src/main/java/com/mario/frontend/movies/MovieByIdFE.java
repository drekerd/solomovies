package com.mario.frontend.movies;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MovieByIdFE {

    private long id;
    private String name;
    private String description;
    private String releaseDate;
    private String popularity;
    private String voteAverage;
    private String tagLine;
    private String thumbnailFullLink;
    private String trailerFulURL;
}

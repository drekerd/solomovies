package com.mario.backend.movies;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MovieByIDtoFE {

    private long id;
    private String name;
    private String description;
    private String popularity;
    private String thumbnailFullLink;
    private String releaseDate;
    private String tagline;
    private String voteAverage;
    private String trailerFulURL;

    public MovieByIDtoFE(MovieByID movieByID) {
        this.id = movieByID.id;
        this.name = movieByID.getName();
        this.description = checkDescription(movieByID.getDescription());
        this.popularity = movieByID.getPopularity();
        this.releaseDate = movieByID.getReleaseDate();
        this.tagline = movieByID.getTagline();
        this.voteAverage = movieByID.getVoteAverage();
    }

    private String checkDescription(String description) {

        if (description.isEmpty()) {
            return "This movie has no description";
        } else
            return description;
    }


}

package com.mario.backend.trending.movies;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class TrendingMoviesResponseFE {

    private long id;
    private String name;
    @Setter(AccessLevel.NONE)
    private String description;
    private String thumbnailFullLink;
    private String trailerFulURL;

    public void setDescription(String description) {

        if (description.isEmpty()) {
            this.description ="This movie has no description";
        } else
            this.description = description;
    }
}

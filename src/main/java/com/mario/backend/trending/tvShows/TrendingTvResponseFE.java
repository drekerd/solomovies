package com.mario.backend.trending.tvShows;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class TrendingTvResponseFE {

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

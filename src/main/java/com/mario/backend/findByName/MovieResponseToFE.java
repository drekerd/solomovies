package com.mario.backend.findByName;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
public class MovieResponseToFE {

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

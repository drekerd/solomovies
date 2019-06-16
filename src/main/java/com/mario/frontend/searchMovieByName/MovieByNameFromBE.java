package com.mario.frontend.searchMovieByName;

import lombok.Data;

@Data
public class MovieByNameFromBE {

    long id;
    String name;
    String description;
    String thumbnailFullLink;

}

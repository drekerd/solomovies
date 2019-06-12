package com.mario.frontend;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class BestFilm {

    long id;
    String name;
    String description;
    String thumbnailFullLink;
}

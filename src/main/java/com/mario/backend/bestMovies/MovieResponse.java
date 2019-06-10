package com.mario.backend.bestMovies;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.ws.rs.Produces;

@Data
@Produces("application/json")
public class MovieResponse {

    private long id;
    private String name;
    @Setter(AccessLevel.NONE)
    private String description;
    private double popularity;
    private String thumbnailLink;
    private String thumbnailFullLink;

    public void setDescription(String description){
        System.out.printf("aqi");
        if(description.isEmpty()){
            this.description="No Description available";
        }else{
            this.description=description;
        }

    }

}

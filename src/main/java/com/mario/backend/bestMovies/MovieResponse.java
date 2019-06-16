package com.mario.backend.bestMovies;

import com.mario.utils.Log;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.ws.rs.Produces;
import java.io.IOException;

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
        if(description.isEmpty()){
            this.description="No Description available";
        }else{
            this.description=description;
        }
    }

}

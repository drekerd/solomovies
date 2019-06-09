package com.mario.bestMovies;

import lombok.Data;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Data
@Produces("application/json")
public class MovieResponse {

    private String name;
    private String overview;
    private double popularity;

}

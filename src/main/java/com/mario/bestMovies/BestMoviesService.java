package com.mario.bestMovies;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Data
public class BestMoviesService {

    int page;

    @SerializedName("total_results")
    int totalResults;
    @SerializedName("total_pages")
    int totalPages;

    @SerializedName("results")
    List<MoviesServiceBestYear> movies;

}

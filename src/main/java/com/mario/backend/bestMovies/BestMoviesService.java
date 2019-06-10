package com.mario.backend.bestMovies;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

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

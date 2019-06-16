package com.mario.backend.trending.movies;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class TrendingMoviesService {

    int page;

    @SerializedName("total_results")
    int totalResults;
    @SerializedName("total_pages")
    int totalPages;

    @SerializedName("results")
    List<TrendingMoviesStore> movies;
}

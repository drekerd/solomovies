package com.mario.backend.trending.tvShows;

import com.google.gson.annotations.SerializedName;
import com.mario.backend.trending.movies.TrendingMoviesStore;
import lombok.Data;

import java.util.List;

@Data
public class TrendingTvService {

    int page;
    @SerializedName("total_results")
    int totalResults;
    @SerializedName("total_pages")
    int totalPages;
    @SerializedName("results")
    List<TrendingTvStore> tvShows;
}

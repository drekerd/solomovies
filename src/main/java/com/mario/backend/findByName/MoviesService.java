package com.mario.backend.findByName;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class MoviesService {

    int page;

    @SerializedName("total_results")
    int totalResults;
    @SerializedName("total_pages")
    int totalPages;

    @SerializedName("results")
    List<MovieStore> movies;
}

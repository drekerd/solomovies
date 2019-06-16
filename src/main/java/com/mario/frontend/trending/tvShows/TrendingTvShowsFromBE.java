package com.mario.frontend.trending.tvShows;

import lombok.Data;

@Data
public class TrendingTvShowsFromBE {
    private long id;
    private String name;
    private String description;
    private String thumbnailFullLink;
}

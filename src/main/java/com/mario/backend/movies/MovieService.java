package com.mario.backend.movies;

import com.google.gson.GsonBuilder;
import com.mario.backend.bestMovies.BestMoviesService;
import com.mario.backend.bestMovies.MovieResponse;
import com.mario.backend.bestMovies.MoviesServiceBestYear;
import com.mario.backend.images.ImageApiCall;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieService {
   // https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US

    private static final String BASE_URL = " https://api.themoviedb.org/3/movie/";
    private static final String API_KEY = "db10e7a8660d7d089fb952a7a4fe4d13";

    public List<MovieResponse> getBestMoviesByYear(@QueryParam("id") long id) throws IOException {
        String uri = BASE_URL + id +  "&api_key="+API_KEY;

        String bodyAsString = callTMDBApi(uri);

        BestMoviesService bestMoviesResponse = new GsonBuilder()
                .create()
                .fromJson(bodyAsString,BestMoviesService.class);

        List<MoviesServiceBestYear> moviesResponseAPI = bestMoviesResponse.getMovies();

        List<MovieResponse> movieResponseForFE = new ArrayList<>();
        ImageApiCall image = new ImageApiCall();

        for(MoviesServiceBestYear m: moviesResponseAPI){

            MovieResponse movie = new MovieResponse();
            movie.setName(m.getName());
            movie.setDescription(m.getDescription());
            movie.setPopularity(m.getPopularity());
            movie.setId(m.getId());
            movie.setThumbnailLink(m.getThumbnailLink());
            movie.setThumbnailFullLink(image.getImageForMoiveBURL(m.getThumbnailLink()));

            movieResponseForFE.add(movie);
        }

        return movieResponseForFE;
    }

    private String callTMDBApi(final String uri) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = client.execute(new HttpGet(uri));
        String bodyAsString = EntityUtils.toString(response.getEntity());
        return bodyAsString;
    }

}

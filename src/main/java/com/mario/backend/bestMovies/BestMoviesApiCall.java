package com.mario.backend.bestMovies;

import com.google.gson.GsonBuilder;
import com.mario.backend.images.ImageApiCall;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("best")
public class BestMoviesApiCall {
    private static final String BASE_URL = "https://api.themoviedb.org/3/discover/movie";
    private static final String API_KEY = "db10e7a8660d7d089fb952a7a4fe4d13";

    @GET
    @Path("/hello")
    @Produces({MediaType.APPLICATION_JSON})
    public String getHello(){
        return "hello";
    }

    @GET
    @Path("/year")
    @Produces("application/json")
    public List<MovieResponse> getBestMoviesByYear(@QueryParam("year") String year) throws IOException {
        String uri = BASE_URL;

        if(year != null){
            uri += "?primary_release_year=" + year + "&sort_by=vote_average.desc";
        }
        uri += "?primary_release_year=" + 2019 + "&sort_by=vote_average.desc"+"&api_key="+API_KEY;

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

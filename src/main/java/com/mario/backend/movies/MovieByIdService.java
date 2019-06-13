package com.mario.backend.movies;

import com.google.gson.GsonBuilder;
import com.mario.backend.bestMovies.BestMoviesService;
import com.mario.backend.bestMovies.MovieResponse;
import com.mario.backend.bestMovies.MoviesServiceBestYear;
import com.mario.backend.images.ImageApiCall;
import com.mario.backend.trailers.TrailersCompose;
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

@Path("details")
public class MovieByIdService {
   // https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String API_KEY = "db10e7a8660d7d089fb952a7a4fe4d13";

    @GET
    @Path("/hello")
    @Produces({MediaType.APPLICATION_JSON})
    public String getHello(){
        return "hello";
    }

    @GET
    @Path("/movie")
    @Produces("application/json")
    public MovieByIDtoFE getMoviesByID(@QueryParam("id") long id) throws IOException {

        String uri = BASE_URL + id + "?api_key="+API_KEY;

        String bodyAsString = callTMDBApi(uri);

        MovieByID movieByIDResponse = new GsonBuilder()
                .create()
                .fromJson(bodyAsString,MovieByID.class);

        MovieByIDtoFE movieResponseForFE = new MovieByIDtoFE(movieByIDResponse);
        ImageApiCall image = new ImageApiCall();
        TrailersCompose trailer = new TrailersCompose();
        movieResponseForFE.setThumbnailFullLink(image.getImageForMoiveBURL(movieByIDResponse.getThumbnailFullLink()));
        movieResponseForFE.setTrailerFulURL(trailer.composeTrailerURL(movieByIDResponse.id));

        return movieResponseForFE;
    }

    private String callTMDBApi(final String uri) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = client.execute(new HttpGet(uri));
        return  EntityUtils.toString(response.getEntity());
    }

}

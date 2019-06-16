package com.mario.backend.findByName;

import com.google.gson.GsonBuilder;
import com.mario.backend.bestMovies.MovieResponse;
import com.mario.backend.images.ImageApiCall;
import com.mario.backend.movies.MovieByID;
import com.mario.backend.movies.MovieByIDtoFE;
import com.mario.backend.trailers.TrailersCompose;
import com.mario.utils.Log;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("search")
public class MovieAPICall {

    Log log = new Log();

    private final String BASE_URL = "https://api.themoviedb.org/3/search/movie?query=";
    private final String API_KEY = "db10e7a8660d7d089fb952a7a4fe4d13";
    private final String OTHER_INSTRUCTIONS = "&page=1&include_adult=false";

    public MovieAPICall() throws IOException {
    }


    @GET
    @Path("/hello")
    @Produces({MediaType.APPLICATION_JSON})
    public String getHello() {
        return "hello";
    }

    @GET
    @Path("/movie")
    @Produces("application/json")
    public List<MovieResponseToFE> getMoviesByName(@QueryParam("movie") String movie) throws IOException {

        String uri = BASE_URL + movie + "&api_key=" + API_KEY + OTHER_INSTRUCTIONS;

        log.logger.info(this.getClass().getName() + " URI " + uri);

        String bodyAsString = callTMDBApi(uri);

        MoviesService movieByNameResponse = new GsonBuilder()
                .create()
                .fromJson(bodyAsString, MoviesService.class);

        ImageApiCall image = new ImageApiCall();
        TrailersCompose trailer = new TrailersCompose();

        List<MovieStore> moviesResponseAPI = movieByNameResponse.getMovies();
        List<MovieResponseToFE> movieResponseForFE = new ArrayList<>();

        for (MovieStore m : moviesResponseAPI) {
            MovieResponseToFE movieToFE = new MovieResponseToFE();

            movieToFE.setId(m.getId());
            movieToFE.setName(m.getName());
            movieToFE.setDescription(m.getDescription());
            movieToFE.setThumbnailFullLink(image.getImageForMoiveBURL(m.getThumbnailLink()));
            movieToFE.setTrailerFulURL(trailer.composeTrailerURL(m.getId()));

            movieResponseForFE.add(movieToFE);
        }


        return movieResponseForFE;
    }

    private String callTMDBApi(final String uri) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = client.execute(new HttpGet(uri));
        return EntityUtils.toString(response.getEntity());
    }

}

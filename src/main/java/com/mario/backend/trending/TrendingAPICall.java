package com.mario.backend.trending;

import com.google.gson.GsonBuilder;
import com.mario.backend.images.ImageApiCall;
import com.mario.backend.trailers.TrailersCompose;
import com.mario.backend.trending.movies.TrendingMoviesResponseFE;
import com.mario.backend.trending.movies.TrendingMoviesService;
import com.mario.backend.trending.movies.TrendingMoviesStore;
import com.mario.backend.trending.tvShows.TrendingTvResponseFE;
import com.mario.backend.trending.tvShows.TrendingTvService;
import com.mario.backend.trending.tvShows.TrendingTvStore;
import com.mario.utils.Log;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Path("trending")
public class TrendingAPICall {

    private final String BASE_URL = "https://api.themoviedb.org/3/trending/";
    private final String API_KEY = "api_key=db10e7a8660d7d089fb952a7a4fe4d13";
    private final String OTHER_INSTRUCTIONS = "&page=1&include_adult=false";

    Log log = new Log();

    public TrendingAPICall() throws IOException {
    }

    @GET
    @Path("/movie")
    @Produces("application/json")
    public List<TrendingMoviesResponseFE> getTrendingMovies() throws IOException {

        String uri = BASE_URL + "movie/week?" + API_KEY;

        log.logger.info(this.getClass().getName() + " CALLING URI " + uri);

        String bodyAsString = callTMDBApi(uri);

        TrendingMoviesService moviesTrendResponse = new GsonBuilder()
                .create()
                .fromJson(bodyAsString, TrendingMoviesService.class);

        ImageApiCall image = new ImageApiCall();
        TrailersCompose trailer = new TrailersCompose();

        List<TrendingMoviesStore> moviesResponseAPI = moviesTrendResponse.getMovies();
        List<TrendingMoviesResponseFE> movieResponseForFE = new ArrayList<>();

        for (TrendingMoviesStore m : moviesResponseAPI) {
            TrendingMoviesResponseFE movieToFE = new TrendingMoviesResponseFE();

            movieToFE.setId(m.getId());
            movieToFE.setName(m.getName());
            movieToFE.setDescription(m.getDescription());
            movieToFE.setThumbnailFullLink(image.getImageForMoiveBURL(m.getThumbnailLink()));
            movieToFE.setTrailerFulURL(trailer.composeTrailerURL(m.getId()));

            movieResponseForFE.add(movieToFE);
        }

        return movieResponseForFE;
    }

    @GET
    @Path("/tvshow")
    @Produces("application/json")
    public List<TrendingTvResponseFE> getTrendingShows() throws IOException {

        String uri = BASE_URL + "tv/week?" + API_KEY;

        log.logger.info(this.getClass().getName() + " CALLING URI " + uri);

        String bodyAsString = callTMDBApi(uri);

        TrendingTvService tvShowTrendResponse = new GsonBuilder()
                .create()
                .fromJson(bodyAsString, TrendingTvService.class);

        ImageApiCall image = new ImageApiCall();
        TrailersCompose trailer = new TrailersCompose();

        List<TrendingTvStore> tvResponseAPI = tvShowTrendResponse.getTvShows();
        List<TrendingTvResponseFE> tvResponseForFE = new ArrayList<>();

        for (TrendingTvStore t : tvResponseAPI) {
            TrendingTvResponseFE tvToFE = new TrendingTvResponseFE();

            tvToFE.setId(t.getId());
            tvToFE.setName(t.getName());
            tvToFE.setDescription(t.getDescription());
            tvToFE.setThumbnailFullLink(image.getImageForMoiveBURL(t.getThumbnailLink()));
            tvToFE.setTrailerFulURL(trailer.composeTrailerURL(t.getId()));

            tvResponseForFE.add(tvToFE);
        }

        return tvResponseForFE;
    }



    private String callTMDBApi(final String uri) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = client.execute(new HttpGet(uri));
        return EntityUtils.toString(response.getEntity());
    }


}

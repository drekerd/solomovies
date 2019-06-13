package com.mario.backend.trailers;

import com.google.gson.GsonBuilder;
import com.mario.backend.movies.MovieByID;
import com.mario.backend.movies.MovieTrailers;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class TrailersCompose {

    private final String YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v=";
    private final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    private static final String API_KEY = "db10e7a8660d7d089fb952a7a4fe4d13";

    public String composeTrailerURL(long id) throws IOException {

        return YOUTUBE_BASE_URL+getTrailers(id);
    }

    private String getTrailers(long id) throws IOException {
        String URI =BASE_URL+id+"/videos?api_key="+API_KEY;

        String bodyAsString = callTMDBApi(URI);

        MovieByID trailersUrls = new GsonBuilder()
                .create()
                .fromJson(bodyAsString,MovieByID.class);

        List<MovieTrailers> movieTrailers = trailersUrls.getMovieTrailers();
        return movieTrailers.get(0).getTrailerPath();
    }

    private String callTMDBApi(final String uri) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = client.execute(new HttpGet(uri));
        return  EntityUtils.toString(response.getEntity());
    }



}

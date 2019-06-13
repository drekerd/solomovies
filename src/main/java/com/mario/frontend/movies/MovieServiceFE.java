package com.mario.frontend.movies;

import com.google.gson.GsonBuilder;
import lombok.Data;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import java.io.IOException;

@Data
@ManagedBean
public class MovieServiceFE {

    //List<MovieByIdFE> movie;
    MovieByIdFE movie;
    String backendResponse;
    long id;


    @PostConstruct
    public void fetchMovies() {

        final String uri = "https://api.themoviedb.org/3/movie/552095?api_key=db10e7a8660d7d089fb952a7a4fe4d13";

        this.backendResponse = callRumosApi(uri);

        this.movie = buildResponse(backendResponse);
    }


    private String callRumosApi(final String uri) {

        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            CloseableHttpResponse response = client.execute(new HttpGet(uri));
            String bodyAsString = EntityUtils.toString(response.getEntity());
            return bodyAsString;
        } catch (IOException e) {
        }
        return "erro";
    }

    public MovieByIdFE buildResponse(String jsonBackendResponse) {

        final MovieByIdFE backendResponseAsObject = new GsonBuilder()
                .create()
                .fromJson(jsonBackendResponse, MovieByIdFE.class);
        return backendResponseAsObject;
    }
    /*public List<BestFilm> buildResponse(String jsonBackendResponse) {

        final List<BestFilm> backendResponseAsObject = new GsonBuilder()
                .create()
                .fromJson(jsonBackendResponse, new TypeToken<List<BestFilm>>(){}.getType());
        return backendResponseAsObject;
    }*/
}

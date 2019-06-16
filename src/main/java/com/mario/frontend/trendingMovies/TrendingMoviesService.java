package com.mario.frontend.trendingMovies;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mario.frontend.searchMovieByName.MovieByNameFromBE;
import com.mario.utils.Log;
import lombok.Data;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

@Data
@ManagedBean
@RequestScoped
public class TrendingMoviesService {

    Log movieByNameLOG;

    {
        try {
            movieByNameLOG = new Log();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<TrendingMoviesFromBE> movies;
    String backendResponse;

    //FacesContext fc = FacesContext.getCurrentInstance();
    String name;

    @PostConstruct
    public void fetchMovies() {

        final String uri = "http://localhost:8080/trending/movie";
        movieByNameLOG.logger.info(this.getClass().getName()+" URI " +uri);
        this.backendResponse = callRumosApi(uri);
        this.movies = buildResponse(backendResponse);

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

    public List<TrendingMoviesFromBE> buildResponse(String jsonBackendResponse) {

        final List<TrendingMoviesFromBE> backendResponseAsObject = new GsonBuilder()
                .create()
                .fromJson(jsonBackendResponse, new TypeToken<List<TrendingMoviesFromBE>>(){
                }.getType());
        return backendResponseAsObject;
    }
}

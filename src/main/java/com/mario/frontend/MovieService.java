package com.mario.frontend;


import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.IOException;


import lombok.Data;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


@Data
@ManagedBean
public class MovieService {

    List<BestFilm> movies;
    String backendResponse;
    String year;


    @PostConstruct
    public void fetchMovies() {

        if(year==null){
            year="2019";
        }

        final String uri = "http://localhost:8080/best/year?year="+year;
        this.backendResponse = callRumosApi(uri);
        this.movies = buildResponse(backendResponse);
    }

    public void getMovieByName() {

        if(year==null){
            year="2019";
        }

        final String uri = "http://localhost:8080/best/year?year="+year;
        this.backendResponse = callRumosApi(uri);
        this.movies = buildResponse(backendResponse);
    }

    public void checkYear(){
        System.out.println(year);
    }

    public void fetchMoviesByYear() {

        System.out.println(year);


        final String uri = "http://localhost:8080/best/year?year"+year;

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

    public List<BestFilm> buildResponse(String jsonBackendResponse) {

        final List<BestFilm> backendResponseAsObject = new GsonBuilder()
                .create()
                .fromJson(jsonBackendResponse, new TypeToken<List<BestFilm>>(){}.getType());
        return backendResponseAsObject;
    }
}

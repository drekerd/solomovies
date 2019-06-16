package com.mario.frontend.movies;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mario.frontend.BestFilm;
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
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@ManagedBean
@RequestScoped
public class MovieServiceFE implements Serializable {

    Log movieServiceFE;

    {
        try {
            movieServiceFE = new Log("movieServiceFE.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    MovieByIdFE movie;
    String backendResponse;

     /*@ManagedProperty(value = "#{movieID}")
     long id;*/

    FacesContext fc = FacesContext.getCurrentInstance();
    String movieID;

    @PostConstruct
    public void fetchMovies() {

        this.movieID = getIdParam(fc);

        final String uri = "http://localhost:8080/details/movie?id=" + movieID;
        movieServiceFE.logger.info("URI FOR REQUEST " + uri);
        this.backendResponse = callRumosApi(uri);
        movieServiceFE.logger.info("BackEnd Response +" + backendResponse);

        this.movie = buildResponse(backendResponse);
        movieServiceFE.logger.info("Movie Returned from json Converter" + this.movie.toString());
    }

    public String getIdParam(FacesContext fc){

        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("movieID");

    }

    private String callRumosApi(final String uri) {

        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            CloseableHttpResponse response = client.execute(new HttpGet(uri));
            String bodyAsString = EntityUtils.toString(response.getEntity());
            return bodyAsString;
        } catch (IOException e) {
        }
        return "error";
    }

    public MovieByIdFE buildResponse(String jsonBackendResponse) {

        final MovieByIdFE backendResponseAsObject = new GsonBuilder()
                .create()
                .fromJson(jsonBackendResponse, new TypeToken<MovieByIdFE>() {
                }.getType());
        return backendResponseAsObject;
    }
}

package com.mario.frontend.searchMovieByName;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@ManagedBean
public class MovieByNameService implements Serializable {

    Log movieByNameLOG;

    {
        try {
            movieByNameLOG = new Log("FEMOVIE.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<MovieByNameFromBE> movies;
    String backendResponse;

    FacesContext fc = FacesContext.getCurrentInstance();
    String name;

    @PostConstruct
    public void fetchMovies() {

        /*if (name == null) {
            name = "Interstellar";
        }*/

        final String uri = "http://localhost:8080/search/movie?movie=" + name;
        movieByNameLOG.logger.info(this.getClass().getName()+" URI " +uri);
        this.backendResponse = callRumosApi(uri);
        this.movies = buildResponse(backendResponse);

    }

    public String changePage(){
        return "movies.xhtml";
    }

    public String getIdParam(FacesContext fc){

        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("movieName");

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

    public List<MovieByNameFromBE> buildResponse(String jsonBackendResponse) {

        final List<MovieByNameFromBE> backendResponseAsObject = new GsonBuilder()
                .create()
                .fromJson(jsonBackendResponse, new TypeToken<List<MovieByNameFromBE>>() {
                }.getType());
        return backendResponseAsObject;
    }
}

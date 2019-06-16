package com.mario.frontend.trending.tvShows;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mario.frontend.movies.MovieByIdFE;
import com.mario.frontend.trending.movies.TrendingMoviesFromBE;
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
public class TrendingTvShowsService {
    Log movieByNameLOG;

    {
        try {
            movieByNameLOG = new Log();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<TrendingTvShowsFromBE> tvShows;
    String backendResponse;
    MovieByIdFE show;

    //FacesContext fc = FacesContext.getCurrentInstance();

    @PostConstruct
    public void fetchTvShows() {

        final String uri = "http://localhost:8080/trending/tvshow";
        movieByNameLOG.logger.info(this.getClass().getName()+" URI " +uri);
        this.backendResponse = callRumosApi(uri);
        movieByNameLOG.logger.info(this.getClass().getName()+" payload " +this.backendResponse);
        this.tvShows = buildResponse(backendResponse);

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

    public List<TrendingTvShowsFromBE> buildResponse(String jsonBackendResponse) {

        final List<TrendingTvShowsFromBE> backendResponseAsObject = new GsonBuilder()
                .create()
                .fromJson(jsonBackendResponse, new TypeToken<List<TrendingTvShowsFromBE>>(){
                }.getType());
        return backendResponseAsObject;
    }
}

package com.mario.backend.images;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ImageApiCall {

    final private String BASE_URL="https://image.tmdb.org/t/p/w500";
    final private String API_KEY="&api_key=db10e7a8660d7d089fb952a7a4fe4d13";

    //https://api.themoviedb.org/3/movie/320288/images?api_key=db10e7a8660d7d089fb952a7a4fe4d13

    public String getImageForMoiveBURL(String imageURL){

        return BASE_URL+imageURL;
    }

}

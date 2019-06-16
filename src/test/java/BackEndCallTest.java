
import com.mario.backend.findByName.MovieAPICall;
import com.mario.frontend.movies.MovieByIdFE;
import com.mario.frontend.movies.MovieServiceFE;
import com.mario.utils.Log;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@Ignore
public class BackEndCallTest {

    Log log = new Log("TestBackEnd.txt");

    public BackEndCallTest() throws IOException {
    }

    @Test
    public void testBackendCall() throws IOException {
        //http call to
        final String uri = "http://localhost:8080/best/year?year=2014";

        final String bodyAsString = callRumosApi(uri);
        System.out.println(bodyAsString);
        Assert.assertNotNull(bodyAsString);
    }

    @Test
    public void testMovieDetailsCallViaId() throws IOException {

        final String BASE_URL = "https://api.themoviedb.org/3/movie/";
        final String API_KEY = "db10e7a8660d7d089fb952a7a4fe4d13";
        final long id = 157336;

        String uri = BASE_URL + id +  "?api_key="+API_KEY;

        System.out.println(callRumosApi(uri));
    }

    @Test
    public void testMovieDetails() throws IOException {

        final String URI = "http://localhost:8080/details/movie?id=157336";
        String bodyAsString = callRumosApi(URI);
        log.logger.info("Test "+this+" result " +bodyAsString);

        assertNotNull(bodyAsString);

    }

    @Test
    public void TestMovieDetailsBECallBackl() throws IOException {
        MovieServiceFE movieServiceFE = new MovieServiceFE();
        String movieSeriviceResponse = callRumosApi("http://localhost:8080/details/movie?id=157336");
        log.logger.info("Movie Reponse from HTTP Request "+ movieSeriviceResponse);
        MovieByIdFE movie = movieServiceFE.buildResponse(movieSeriviceResponse);
        log.logger.info("Movie to String after Json Converter " + movie.toString());

        assertNotNull(movie.getName());
        assertNotNull(movie.getDescription());
    }

    @Test
    public void TestSearchByNameAPICall() throws IOException {
        MovieAPICall movie = new MovieAPICall();
        //System.out.printf(movie.getTrendingShows("Interstellar"));
    }

    //this is the method for all Tests make their HTTP requests
    private String callRumosApi(final String uri) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = client.execute(new HttpGet(uri));
        String bodyAsString = EntityUtils.toString(response.getEntity());
        return bodyAsString;
    }

}
